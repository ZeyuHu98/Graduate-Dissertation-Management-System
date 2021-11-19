package edu.whu.web.support;

import edu.whu.services.impl.B02ImplServices;

public abstract class B02ControllerSupport extends BaseControllerSupport
{
	public B02ControllerSupport()
	{
		this.setServices(new B02ImplServices());
	}
}
