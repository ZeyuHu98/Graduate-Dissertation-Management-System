package edu.whu.web.impl;

import edu.whu.web.support.JudgeExcelControllerSupport;

public class ExcelJudgeServlet extends JudgeExcelControllerSupport 
{
	@Override
	public String execute() throws Exception 
	{
		this.update("judgethesis");
		return "judgeThesis";
	}
}
