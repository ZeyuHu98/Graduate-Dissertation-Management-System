package edu.whu.web.impl;

import edu.whu.web.support.StaffControllerSupport;

public class AddStaffServlet extends StaffControllerSupport 
{

	@Override
	public String execute() throws Exception 
	{
		this.update("addStaff", "添加", "添加成功,该用户编号是:", "username");
		return "addStaff";
	}

}
