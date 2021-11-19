package edu.whu.web.support;

import edu.whu.services.impl.A01ImplServices;

public abstract class A01ControllerSupport extends BaseControllerSupport
{
	
	public A01ControllerSupport() 
	{
		this.setServices(new A01ImplServices());
	}
}
