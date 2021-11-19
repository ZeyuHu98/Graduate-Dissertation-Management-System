package edu.whu.web.impl;

import edu.whu.web.support.StuControllerSupport;

public class AddStuServlet extends StuControllerSupport 
{

	@Override
	public String execute() throws Exception 
	{
		this.update("addStu", "添加", "添加成功,该学生编号是:", "uid");
		return "addStu";
	}

}
