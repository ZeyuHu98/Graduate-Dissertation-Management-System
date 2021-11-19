package edu.whu.web.impl;

import edu.whu.web.support.UserControllerSupport;

public class Password_ChangeServlet extends UserControllerSupport 
{

	@Override
	public String execute() throws Exception 
	{
		this.queryByID();
		return "passwordChange";
	}

}
