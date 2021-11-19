package edu.whu.web.impl;

import edu.whu.web.support.AdminControllerSupport;

public final class SecDelByIdServlet extends AdminControllerSupport
{
	@Override
	public String execute()throws Exception
	{
		this.update("deleteById","É¾³ý");
		this.query();
		return "secManage";
	}
}