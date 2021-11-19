package edu.whu.web.impl;

import edu.whu.web.support.StaffControllerSupport;

public class FindStaffByIDServlet extends StaffControllerSupport 
{

	@Override
	public String execute() throws Exception 
	{
		this.queryByID();
		return "addStaff";
	}

}
