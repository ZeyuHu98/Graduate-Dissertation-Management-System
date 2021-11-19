package edu.whu.services.impl;

import edu.whu.services.support.JdbcServicesSupport;

public class ChooseRoleServicesImpl extends JdbcServicesSupport{
	
	public boolean chooseRole(String[] list,String uid) throws Exception 
	{
		String sql1="delete from u_r_relation where uid="+uid;
		String sql2="insert into u_r_relation (uid,rid,u_r_state) values ("+uid+",?,'1')";
		this.executeUpdate(sql1);
		StringBuilder sb=new StringBuilder();
		for(String s:list)
		{
			this.executeUpdate(sql2, s);
			if(s.equals("4"))
			{
				String sql3="insert into a04 (uid,a401,a402) select a06.uid,a06.a602,a06.a603 from a06 where a06.uid="+uid;
				this.executeUpdate(sql3);
				sb.append("评审专家和");
			}
			else if(s.equals("5"))
			{
				String sql3="insert into a05 (uid,a501,a502,a503) select a06.uid,a06.a602,a06.a603,a06.a604 from a06 where uid="+uid;
				this.executeUpdate(sql3);
				sb.append("答辩专家和");
			}
		}
		String str=sb.toString().substring(0,sb.toString().lastIndexOf("和"));
		Object[] content=
			{
					"职责确定成功",
					"您已经成功确定在本次答辩流程中将要履行的职责："+str+"。"
			};
		this.sendMessage(uid, content);
		return true;
	}
	
}
