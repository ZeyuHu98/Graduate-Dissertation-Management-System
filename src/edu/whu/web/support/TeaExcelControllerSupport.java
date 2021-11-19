package edu.whu.web.support;

import edu.whu.services.impl.TeaExcelServiceImpl;

public abstract class TeaExcelControllerSupport extends BaseControllerSupport
{
	public TeaExcelControllerSupport() 
	{
		this.setServices(new TeaExcelServiceImpl());
	}
}
