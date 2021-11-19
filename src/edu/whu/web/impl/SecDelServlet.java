package edu.whu.web.impl;

import edu.whu.web.support.AdminControllerSupport;

public final class SecDelServlet extends AdminControllerSupport
{
	@Override
	public String execute()throws Exception
	{
		this.update("batchDelete","É¾³ý");
		this.query();
		return "secManage";
	}
}