package edu.whu.web.impl;

import edu.whu.web.support.StuControllerSupport;

public class DisaStuByIDServlet extends StuControllerSupport {

	@Override
	public String execute() throws Exception {
		this.update("disableStuByID", "½ûÓÃ");
		this.query();
		return "queryStu";
	}

}
