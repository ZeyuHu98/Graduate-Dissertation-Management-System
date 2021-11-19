package edu.whu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.whu.services.impl.MessageServicesImpl;
@WebServlet(urlPatterns = "/readMessage",name="ReadMessageServlet")
public class ReadMessageServlet extends BaseServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		MessageServicesImpl services=new MessageServicesImpl();
		try 
		{
			if(services.readMessage(request.getParameter("index"))==true)
			{
			}
			this.getMessage(request);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		String index=(String)request.getParameter("index");
		System.out.println("index="+index);
		
		request.getRequestDispatcher("/message.jsp?index="+index).forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		this.doGet(request, response);
	}
}
