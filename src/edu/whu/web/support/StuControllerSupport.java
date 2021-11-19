package edu.whu.web.support;

import edu.whu.services.impl.A01ServicesImpl;

public abstract class StuControllerSupport extends BaseControllerSupport
{
	public StuControllerSupport() 
	{
		this.setServices(new A01ServicesImpl());
	}
}
