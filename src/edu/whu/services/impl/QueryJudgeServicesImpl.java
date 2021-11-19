package edu.whu.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.whu.services.support.JdbcServicesSupport;
import edu.whu.system.db.DBUtils;
import edu.whu.system.tools.Tools;

public class QueryJudgeServicesImpl extends JdbcServicesSupport 
{
	  public List<Map<String,String>> query()throws Exception
	  {
	  		//还原页面查询条件
	  		Object name=this.getFromDto("qename");    
    
	  		
	  		//定义SQL主体
	  		StringBuilder sql=new StringBuilder()
					.append("select user.name,b01.b102 ")
					.append("from user,b01,b02 ")
					.append("where user.uid=b02.uid ")
					.append("and b01.b101=b02.b101 ")
	  				;
	  		
	  		//参数列表
	  		List<Object> paramList=new ArrayList<>();
	  		//逐一判断查询条件是否录入,拼接AND条件
	  		if(this.isNotNull(name))
	  		{
	  			sql.append(" and user.name like ?");
	  			paramList.add("%"+name+"%");
	  		}

	  		return this.queryForList(sql.toString(), paramList.toArray());
	  }
}    
