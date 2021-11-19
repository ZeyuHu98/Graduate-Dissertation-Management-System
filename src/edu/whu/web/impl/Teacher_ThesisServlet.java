package edu.whu.web.impl;

import java.util.List;
import java.util.Map;

import edu.whu.services.impl.B01ImplServices;
import edu.whu.web.support.BaseControllerSupport;

public class Teacher_ThesisServlet extends BaseControllerSupport
{
	private B01ImplServices b01ImplServices = new B01ImplServices();
	
	public Teacher_ThesisServlet()
	{
		this.setServices(b01ImplServices);
	}
	@Override
	public String execute() throws Exception 
	{
		List<Map<String,String>> dataList=b01ImplServices.tequery();
		if(dataList.size()>0)
		{
			this.saveAttribute("dataList", dataList);
		}
		else 
		{
			this.saveAttribute("msg", "≤È—Ø ß∞‹");
		}
		return "teacherThesis";
	}

}
