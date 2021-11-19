package edu.whu.web.support;

import edu.whu.services.impl.ExpertInfoServicesImpl;

public abstract class ExpertInfoControllerSupport extends BaseControllerSupport
{
	public ExpertInfoControllerSupport()
	{
		this.setServices(new ExpertInfoServicesImpl());
	}
}