package edu.whu.web.impl;

import edu.whu.web.support.TimeControllerSupport;

public final class TimeManageServlet extends TimeControllerSupport 
{
	public TimeManageServlet() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute() throws Exception 
	{
		this.query();
		return "showTime";
	}
}
