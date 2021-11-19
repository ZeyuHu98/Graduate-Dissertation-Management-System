package edu.whu.web.impl;

import edu.whu.web.support.StaffControllerSupport;

public class DelStaffByIDServlet extends StaffControllerSupport {

	@Override
	public String execute() throws Exception {
		this.update("deleteByID","É¾³ý");
		this.query();
		return "queryStaff";
	}

}
