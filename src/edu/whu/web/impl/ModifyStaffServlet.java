package edu.whu.web.impl;

import edu.whu.web.support.StaffControllerSupport;

public class ModifyStaffServlet extends StaffControllerSupport {

	@Override
	public String execute() throws Exception
	{
		this.update("modifyStaff", "ÐÞ¸Ä");
		this.query();
		return "queryStaff";
	}

}
