package edu.whu.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.whu.services.support.JdbcServicesSupport;

public class TimeManageImpl extends JdbcServicesSupport 
{
	  public List<Map<String,String>> query()throws Exception
	  {	  	
		    List<Map<String,String>> Lists = new ArrayList<Map<String,String>>();
	  		//定义SQL主体
	  		String sql = "SELECT a1.a201, b.name as name1 from a02 a1,user b where a1.uid=b.uid";
	  		Lists.addAll(this.queryForList(sql.toString()));
	  		String sql2 = "SELECT a1.a301, b.name as name2 from a03 a1,user b where a1.uid=b.uid";
	  		Lists.addAll(this.queryForList(sql2.toString()));
	  		String sql3 = "SELECT a1.a504, b.name as name3 from a05 a1,user b where a1.uid=b.uid";
	  		Lists.addAll(this.queryForList(sql3.toString()));
	  		return Lists;
	  }
    
}