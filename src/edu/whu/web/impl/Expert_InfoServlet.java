package edu.whu.web.impl;

import edu.whu.web.support.A06ControllerSupport;

public class Expert_InfoServlet extends A06ControllerSupport 
{

	@Override
	public String execute() throws Exception 
	{
		this.queryByID();
		return "staffInfo";
	}

}
