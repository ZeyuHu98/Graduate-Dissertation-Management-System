package edu.whu.services.impl;

import java.util.List;
import java.util.Map;

import edu.whu.services.support.JdbcServicesSupport;

public class B02ImplServices extends JdbcServicesSupport 
{
	public Map<String, String> queryByID() throws Exception
	{
    	StringBuilder sql=new StringBuilder()
    			.append("select a.fvalue b202,x.b203")
    			.append("  from syscode a,b02 x")
    			.append(" where x.b202=a.fcode and a.fname='b202'")
    			.append("   and x.b101=?")
    			;

    	return this.queryForMap(sql.toString(), this.getFromDto("b101"));
	}
	
	/**
	  * 查询评审专家的所有论文id
	 * @param objects
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> query(Object[] objects) throws Exception
	{
		StringBuilder sql=new StringBuilder()
				.append("select b101")
				.append("  from b02")
				.append(" where uid=?")
				;
		
		
		return this.queryForList(sql.toString(), objects);	
	}
	
	public Map<String, String> query(Object object) throws Exception
	{
		StringBuilder sql=new StringBuilder()
				.append("select b101,uid,b202,b203")
				.append("  from b02")
				.append(" where b101=?")
				;
		
		
		return this.queryForMap(sql.toString(), object);	
	}
	
	/**
	 * 评审专家对论文给出评审
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> add() throws Exception
	{
		StringBuilder sql=new StringBuilder()
				.append("insert into b02(b101,uid,b202,b203)")
				.append("         values(?,?,?,?)")
				;
		Object b202=this.getFromDto("b202");
		if(!this.isNotNull(b202))
		{
			b202=4;
		}
    	Object args[]={
    			this.getFromDto("b101"),
    			this.getFromDto("uid"),
    			b202,
    			this.getFromDto("b203")
    	};
    	
    	this.executeUpdate(sql.toString(), args);
    	
    	B01ImplServices b01ImplServices = new B01ImplServices();
    	  	
		return b01ImplServices.queryByID(this.getFromDto("b101"));
	}
	
	/**
	 * 评审专家对论文做出评审修改
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> modify() throws Exception
	{
		StringBuilder sql=new StringBuilder()
				.append("update b02")
				.append("   set b202=?,b203=?")
				.append(" where b101=?")
				;
		Object b202=this.getFromDto("b202");
		if(!this.isNotNull(b202))
		{
			b202=4;
		}
		Object args[]= {
				b202,
				this.getFromDto("b203"),
				this.getFromDto("b101")
		};
		
    	this.executeUpdate(sql.toString(), args);
    	
    	B01ImplServices b01ImplServices = new B01ImplServices();
    	  	
		return b01ImplServices.queryByID(this.getFromDto("b101"));
	}
	
	/**
	 * 统计评审等级
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,String>> statib202() throws Exception
	{
		StringBuilder b202=new StringBuilder()
				.append("  select a.fvalue b202,count(x.b202) count")
//				.append("  select any_value(a.fvalue) b202,count(x.b202) count")
				.append("    from syscode a,b02 x")
				.append("   where x.b202=a.fcode and a.fname='b202'")
				.append("group by b202")
				;
		return this.queryForList(b202.toString());
	}
}
