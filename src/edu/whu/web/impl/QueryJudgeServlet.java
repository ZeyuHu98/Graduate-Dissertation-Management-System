package edu.whu.web.impl;

import edu.whu.web.support.QueryJudgeControllerSupport;

public final class QueryJudgeServlet extends QueryJudgeControllerSupport 
{
	@Override
	public String execute() throws Exception 
	{
		this.query();
		return "judgeThesis";
	}
}
