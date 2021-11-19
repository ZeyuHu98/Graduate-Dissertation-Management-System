package edu.whu.web.impl;

import edu.whu.web.support.A01ControllerSupport;

public class Student_InfoServlet extends A01ControllerSupport
{

	@Override
	public String execute() throws Exception 
	{
		this.queryByID();
		return "studentInfo";
	}
	
}
