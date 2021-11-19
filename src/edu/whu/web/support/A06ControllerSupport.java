package edu.whu.web.support;

import edu.whu.services.impl.A06ImplServices;

public abstract class A06ControllerSupport extends BaseControllerSupport 
{
	
	public A06ControllerSupport() 
	{
		this.setServices(new A06ImplServices());
	}
}
