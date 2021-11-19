package edu.whu.web.impl;

import edu.whu.web.support.StaffControllerSupport;

public final class QueryStaffServlet extends StaffControllerSupport 
{
	@Override
	public String execute() throws Exception 
	{
		this.query();
		return "queryStaff";
	}
}
