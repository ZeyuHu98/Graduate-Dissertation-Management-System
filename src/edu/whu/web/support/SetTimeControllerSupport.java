package edu.whu.web.support;

import edu.whu.services.impl.SetTimeServicesImpl;


public abstract class SetTimeControllerSupport extends BaseControllerSupport 
{
	public SetTimeControllerSupport() throws Exception 
	{
		
		this.setServices(new SetTimeServicesImpl());
	}

}
