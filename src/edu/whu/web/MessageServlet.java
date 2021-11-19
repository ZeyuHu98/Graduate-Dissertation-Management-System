package edu.whu.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = "/message",name="MessageServlet")
public class MessageServlet extends BaseServlet {
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException
	{
		try
		{
			this.getMessage(request);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			request.setAttribute("msg", "Õ¯¬Áπ ’œ!");
		}
		request.getRequestDispatcher("main.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException
	{
		this.doGet(request, response);
	}
}
