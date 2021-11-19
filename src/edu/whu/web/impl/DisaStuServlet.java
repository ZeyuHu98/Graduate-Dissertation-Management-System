package edu.whu.web.impl;

import edu.whu.web.support.StuControllerSupport;

public class DisaStuServlet extends StuControllerSupport {
	@Override
	public String execute() throws Exception {
		this.update("disableStu","½ûÓÃ");
		this.query();
		return "queryStu";
	}

}
