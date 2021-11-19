package edu.whu.web.impl;

import edu.whu.web.support.StuControllerSupport;

public class DelStuServlet extends StuControllerSupport {

	@Override
	public String execute() throws Exception {
		this.update("deleteStu", "É¾³ý");
		this.query();
		return "queryStu";
	}
}
