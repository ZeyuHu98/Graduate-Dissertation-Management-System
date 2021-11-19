package edu.whu.web.impl;

import edu.whu.web.support.StuControllerSupport;

public class AbleStuServlet extends StuControllerSupport {

	@Override
	public String execute() throws Exception {
		this.update("ableStu","∆Ù”√");
		this.query();
		return "queryStu";
	}

}
