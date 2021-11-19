package edu.whu.services.impl;

import java.util.Map;

import edu.whu.services.support.JdbcServicesSupport;

public class ActivateServicesImpl extends JdbcServicesSupport 
{
	public Boolean activateAcount() throws Exception 
	{
		String uname=(String)this.getFromDto("flag");
		if(uname==null) 
		{
			return null;
		}
		
		String sql1="select uid,name,ustate from user where uname="+uname;
		Map<String,String> map=this.queryForMap(sql1);
		if(map.get("ustate").equals("1"))
		{
			return false;
		}
		String uid=map.get("uid");
		String sql2="update user set ustate= '1' where uname="+uname;
		this.executeUpdate(sql2);
		this.putIntoDto("name", map.get("name"));
		Object[] list= {
				"账号激活完成",
				"恭喜您成功激活本系统账户，您在本学院本次硕士论文评审答辩期间将全程由本系统为您提供帮助，请您定期上线查看通知。"
		};
		this.sendMessage(uid, list);
		return true;
	}
}
