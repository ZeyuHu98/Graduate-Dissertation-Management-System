package edu.whu.services.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.whu.services.support.JdbcServicesSupport;

public class MessageServicesImpl extends JdbcServicesSupport 
{
	
	public Map<String,List<String>> getMessage(String uid) throws Exception
	{
		Map<String,List<String>> map=new HashMap<String,List<String>>();
		String sql="select b501,b502,CONCAT('<a href=\"message.jsp?index=',b501,'\">',b502,'</a>') atext, b503,b504,b505 from b05 where uid="+uid;
		List<Map<String,String>> list=this.queryForList(sql);
		for(Map<String,String> m:list)
		{
			List<String> temp=new ArrayList<String>();
			//标签
			temp.add(m.get("atext"));//0
			//时间
			temp.add(m.get("b504").substring(0, m.get("b504").lastIndexOf(".")));//1
			//标题
			temp.add(m.get("b502"));//2
			//内容
			temp.add(m.get("b503"));//3
			//是否已读
			temp.add(m.get("b505"));//4
			//id
			map.put(m.get("b501"), temp);//5
		}
		return map;
	}
	

	public boolean readMessage(String b501) throws Exception
	{
		String sql="update b05 set b505='1' where b501="+b501;
		return this.executeUpdate(sql)>0;
	}
	

}
