package edu.whu.web.impl;

import edu.whu.web.support.B01ControllerSupport;

public class Sec_ThesisServlet extends B01ControllerSupport
{

	@Override
	public String execute() throws Exception
	{
		this.query();
		return "secThesis";
	}

}
