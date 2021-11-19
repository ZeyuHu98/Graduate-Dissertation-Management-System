package edu.whu.web;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import edu.whu.services.impl.OutputExcelServicesImpl;
import edu.whu.services.impl.StuDistributeServicesImpl;
import edu.whu.system.db.DBUtils;
import edu.whu.web.BaseServlet;

@WebServlet(urlPatterns = "/stuDistribute.xls",name="StuDistributeExcelServlet")
public class StuDistributeExcelServlet extends BaseServlet
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html;charset=utf-8");
		ResultSet rs=null;
        try
        {
        	StuDistributeServicesImpl servicesImpl=new StuDistributeServicesImpl();
        	//获得数据库连接
            Workbook wb=new HSSFWorkbook();
            //预先设定标题
            String headers[]={"序号","秘书","导师","学生姓名","学生学号"};
            //得到结果集
            rs=servicesImpl.outPutConResult();
            servicesImpl.fillExcelData(rs,wb,headers);
            export(response,wb,"评审分配.xls");
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        finally
        {
        	DBUtils.close(rs);
        }
	}

    /**
     * 把数据放入到.xls文件中并下载到本地
     * 
     * @param response
     * @param wb
     * @param fileName
     * @throws Exception
     */
    public void export(HttpServletResponse response,Workbook wb,String fileName)throws Exception 
    {
    	//设置头信息
        response.setHeader("Content-Disposition", "attachment;filename="
                + new String(fileName.getBytes("utf-8"), "iso8859-1"));
        response.setContentType("application/ynd.ms-excel;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        //进行输出，下载到本地
        wb.write(out);
        out.flush();
        out.close();
    }
	
}
