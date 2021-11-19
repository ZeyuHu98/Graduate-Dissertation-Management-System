package edu.whu.web.impl;

import edu.whu.web.support.StaffControllerSupport;

public class DelStaffServlet extends StaffControllerSupport {

	@Override
	public String execute() throws Exception {
		this.update("batchDelete","É¾³ý");
		this.query();
		return "queryStaff";
	}

}
