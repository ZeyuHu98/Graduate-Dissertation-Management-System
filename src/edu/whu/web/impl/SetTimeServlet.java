package edu.whu.web.impl;

import edu.whu.web.support.SetTimeControllerSupport;

public class SetTimeServlet extends SetTimeControllerSupport 
{

	public SetTimeServlet() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String execute() throws Exception 
	{
		this.update("settime");
		return "setTime";
	}

}

