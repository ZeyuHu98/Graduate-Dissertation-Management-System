package edu.whu.web.support;

import edu.whu.services.impl.A06ServicesImpl;

public abstract class StaffControllerSupport extends BaseControllerSupport
{
	public StaffControllerSupport() 
	{
		this.setServices(new A06ServicesImpl());
	}
}
