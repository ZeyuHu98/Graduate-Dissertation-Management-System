package edu.whu.web.impl;

import edu.whu.web.support.StaffControllerSupport;

public class AbleStaffServlet extends StaffControllerSupport {

	@Override
	public String execute() throws Exception {
		this.update("ableStaff");
		this.query();
		return "queryStaff";
	}

}
