package edu.whu.web.impl;

import java.util.List;
import java.util.Map;

import edu.whu.services.impl.B01ImplServices;
import edu.whu.web.support.BaseControllerSupport;

public class StaB01Servlet extends BaseControllerSupport 
{
	private B01ImplServices B01ImplServices = new B01ImplServices();
	
	public StaB01Servlet() 
	{
		this.setServices(B01ImplServices);
	}
	
	@Override
	public String execute() throws Exception 
	{
		List<Map<String,String>> b107List=B01ImplServices.statib107();
		if(b107List.size()>0) 
		{
			this.saveAttribute("b107List", b107List);
		}
		else
		{
			this.saveAttribute("msg", "还没有检查结果！");
		}
		
		List<Map<String,String>> b108List=B01ImplServices.statib108();
		if(b108List.size()>0) 
		{
			this.saveAttribute("b108List", b108List);
		}
		else
		{
			this.saveAttribute("msg", "还没有评审结果！");
		}
		
		List<Map<String,String>> b109List=B01ImplServices.statib109();
		if(b109List.size()>0) 
		{
			this.saveAttribute("b109List", b109List);
		}
		else
		{
			this.saveAttribute("msg", "还没有答辩结果！");
		}
		
		return "statistics";
	}

}
