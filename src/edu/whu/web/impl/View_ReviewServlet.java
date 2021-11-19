package edu.whu.web.impl;

import edu.whu.web.support.B02ControllerSupport;

public class View_ReviewServlet extends B02ControllerSupport 
{

	@Override
	public String execute() throws Exception 
	{
		this.queryByID();
		return "examInfo";
	}

}
