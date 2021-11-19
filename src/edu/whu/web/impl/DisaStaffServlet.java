package edu.whu.web.impl;

import edu.whu.web.support.StaffControllerSupport;

public class DisaStaffServlet extends StaffControllerSupport {

	@Override
	public String execute() throws Exception {
		this.update("disaStaff","½ûÓÃ");
		this.query();
		return "queryStaff";
	}

}
