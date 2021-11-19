package edu.whu.services.impl;

import java.util.Map;

import edu.whu.services.support.JdbcServicesSupport;

public class A01ImplServices extends JdbcServicesSupport
{
	
	public Map<String, String> queryByID() throws Exception
	{
    	StringBuilder sql=new StringBuilder()
    			.append("select x.uid,x.uid2,x.uid3,x.a101,x.a102,")
    			.append("       x.a103,x.a104,x.b101,x.a105,u.`name`")
    			.append("  from a01 x,`user` u")
    			.append(" where x.uid=u.uid")
    			.append("   and x.uid=?")
    			;
    	
    	return this.queryForMap(sql.toString(), this.getFromDto("cuid"));
	}	
}
