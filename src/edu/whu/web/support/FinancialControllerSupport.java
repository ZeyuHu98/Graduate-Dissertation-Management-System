package edu.whu.web.support;

import edu.whu.services.impl.OutputExcelServicesImpl;

public abstract class FinancialControllerSupport extends BaseControllerSupport
{
	public FinancialControllerSupport()
	{
		this.setServices(new OutputExcelServicesImpl());
	}
}
