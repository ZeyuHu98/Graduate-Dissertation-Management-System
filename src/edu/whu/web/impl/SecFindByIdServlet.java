package edu.whu.web.impl;

import edu.whu.web.support.AdminControllerSupport;

public class SecFindByIdServlet extends AdminControllerSupport
{
	@Override
	public String execute() throws Exception 
	{
		this.queryByID();
		this.query();
		return "secAddManage";
	}
}