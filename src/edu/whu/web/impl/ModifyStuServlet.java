package edu.whu.web.impl;

import edu.whu.web.support.StuControllerSupport;

public class ModifyStuServlet extends StuControllerSupport {

	@Override
	public String execute() throws Exception
	{
		this.update("modifyStu", "ÐÞ¸Ä");
		return "queryStu";
	}

}
