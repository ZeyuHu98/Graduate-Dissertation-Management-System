package edu.whu.web.support;

import edu.whu.services.impl.TimeManageImpl;

public abstract class TimeControllerSupport extends BaseControllerSupport 
{
	public TimeControllerSupport() throws Exception 
	{
		
		this.setServices(new TimeManageImpl());
	}

}
