package edu.whu.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.whu.services.support.JdbcServicesSupport;
import edu.whu.system.db.DBUtils;
import edu.whu.system.tools.Tools;

public class A01ServicesImpl extends JdbcServicesSupport {

	public boolean modifyStu() throws Exception 
	{
		StringBuilder sql = new StringBuilder().append("update a01 a, user b")
				.append("   set a.a101=?,a.a102=?,a.a103=?,a.a104=?,b.name=?").append(" where a.uid=b.uid")
				.append("   and a.uid=?");
		Object args[] = { this.getFromDto("a101"), this.getFromDto("a102"), this.getFromDto("a103"),
				this.getFromDto("a104"), this.getFromDto("name"), this.getFromDto("uid") };
		return this.executeUpdate(sql.toString(), args) > 0;
	}

	public boolean addStu() throws Exception 
	{
		String username = Tools.getFormatNumber("1", "Stu");
		boolean tag = false;
		// 向DTO添加员工编号
		DBUtils.beginTransaction();
		try 
		{
			String sql2 = "insert into user(uname,upassword,name,ustate) values(?,?,?,?)";

			Object args2[] = { username, Tools.getMd5(username), this.getFromDto("name"), "1" };
			this.executeUpdate(sql2.toString(), args2);
			String sql3 = "insert into u_r_relation(uid,rid,u_r_state) select user.uid,?,? from user where user.uname=?";
			Object args3[] = { "1", "1", username };
			this.executeUpdate(sql3.toString(), args3);

			// 1.编写SQL语句
			StringBuilder sql = new StringBuilder()
					.append("insert into a01(uid,uid2,a101,a102,a103,a104)")
					.append("select user.uid,?,?,?,?,?")
					.append(" from user ")
					.append("where user.uname=?");
			// 2.编写参数数组
			
			//根据导师姓名查询导师id
			String sql4="select uid from user where name=?";
			Map<String,String> map=this.queryForMap(sql4,this.getFromDto("name2"));

			Integer uid2=Integer.parseInt(map.get("uid"));
			Object args[] = { uid2, this.getFromDto("a101"),
					this.getFromDto("a102"), this.getFromDto("a103"), this.getFromDto("a104"), username };
			this.executeUpdate(sql.toString(), args);
			tag = true;
			DBUtils.commit();
		} 
		catch (Exception ex) 
		{
			DBUtils.rollback();
			ex.printStackTrace();
		}
		finally 
		{
			DBUtils.endTransaction();
		}
		return tag;
	}

	public Map<String, String> queryByID() throws Exception 
	{
		// 1.编写SQL语句
		StringBuilder sql = new StringBuilder().append("select b.name,a.uid,a.uid2,a.a101,a.a102,a.a103,a.a104")
				.append("  from a01 a,user b").append(" where a.uid=b.uid").append("   and a.uid=?");
		// 执行查询
		return this.queryForMap(sql.toString(), this.getFromDto("uid"));
	}

	/**
	 * 不定条件查询
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> query() throws Exception 
	{
		// 还原页面查询条件
		Object name = this.getFromDto("qname");
		Object a101 = this.getFromDto("qa101");
		Object a104 = this.getFromDto("qa104");

		// 定义SQL主体
		StringBuilder sql = new StringBuilder()
				.append("select a.uid,b.name,b.ustate,u.name name2,a.a101,a.a102,a.a103,a.a104,a.b101")
				.append("  from a01 a,user b,u_r_relation c,user u")
				.append(" where a.uid=b.uid and u.uid=a.uid2")
				.append("   and c.uid=b.uid")
				.append("   and c.rid=1");

		// 参数列表
		List<Object> paramList = new ArrayList<>();
		// 逐一判断查询条件是否录入,拼接AND条件
		if (this.isNotNull(name))
		{
			sql.append(" and b.name like ?");
			paramList.add("%" + name + "%");
		}
		if (this.isNotNull(a101))
		{
			sql.append(" and a.a101=?");
			paramList.add(a101);
		}
		if (this.isNotNull(a104))
		{
			sql.append(" and a.a104=?");
			paramList.add(a104);
		}
		return this.queryForList(sql.toString(),paramList.toArray());
	}

	/*************************************hzy修改*****************************************/

	//删除方法。仅论文库无论文时可以使用
	public boolean deleteStuByID() throws Exception
	{
		Object uid=this.getFromDto("uid");
		String sql1="delete from u_r_relation where uid="+uid;
		String sql2="delete from a01 where uid="+uid;
		String sql3="delete from user where uid="+uid;
		this.executeUpdate(sql1);
		this.executeUpdate(sql2);
		return this.executeUpdate(sql3)>0;
	}

	public boolean deleteStu() throws Exception 
	{
		
		Object[] idlist=this.getIdList("idlist");
		for(Object o:idlist)
		{
			System.out.println(o);
		}
		String sql1="delete from u_r_relation where uid=?";
		String sql2="delete from a01 where uid=?";
		String sql3="delete from user where uid=?";
		this.batchUpdate(sql1, idlist);
		this.batchUpdate(sql2, idlist);
		return this.batchUpdate(sql3, idlist);
	}

	//禁用方法
	public boolean disableStuByID() throws Exception
	{
		Object uid=this.getFromDto("uid");
		System.out.println("uid");
		String sql="update user set ustate = '0' where uid="+uid;
		return this.executeUpdate(sql)>0;
	}
	
	public boolean disableStu() throws Exception
	{
		Object[] idlist=this.getIdList("idlist");
		String sql="update user set ustate='0' where uid = ?";
		return this.batchUpdate(sql, idlist);
	}
	
	//启用方法
	public boolean ableStuByID() throws Exception
	{
		Object uid=this.getFromDto("uid");
		String sql="update user set ustate = '1' where uid="+uid;
		return this.executeUpdate(sql)>0;
	}
	
	public boolean ableStu() throws Exception
	{
		Object[] idlist=this.getIdList("idlist");
		String sql="update user set ustate='1' where uid = ?";
		return this.batchUpdate(sql, idlist);
	}
}
