package edu.whu.web.impl;

import edu.whu.web.support.DicExcelControllerSupport;

public class DicExcelQueryServlet extends DicExcelControllerSupport 
{
	@Override
	public String execute() throws Exception 
	{
		query();
		return "dicResult";
	}
}
