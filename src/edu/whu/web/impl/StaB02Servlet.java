package edu.whu.web.impl;

import java.util.List;
import java.util.Map;

import edu.whu.services.impl.B02ImplServices;
import edu.whu.web.support.BaseControllerSupport;

public class StaB02Servlet extends BaseControllerSupport 
{
	private B02ImplServices B02ImplServices = new B02ImplServices();
	
	public StaB02Servlet() 
	{
		this.setServices(B02ImplServices);
	}
	
	@Override
	public String execute() throws Exception 
	{
		List<Map<String,String>> b202List=B02ImplServices.statib202();
		if(b202List.size()>0) 
		{
			this.saveAttribute("b202List", b202List);
		}
		else
		{
			this.saveAttribute("msg", "还没有检查结果！");
		}
		return "stab02";
	}

}
