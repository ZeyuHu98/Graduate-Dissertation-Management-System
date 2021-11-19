package edu.whu.web.impl;

import java.util.List;
import java.util.Map;

import edu.whu.services.impl.B01ImplServices;
import edu.whu.web.support.BaseControllerSupport;

public class Exam_ThesisServlet extends BaseControllerSupport
{
	
	private B01ImplServices b01ImplServices=new B01ImplServices();
	
	public Exam_ThesisServlet() 
	{
		this.setServices(b01ImplServices);
	}
	
	@Override
	public String execute() throws Exception 
	{
		List<Map<String,String>> dataList=b01ImplServices.examQuery();
		if(dataList.size()>0) 
		{
			this.saveAttribute("dataList", dataList);
		}
		else
		{
			this.saveAttribute("msg", "没有符合条件的数据！");
		}
		return "examThesis";
	}
	
}
