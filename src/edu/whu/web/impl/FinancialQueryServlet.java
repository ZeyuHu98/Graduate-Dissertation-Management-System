package edu.whu.web.impl;

import edu.whu.web.support.FinancialControllerSupport;

public class FinancialQueryServlet extends FinancialControllerSupport
{
	@Override
	public String execute() throws Exception 
	{
		this.query();
		return "outputExcel";
	}
}
