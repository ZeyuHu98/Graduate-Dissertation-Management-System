package edu.whu.web.support;

import edu.whu.services.impl.A06ServicesImpl;
import edu.whu.services.impl.DicExcelServicesImpl;
import edu.whu.services.impl.JudgeExcelServicesImpl;
import edu.whu.services.impl.QueryJudgeServicesImpl;

public abstract class QueryJudgeControllerSupport extends BaseControllerSupport
{
	public QueryJudgeControllerSupport() 
	{
		this.setServices(new QueryJudgeServicesImpl());
	}
}
