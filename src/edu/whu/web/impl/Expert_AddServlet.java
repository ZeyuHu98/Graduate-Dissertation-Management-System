package edu.whu.web.impl;

import java.util.Map;

import edu.whu.services.impl.B02ImplServices;
import edu.whu.web.support.BaseControllerSupport;

public class Expert_AddServlet extends BaseControllerSupport 
{
    private B02ImplServices B02ImplServices = new B02ImplServices();
    
    public Expert_AddServlet()
    {
		this.setServices(B02ImplServices);
	}
    
	@Override
	public String execute() throws Exception 
	{
		Map<String,String> dataMap=B02ImplServices.add();
		if(dataMap!=null) 
		{
			this.saveAttribute("dataMap", dataMap);
		}
		else
		{
			this.saveAttribute("msg", "没有符合条件的数据！");
		}
		return "thesisDetails_e";
	}

}
