package edu.whu.web.support;

import edu.whu.services.impl.ExpExcelServiceImpl;

public abstract class ExpExcelControllerSupport extends BaseControllerSupport
{
	public ExpExcelControllerSupport() 
	{
		this.setServices(new ExpExcelServiceImpl());
	}
}
