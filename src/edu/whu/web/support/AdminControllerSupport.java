package edu.whu.web.support;

import edu.whu.services.impl.AdminServicesImpl;

public abstract class AdminControllerSupport extends BaseControllerSupport
{
	public AdminControllerSupport()
	{
		this.setServices(new AdminServicesImpl());
	}
}
