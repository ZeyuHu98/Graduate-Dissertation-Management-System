package edu.whu.web.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import edu.whu.services.impl.B01ImplServices;

@WebServlet("/Thesis_Resubmit.com")
public class Thesis_ResubmitServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	// 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "upload";
 
    // 上传配置
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB

    /**
           * 重交论文
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		// 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
 
        ServletFileUpload upload = new ServletFileUpload(factory);
         
        // 设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);
         
        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);
        
        // 中文处理
        upload.setHeaderEncoding("GBK"); 

        // 构造临时路径来存储上传的文件
        // 这个路径相对当前应用的目录
        String uploadPath = getServletContext().getRealPath("/") + UPLOAD_DIRECTORY;
              
        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) 
        {
            uploadDir.mkdir();
        }
        
        B01ImplServices b01ImplServices = new B01ImplServices();
 
        Map<String, Object> dto=new HashMap<>();
        
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        try 
        {

            // 解析请求的内容提取文件数据
            List<FileItem> formItems = upload.parseRequest(request);
 
            if (formItems != null && formItems.size() > 0) 
            {
                // 迭代表单数据
                for (FileItem item : formItems)
                {
                    // 处理不是file表单中的字段
                    if (!item.isFormField()) 
                    {
                        //String fileName = new File(item.getName()).getName();
                    	String newFileName = uuid + ".pdf";
                        String newFilePath = uploadPath + File.separator + newFileName;
                        File storeFile = new File(newFilePath);
                        // 在控制台输出文件的上传路径
                        System.out.println("上传成功，路径为("+newFilePath+")");
                        // 保存文件到硬盘
                        item.write(storeFile);
                        // 把file input的name和文件名添加到dto      
                        dto.put(item.getFieldName().toString(), newFileName);
                    }
                    // 处理文本框的数据
                    else 
                    {
                    	// 把控件的name和value添加到dto
                    	dto.put(item.getFieldName().toString(),item.getString(request.getCharacterEncoding()));
					}
                }
            }
            
            /**
             * ------------------------删除文件操作----------------------
             */
        	//查询论文表
        	Map<String, String> thesis=new HashMap<>();
        	thesis=b01ImplServices.queryByID(dto);
        	//得到文件名	
        	String fileName=thesis.get("b106").toString();
        	//组成文件路径
        	String filePath=uploadPath + File.separator + fileName;
        	//删除文件
        	File file=new File(filePath);
        	System.out.println(filePath);
        	if(file.exists()&&file.isFile())
        	{
        		if(file.delete())
        		{
        			System.out.println("删除成功，路径为("+filePath+")");
        		}
        		else 
        		{
					System.out.println("删除失败");
					request.setAttribute("message","文件删除失败!");
					request.getRequestDispatcher("studentUpload.jsp").forward(request, response);
					return;
				}
        	}

            /**
             * ------------------------修改论文表操作----------------------
             */
            Map<String,String> dataMap=b01ImplServices.modify(dto);
    		if(dataMap!=null)
    		{
    			request.setAttribute("msg","文件上传成功!");
    			request.setAttribute("dataMap",dataMap);
    		}
    		else 
    		{
    			request.setAttribute("msg","文件上传失败!");
    		}
        } 
        catch (Exception ex) 
        {
            request.setAttribute("message","错误信息: " + ex.getMessage());
            ex.printStackTrace();
        }
        request.getRequestDispatcher("studentUpload.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		doGet(request, response);
	}
}
