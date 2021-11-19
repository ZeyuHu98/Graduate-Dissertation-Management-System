package edu.whu.web.support;

import edu.whu.services.impl.B01ImplServices;

public abstract class B01ControllerSupport extends BaseControllerSupport
{

	public B01ControllerSupport() 
	{
		this.setServices(new B01ImplServices());
	}
}
