package edu.whu.web.support;

import java.util.Map;

public interface BaseController {
	//dto·â×°
	void setDto(Map<String,Object> dto);
	
    String execute()throws Exception;
    
    Map<String,Object> getAttribute();
}
