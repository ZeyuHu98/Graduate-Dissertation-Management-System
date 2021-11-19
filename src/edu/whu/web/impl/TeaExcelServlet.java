package edu.whu.web.impl;

import edu.whu.web.support.TeaExcelControllerSupport;

public class TeaExcelServlet extends TeaExcelControllerSupport 
{

	@Override
	public String execute() throws Exception 
	{
		this.update("addEmp", "添加", "添加成功,该员工编号是:", "uid");
		return "queryTea";
	}

}
