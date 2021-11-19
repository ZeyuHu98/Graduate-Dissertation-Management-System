package edu.whu.web.impl;

import edu.whu.web.support.StuControllerSupport;

public class FindStuByIDServlet extends StuControllerSupport 
{

	@Override
	public String execute() throws Exception 
	{
		this.queryByID();
		return "addStu";
	}

}
