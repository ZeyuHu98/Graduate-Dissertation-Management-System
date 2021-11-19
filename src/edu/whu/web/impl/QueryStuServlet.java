package edu.whu.web.impl;

import edu.whu.web.support.StuControllerSupport;

public final class QueryStuServlet extends StuControllerSupport 
{
	@Override
	public String execute() throws Exception 
	{
		this.query();
		return "queryStu";
	}
}
