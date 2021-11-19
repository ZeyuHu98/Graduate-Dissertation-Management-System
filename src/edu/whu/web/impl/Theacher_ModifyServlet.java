package edu.whu.web.impl;

import java.util.Map;

import edu.whu.services.impl.B01ImplServices;
import edu.whu.web.support.BaseControllerSupport;

public class Theacher_ModifyServlet extends BaseControllerSupport 
{
	private B01ImplServices B01ImplServices=new B01ImplServices();
	
	public Theacher_ModifyServlet() 
	{
		this.setServices(B01ImplServices);
	}

	@Override
	public String execute() throws Exception
	{
		Map<String,String> dataMap=B01ImplServices.teaModify();
		if(dataMap!=null) 
		{
			this.saveAttribute("dataMap", dataMap);
		}
		else
		{
			this.saveAttribute("msg", "没有符合条件的数据！");
		}
		return "thesisDetails_t";
	}

}
