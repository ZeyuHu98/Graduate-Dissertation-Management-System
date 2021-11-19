package edu.whu.web.impl;

import edu.whu.web.support.AdminControllerSupport;

public class SecQueryServlet extends AdminControllerSupport
{
	@Override
	public String execute() throws Exception 
	{
		this.query();
		return "secManage";
	}
}
