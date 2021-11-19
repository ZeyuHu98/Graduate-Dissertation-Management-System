package edu.whu.web.support;

import edu.whu.services.impl.DicExcelServicesImpl;

public abstract class DicExcelControllerSupport extends BaseControllerSupport
{
	public DicExcelControllerSupport() 
	{
		this.setServices(new DicExcelServicesImpl());
	}
}
