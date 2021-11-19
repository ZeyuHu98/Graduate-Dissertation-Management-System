package edu.whu.services.impl;

import java.util.Map;

import edu.whu.services.support.JdbcServicesSupport;
import edu.whu.system.tools.Tools;

public class UserImplServices extends JdbcServicesSupport
{
	/**
	 * 修改密码
	 * @return
	 * @throws Exception
	 */
	public boolean modify() throws Exception
	{
		StringBuilder comfirm=new StringBuilder()
				.append("select uid,upassword")
				.append("  from `user`")
				.append(" where uid=? and upassword=?")
				;
		Object args1[]= {
				this.getFromDto("cuid"),
				Tools.getMd5(this.getFromDto("upassword1"))
				};
		
		//原密码不正确返回false
		if(this.queryForMap(comfirm.toString(), args1)==null)
		{
			return false;
		}
		
		StringBuilder sql=new StringBuilder()
				.append("update `user`")
				.append("   set upassword=?")
				.append(" where uid=?")
				;
		Object args2[]= {
				Tools.getMd5(this.getFromDto("upassword2")),
				this.getFromDto("cuid")
		};
		return this.executeUpdate(sql.toString(), args2)>0;
	}
	
	public Map<String, String> queryByID() throws Exception
	{
    	StringBuilder sql=new StringBuilder()
    			.append("select uid,uname,upassword,name")
    			.append("  from user")
    			.append(" where uid=?")
    			;

    	return this.queryForMap(sql.toString(), this.getFromDto("cuid"));
	}
	
}
