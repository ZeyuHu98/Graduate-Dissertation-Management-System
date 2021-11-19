package edu.whu.web;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import edu.whu.web.support.BaseController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "*.htms",name="BaseExcelServlet")
public class BaseExcelServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{
		List<Map<String, Object>> dtos = this.createExcel(request);
		String toPath = null; // 跳转的目标页面
		try 
		{

			/************************************************************
			 * 解析访问路径,获取目标类的名称
			 ************************************************************/
			// 拦截请求的访问路径
			String uri = request.getRequestURI();
			// 获取请求资源的主文件名
			String baseName = uri.substring(uri.lastIndexOf("/") + 1).replace(".htms", "");

			// 定义变量,描述所有业务控制器的基础包名称
			String basePackageName = "edu.whu.web.impl.";
			// 获取控制器的前缀名
			String controllerFirstName = baseName.substring(0, 1).toUpperCase() + baseName.substring(1);

			/***********************************************************
			 * 实例化目标类---业务控制器
			 ***********************************************************/
			// 实例化业务控制器
			System.out.println(basePackageName + controllerFirstName + "Servlet");
			BaseController controller = (BaseController) Class
					.forName(basePackageName + controllerFirstName + "Servlet").newInstance();

			/***********************************************************
			 * 向业务控制器,填充页面数据 i
			 ***********************************************************/
			for (Map<String, Object> dto : dtos) 
			{
				controller.setDto(dto);

				/***********************************************************
				 * 调用业务控制器的流程控制方法
				 ***********************************************************/
				// 调用流程控制方法
				toPath = controller.execute();
			}

		} 
		catch (Exception ex) 
		{
			request.setAttribute("msg", "提示:网络故障!");
			ex.printStackTrace();
		}
		request.getRequestDispatcher("/" + toPath + ".jsp").forward(request, response);
	}

	private List<Map<String, Object>> createExcel(HttpServletRequest request)
	{
		List<Map<String, Object>> dtos = new ArrayList<Map<String, Object>>();
		try 
		{
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);

			request.setCharacterEncoding("GBK");
			// 文件名中文乱码处理也可以如此写
//	       upload.setHeaderEncoding("utf-8");

			// 设置缓冲区大小与临时文件目录
			factory.setSizeThreshold(1024 * 1024 * 10);
			File uploadTemp = new File("e:\\uploadTemp");
			uploadTemp.mkdirs();
			factory.setRepository(uploadTemp);

			// 设置单个文件大小限制
			upload.setFileSizeMax(1024 * 1024 * 10);
			// 设置所有文件总和大小限制
			upload.setSizeMax(1024 * 1024 * 30);
			String filName = null;
			String uploadPath = null;
			try
			{
				List<FileItem> list = upload.parseRequest(request);
				for (FileItem fileItem : list)
				{
					if (!fileItem.isFormField() && fileItem.getName() != null && !"".equals(fileItem.getName())) 
					{
						filName = fileItem.getName();
						if(filName.contains("\\"))
						{
							filName = filName.substring(filName.lastIndexOf("\\"));
						}
						// 获取文件上传目录路径，在项目部署路径下的upload目录里。若想让浏览器不能直接访问到图片，可以放在WEB-INF下
						uploadPath = request.getSession().getServletContext().getRealPath("/upload");
						System.out.println(uploadPath + filName);
						File file = new File(uploadPath);
						file.mkdirs();
						// 写入文件到磁盘，该行执行完毕后，若有该临时文件，将会自动删除
						fileItem.write(new File(uploadPath, filName));

					}
				}
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}

			/*
			 * 解析excel
			 */
			String filePath = uploadPath + "\\" + filName;
			InputStream excelfile = new FileInputStream(filePath);// 读取指定位置中的文件，就是刚才保存在指定文件夹中的上传文件

			Workbook wb = null;
			// if(filePath.endsWith(".xlsx")){
			// System.out.println(filePath.endsWith(".xlsx"));
			// wb = new XSSFWorkbook(excelfile);//07版
			// System.out.println("\n111111111111111111111");

			// }else{

			wb = new HSSFWorkbook(excelfile);// 03版

			// }

			// 初始化工作表
			Sheet sheet = null; // excel工作表sheet
			Row row = null; // excel工作表行

			sheet = wb.getSheet("学生表"); // 获取xssfworkbook对象--以此得到sheet(工作表)对象：丙机房发射机备件定额
			// 第二步
			// 判断获取的工作表（丙机房发射机备件定额）是否为空
			// 使用工作表之前要检查行对象是否为null，否则会报空指针异常
			sheet = wb.getSheetAt(0);// 读取下标为0的表(也就是第一张表：sheet1)
			if (sheet == null) 
			{
				System.out.println("工作表0【默认名为 Sheet1 】为空");
				try 
				{
					throw new Exception("工作表为空！");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			// 第三步（在满足第二步的基础上进行）:获取一共的行数和列数
			// 行数
			int allRowNum = sheet.getLastRowNum(); // 获取一共的行数,因为从0行开始算起的，所以不+1就少一行

			// 列数
			row = sheet.getRow(0);
			// System.out.println("检测："+row); // 初始化: A1,B1,C1,,,M1

			int allCellNum1 = row.getLastCellNum();
			// int allCellNum2 = Row.getPhysicalNumberOfCells();

			// System.out.println("有效物理列："+allCellNum2);
			// 开始一行一行解析---一行解析出来的就是个数组----------全部就是一个list集合
			// 遍历行
			List<String> paras = new ArrayList<String>();
			Map<String, Object> dto = null;

			for (int j1 = row.getFirstCellNum(); j1 < allCellNum1; j1++) 
			{
				Cell cell1 = row.getCell(j1);// 获取当前行的单元格
				paras.add(cell1.toString());

			}

			for (int i2 = 1; i2 <= sheet.getLastRowNum(); i2++) 
			{
				dto = new HashMap<>();
				// 获取当前行
				row = sheet.getRow(i2);

				if (null == row) 
				{
					// row="";
					continue;
				}
				// 单元格列表

				for (int j1 = row.getFirstCellNum(); j1 < allCellNum1; j1++) 
				{
					Cell cell2 = row.getCell(j1);// 获取当前行的单元格
					dto.put(paras.get(j1).toString(), getValue(cell2));
				}
				dtos.add(dto);
			}

			System.out.println(dtos);
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		return dtos;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		doPost(request, response);
	}

	private String getValue(Cell hssfCell) 
	{
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) 
		{
			// 返回数值类型的值
			// hssfCell.getNumericCellValue()返回double类型，数目过大会变成科学进制
			return toVarchar(hssfCell.getNumericCellValue());
		} 
		else
		{
			// 返回字符串类型的值
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}

	private String toVarchar(double d) 
	{
		BigDecimal bigDecimal = new BigDecimal(d);
		return bigDecimal.toString();
	}

}