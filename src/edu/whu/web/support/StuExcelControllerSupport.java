package edu.whu.web.support;

import edu.whu.services.impl.StuExcelServiceImpl;

public abstract class StuExcelControllerSupport extends BaseControllerSupport
{
	public StuExcelControllerSupport() 
	{
		this.setServices(new StuExcelServiceImpl());
	}
}
