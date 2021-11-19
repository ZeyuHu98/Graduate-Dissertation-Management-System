package edu.whu.web.support;

import edu.whu.services.impl.B04ImplServices;

public abstract class B04ControllerSupport extends BaseControllerSupport 
{
	public B04ControllerSupport()
	{
		this.setServices(new B04ImplServices());
	}
}
