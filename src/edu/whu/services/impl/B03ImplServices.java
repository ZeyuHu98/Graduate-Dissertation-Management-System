package edu.whu.services.impl;

import java.util.List;
import java.util.Map;

import edu.whu.services.support.JdbcServicesSupport;

public class B03ImplServices extends JdbcServicesSupport 
{
	/**
	  * 查询答辩专家的所有论文id
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> query(Object object) throws Exception
	{
		StringBuilder sql=new StringBuilder()
				.append("select b101")
				.append("  from b03")
				.append(" where uid=?")
				;
		
		Object objects[]= {object};
		
		return this.queryForList(sql.toString(), objects);	
	}
}
