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
import edu.whu.system.db.DBUtils;
import edu.whu.web.BaseServlet;

@WebServlet(urlPatterns = "/financialExcel.xls",name="FinancialExcelServlet")
public class FinancialExcelServlet extends BaseServlet
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html;charset=utf-8");
		ResultSet rs=null;
        try
        {
        	OutputExcelServicesImpl servicesImpl=new OutputExcelServicesImpl();
        	//获得数据库连接
            Workbook wb=new HSSFWorkbook();
            //预先设定标题
            String headers[]={"学号","姓名","导师","姓名","职称","工作单位","酬金"};
            //得到结果集
            rs=servicesImpl.financialList();
            servicesImpl.fillExcelData(rs,wb,headers);
            export(response,wb,"财务报表.xls");
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
