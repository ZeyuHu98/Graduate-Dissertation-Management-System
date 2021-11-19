package edu.whu.services.impl;

import edu.whu.services.support.JdbcServicesSupport;
import edu.whu.system.db.DBUtils;
import edu.whu.system.tools.Tools;

public class JudgeExcelServicesImpl extends JdbcServicesSupport 
{
	public void deletejudge() throws Exception
	{
		String sql = "delete from b02";
		// 2.编写参数数组
		
		this.executeUpdate(sql.toString());
	}
	public boolean judgethesis() throws Exception 
	{
		boolean tag = false;
		DBUtils.beginTransaction();
		try 
		{
			// 1.编写SQL语句
			StringBuilder sql = new StringBuilder()
					.append("insert into b02(uid,b101) ")
					.append("select user.uid,b01.b101 ")
					.append("from user,b01 ")
					.append("where user.name=? ")
					.append("and b01.b102=? ")
					;
			// 2.编写参数数组
			Object args[] = { 
			    this.getFromDto("ename"),
			    this.getFromDto("b102")
			};
			this.executeUpdate(sql.toString(), args);
			DBUtils.commit();
		} 
		catch (Exception ex) 
		{
			DBUtils.rollback();
			ex.printStackTrace();
		}
		finally
		{
			tag = true;
			DBUtils.endTransaction();
		}
		return tag;
	}
}
