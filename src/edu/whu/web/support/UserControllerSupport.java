package edu.whu.web.support;

import edu.whu.services.impl.UserImplServices;

public abstract class UserControllerSupport extends BaseControllerSupport 
{
	
	public UserControllerSupport()
	{
		this.setServices(new UserImplServices());
	}
}
