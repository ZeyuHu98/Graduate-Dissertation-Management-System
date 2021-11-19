package edu.whu.services.impl;

import java.util.Map;

import edu.whu.services.support.JdbcServicesSupport;

public class B04ImplServices extends JdbcServicesSupport 
{
	public Map<String, String> queryByID() throws Exception
	{
    	StringBuilder sql=new StringBuilder()
    			.append("select x.user06,x.b402,x.b403,x.b408,b.fvalue b409,c.fvalue b410")
    			.append("  from syscode b, syscode c,b04 x")
    			.append(" where b.fcode=x.b409 and b.fname='b409'")
    			.append("   and c.fcode=x.b410 and c.fname='b410'")
    			.append("   and x.b101=?")
    			;
    	return this.queryForMap(sql.toString(), this.getFromDto("b101"));
	}
}
