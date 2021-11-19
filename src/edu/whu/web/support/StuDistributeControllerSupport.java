package edu.whu.web.support;

import edu.whu.services.impl.StuDistributeServicesImpl;

public abstract class StuDistributeControllerSupport extends BaseControllerSupport 
{
	public StuDistributeControllerSupport()
	{
		this.setServices(new StuDistributeServicesImpl());
	}
}
