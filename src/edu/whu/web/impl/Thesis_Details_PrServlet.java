package edu.whu.web.impl;

import edu.whu.web.support.B01ControllerSupport;

public class Thesis_Details_PrServlet extends B01ControllerSupport 
{

	@Override
	public String execute() throws Exception 
	{
		this.queryByID();
		return "thesisDetails_p";
	}

}
