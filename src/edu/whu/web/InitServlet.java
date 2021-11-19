package edu.whu.web;

import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;

import edu.whu.services.impl.InitServicesImpl;

//用于初始化角色功能列表
@WebServlet(value="/init.html",loadOnStartup=0)
public class InitServlet extends BaseServlet {
	@Override
	public void init()
	{
		InitServicesImpl services=new InitServicesImpl();
		try {
			Map<String,List<String>> rfmap1=services.initQuery1();
			this.getServletContext().setAttribute("rfmap1", rfmap1);
			Map<String,List<String>> rfmap2=services.initQuery2();
			this.getServletContext().setAttribute("rfmap2", rfmap2);
			System.out.println(this.getServletContext().getAttribute("rfmap2"));
		} catch (Exception e) {
			System.out.println("init failed");
			e.printStackTrace();
		}
	}
}
