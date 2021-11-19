package edu.whu.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.whu.services.support.JdbcServicesSupport;

public class InitServicesImpl extends JdbcServicesSupport
{
	//两状态init方法
	//1.答辩日期分配之后的判定方法
	public Map<String,List<String>> initQuery1() throws Exception 
	{
		String sql1="select rid from role";
		
		List<Map<String,String>> roleMapList=this.queryForList(sql1);
		Map<String,List<String>> rfmap=new HashMap<String,List<String>>();
		
		//select concat('<a href=\"',fentrance,'\">', fname,'</a>') from function;
		
		for(Map<String,String> m:roleMapList)
		{
			String sql2="select concat('<a href=\\\"',f.fentrance,'\\\">', f.fname,'</a>') result "
					+ "from function f, role r, r_f_relation rf "
					+ "where r.rid=rf.rid and rf.fid=f.fid and r.rid="+m.get("rid")+";";
			
			List<Map<String,String>> temp=this.queryForList(sql2);
			List<String> funcList=new ArrayList<String>();
			
			for(Map<String,String> m1:temp) 
			{
				funcList.add(m1.get("result"));
			}
			rfmap.put(m.get("rid"), funcList);
			
		}
		return rfmap;
	}
	
	//无时间安排
	public Map<String,List<String>> initQuery2() throws Exception 
	{
		String sql1="select rid from role";
		
		List<Map<String,String>> roleMapList=this.queryForList(sql1);
		Map<String,List<String>> rfmap=new HashMap<String,List<String>>();
		
		//select concat('<a href=\"',fentrance,'\">', fname,'</a>') from function;
		
		for(Map<String,String> m:roleMapList)
		{
			String sql2="select concat('<a href=\\\"',f.fentrance,'\\\">', f.fname,'</a>') result "
					+ "from function f, role r, r_f_relation rf "
					+ "where r.rid=rf.rid and rf.fid=f.fid and f.fid<>'03' and r.rid="+m.get("rid")+";";
			
			List<Map<String,String>> temp=this.queryForList(sql2);
			List<String> funcList=new ArrayList<String>();
			
			for(Map<String,String> m1:temp) 
			{
				funcList.add(m1.get("result"));
			}
			rfmap.put(m.get("rid"), funcList);
			
		}
		return rfmap;
	}
}
