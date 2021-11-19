package edu.whu.web.support;

import edu.whu.services.impl.JudgeExcelServicesImpl;

public abstract class JudgeExcelControllerSupport extends BaseControllerSupport
{
	public JudgeExcelControllerSupport() 
	{
		this.setServices(new JudgeExcelServicesImpl());
	}
}
