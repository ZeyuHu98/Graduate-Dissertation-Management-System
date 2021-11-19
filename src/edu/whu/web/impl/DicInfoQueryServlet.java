package edu.whu.web.impl;

import edu.whu.web.support.ExpertInfoControllerSupport;

public class DicInfoQueryServlet extends ExpertInfoControllerSupport
{

	@Override
	public String execute() throws Exception 
	{
		this.query();
		return "dicInfoQuery";
	}

}
