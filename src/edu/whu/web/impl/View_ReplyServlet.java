package edu.whu.web.impl;

import edu.whu.web.support.B04ControllerSupport;

public class View_ReplyServlet extends B04ControllerSupport
{

	@Override
	public String execute() throws Exception 
	{
		this.queryByID();
		return "previewInfo";
	}

}
