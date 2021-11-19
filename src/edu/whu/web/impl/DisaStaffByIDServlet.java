package edu.whu.web.impl;

import edu.whu.web.support.StaffControllerSupport;

public class DisaStaffByIDServlet extends StaffControllerSupport {

	@Override
	public String execute() throws Exception {
		this.update("disaStaffByID", "½ûÓÃ");
		this.query();
		return "queryStaff";
	}

}
