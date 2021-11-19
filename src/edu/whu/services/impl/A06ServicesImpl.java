package edu.whu.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import edu.whu.services.support.JdbcServicesSupport;
import edu.whu.system.db.DBUtils;
import edu.whu.system.tools.Tools;

import java.util.Properties;
import java.util.Set;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class A06ServicesImpl extends JdbcServicesSupport 
{
	public boolean addStaff() throws Exception
	{
		String username = Tools.getFormatNumber("2", "Sta");
		boolean tag = false;
		System.out.println(username);
		// 向DTO添加员工编号
		DBUtils.beginTransaction();
		try {
			String sql1 = "insert into user( uname,upassword,name,ustate) values(?,?,?,?)";

			Object args2[] = { 
					username, 
					Tools.getMd5(username), 
					this.getFromDto("name"), 
					"0" 
					};
			this.executeUpdate(sql1.toString(), args2);
			// 1.编写SQL语句
			StringBuilder sql = new StringBuilder()
					.append("insert into a06(uid,a601,a602,a603,a604,a605,")
					.append("                 a606,a607,a608,a609)")
					.append(" select user.uid,?,?,?,?,?,?,?,?,?  ")
					.append(" from user     ")
			        .append(" where user.uname=?  ");
			// 2.编写参数数组
			Object args[] = {
					this.getFromDto("a601"), 
					this.getFromDto("a602"),
					this.getFromDto("a603"),
					this.getFromDto("a604"), 
					this.getFromDto("a605"),
					this.getFromDto("a606"),
					this.getFromDto("a607"),
					this.getFromDto("a608"), 
					this.getFromDto("a609"),
					username
					};
			this.executeUpdate(sql.toString(), args);
			//u_r_relation表插入
			String sql2="insert into u_r_relation (uid,rid,u_r_state) select user.uid,?,? from user where user.uname=?";	
			String[] rolelist=null;
			if(this.getFromDto("roles") instanceof String[])
			{
				rolelist=(String[])this.getFromDto("roles");
			}
			else
			{
				rolelist=((String)this.getFromDto("roles")).split(" ");
			}
			for(String s:rolelist)
			{
				Object[] args3={
							s,
							"1",
							username
					};
				this.executeUpdate(sql2, args3);
				//判断是否插入导师表信息
				if(s.equals("2"))
				{
					String sql3="insert into a02 (uid,a201) select user.uid,null from user where user.uname="+username;
					String sql4="update user set ustate='1' where uname="+username;
					this.executeUpdate(sql3);
					this.executeUpdate(sql4);
				}
			}
			DBUtils.commit();
		} catch (Exception ex) {
			DBUtils.rollback();
			ex.printStackTrace();
		} finally {
			tag = true;
			DBUtils.endTransaction();
		}
		this.putIntoDto("username", username);
		return tag;
	}


	public Map<String, String> queryByID() throws Exception 
	{
		Map<String,String> map=new HashMap<String, String>();
		// 1.编写SQL语句
		StringBuilder sql1 = new StringBuilder()
				.append("select b.name,a.uid,a.a601,a.a602,a.a603,a.a604,")
				.append("       a.a605,a.a606,a.a607,a.a608,a.a609")
				.append("  from a06 a,user b")
				.append(" where a.uid=b.uid")
				.append("   and a.uid=?");
		// 执行查询
		map=this.queryForMap(sql1.toString(), this.getFromDto("uid"));
		String uid=map.get("uid");
		//获取roles
		String sql2="select rid from u_r_relation where uid="+uid;
		List<Map<String,String>> maplist=this.queryForList(sql2);
		StringBuilder roles=new StringBuilder();
		for(Map<String,String> m:maplist)
		{
			roles.append(m.get("rid")+",");
		}
		map.put("roles", roles.toString().substring(0,roles.toString().lastIndexOf(",")));
		return map;
	}

	/**
	 * 不定条件查询
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> query() throws Exception
	{
		//获取禁用，启用，删除条件
		String canDel="0";
		// 还原页面查询条件
		Object name = this.getFromDto("qname");
		Object a601 = this.getFromDto("qa601");
		Object a602 = this.getFromDto("qa602");
		Object a605 = this.getFromDto("qa605");
		
		// 定义SQL主体
		StringBuilder sql = new StringBuilder()
				.append("select b.name,b.ustate,a.a601,x.fvalue a602,a.a603,s.fvalue a604,")
				.append("       a.a605,a.a608,a.a609,a.uid")
				.append("  from a06 a,user b,syscode s,syscode x")
				.append(" where a.uid=b.uid and s.fcode=a.a604 and s.fname='a604' and x.fcode=a.a602 and x.fname='a602' ")
				;
		
		// 参数列表
		List<Object> paramList = new ArrayList<>();
		// 逐一判断查询条件是否录入,拼接AND条件
		if (this.isNotNull(name)) {
			sql.append(" and b.name like ?");
			paramList.add("%" + name + "%");
		}
		if (this.isNotNull(a601)) {
			sql.append(" and a.a601=?");
			paramList.add(a601);
		}
		if (this.isNotNull(a602)) {
			sql.append(" and a.a602=?");
			paramList.add(a602);
		}
		if (this.isNotNull(a605)) {
			sql.append(" and a.a605=?");
			paramList.add(a605);
		}

		List<Map<String,String>> maplist=this.queryForList(sql.toString(), paramList.toArray());
		
		//建立set确定用户角色范围
		Set<String> roleset=new HashSet<>( );
		roleset.add("2");
		roleset.add("4");
		roleset.add("5");
		roleset.add("7");
		
		Iterator<Map<String,String>> it=maplist.iterator();
		while(it.hasNext())
		{
			//获取角色列表
			Map<String,String> m=it.next();
			String uid=m.get("uid");
			String sql2="select u.rid,r.rname from u_r_relation u,role r where r.rid=u.rid and u.uid="+uid;
			List<Map<String,String>> temp=this.queryForList(sql2);
			boolean flag=false;
			StringBuilder roles=new StringBuilder();

			for(Map<String,String> tm:temp)
			{
				if(roleset.contains(tm.get("rid")))
				{
					roles.append(tm.get("rname")+" ");
					flag=true;
				}
			}
			if(!flag)
			{
				it.remove();
			}
			else
			{
				m.put("roles", roles.toString().trim());
				//是否可删条件获取
				//是否为学生导师
				String sql3="select uid from a01 where uid2="+uid;
				Map<String,String> c1=this.queryForMap(sql3);
				//是否在评审，答辩表中有数据
				String sql4="select uid from a04 where uid="+uid;
				Map<String,String> c2=this.queryForMap(sql4);
				String sql5="select uid from a05 where uid="+uid;
				Map<String,String> c3=this.queryForMap(sql5);
				if(c1==null&&c2==null&c3==null)
				{
					canDel="1";
					
				}
				m.put("canDel", canDel);
			}
		}
		
		//领域代码按syscode转换
		for(Map<String,String> m:maplist)
		{
			String uid=m.get("uid");
			List<Map<String,String>> list_1=new ArrayList<>();
			List<Map<String,String>> list_2=new ArrayList<>();
			//依据uid读出领域码
			String sql_1="select a603 from a06 where uid="+uid;
			list_1=this.queryForList(sql_1);
			for(Map<String,String> map_1:list_1)
			{
				//读出领域代码字符串
				String a603=map_1.get("a603");
				//syscode转换
				String sql_2="select fvalue from syscode where fname='a603' and fcode in("+a603+")";
				list_2=this.queryForList(sql_2);
				//将得到的多条数据，粘合成一条
				StringBuilder str=new StringBuilder();
				for(Map<String,String> fvalueMap:list_2)
				{
					str.append(fvalueMap.get("fvalue"));//程序语义---大数据
					str.append(" ");//空格分隔
				}
				m.put("a603",str.toString());
			}
		}
		
		return maplist;
	}
	
	public boolean modifyStaff() throws Exception {
		String uid=(String)this.getFromDto("uid");
		//修改a01信息
		StringBuilder sql1 = new StringBuilder()
				.append("update a06 a, user b")
				.append("   set a.a601=?,a.a602=?,a.a603=?,a.a604=?,a.a605=?,a.a606=?,")
				.append("       a.a607=?,a.a608=?,a.a609=?,b.name=?")
				.append(" where a.uid=b.uid")
				.append("   and a.uid=?");
		Object args[] = { 
				this.getFromDto("a601"), 
				this.getFromDto("a602"), 
				this.getFromDto("a603"), 
				this.getFromDto("a604"), 
				this.getFromDto("a605"), 
				this.getFromDto("a606"), 
				this.getFromDto("a607"), 
				this.getFromDto("a608"), 
				this.getFromDto("a609"), 
				this.getFromDto("name"),
				uid
				};
		
		//处理角色变更
		//获取已有和将要变更的用户角色情况
		//生成rid情况的对应map，-1为删除，0为不改变，1为添加
		Map<String,Integer> map=new HashMap<String,Integer>();
		String[] nlist=null;
		if(this.getFromDto("roles") instanceof String[])
		{
			nlist=(String[])this.getFromDto("roles");
		}
		else
		{
			nlist=((String)this.getFromDto("roles")).split(" ");
		}
		
		for(String s:nlist)
		{
			map.put(s,1);
		}
		
		String sql2="select rid from u_r_relation where uid="+uid;
		List<Map<String,String>> maplist=this.queryForList(sql2);
		for(Map<String,String> m:maplist)
		{
			String rid=m.get("rid");
			if(map.containsKey(rid))
			{
				map.replace(rid, 0);
			}
			else
			{
				map.put(rid, -1);
			}
		}
		
		for(String s:map.keySet())
		{
			switch(map.get(s)) {
				case -1:
					String sql3="delete from u_r_relation where uid="+uid+" and rid="+s;
					
					//判断是否删除导师表信息
					if(s.equals("2"))
					{
						String sql4="delete from a02 where uid="+uid;
						String sql5="update user set ustate='0' where uid="+uid;
						this.executeUpdate(sql4);
						this.executeUpdate(sql5);
					}
					this.executeUpdate(sql3);
					break;
				case 0:
					break;
				case 1:
					String sql5="insert into u_r_relation (uid,rid,u_r_state) values("+uid+","+s+",'1')";
					//判断是否插入导师表信息
					if(s.equals("2"))
					{
						String sql4="insert into a02 (uid,a201) values ("+uid+",null)";
						String sql6="update user set ustate='1' where uid="+uid;
						this.executeUpdate(sql4);
						this.executeUpdate(sql6);
					}
					this.executeUpdate(sql5);
					break;
			}
		}
		return this.executeUpdate(sql1.toString(), args) > 0;
	}
	
	

	
	/******************************************************************************/
	//删除方法
	public boolean deleteByID() throws Exception 
	{
		
		boolean tag = false;
		DBUtils.beginTransaction();
		try
		{
			//获取角色列表 删除对应角色表信息（导师）
			String uid=(String)this.getFromDto("uid");
			String sql1="select rid from u_r_relation where uid="+uid;
			List<Map<String,String>> maplist=this.queryForList(sql1);
			for(Map<String,String> m:maplist)
			{
				if(m.get("rid").equals("2"))
				{
					String sql2="delete from a02 where uid="+uid;
					this.executeUpdate(sql2);
				}
			}
			String sql3 = "delete from u_r_relation where uid="+uid;
			this.executeUpdate(sql3);
			String sql4 = "delete from a06 where uid="+uid;
			this.executeUpdate(sql4);
			String sql5 = "delete from user where uid="+uid;
			this.executeUpdate(sql5);
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

	public boolean batchDelete() throws Exception {
		boolean tag = false;
		DBUtils.beginTransaction();
		try
		{
			String[] list=this.getIdList("idlist");
			//获取角色列表 删除对应角色表信息（导师）
			for(String uid:list)
			{
				String sql1="select rid from u_r_relation where uid="+uid;
				List<Map<String,String>> maplist=this.queryForList(sql1);
				for(Map<String,String> m:maplist)
				{
					if(m.get("rid").equals("2"))
					{
						String sql2="delete from a02 where uid="+uid;
						this.executeUpdate(sql2);
					}
				}
				String sql3 = "delete from u_r_relation where uid="+uid;
				this.executeUpdate(sql3);
				String sql4 = "delete from a06 where uid="+uid;
				this.executeUpdate(sql4);
				String sql5 = "delete from user where uid="+uid;
				this.executeUpdate(sql5);
			}
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
	
	//禁用方法
	public boolean disaStaffByID() throws Exception
	{
		Object uid=this.getFromDto("uid");
		String sql="update user set ustate = '0' where uid="+uid;
		return this.executeUpdate(sql)>0;
	}
	
	public boolean disaStaff() throws Exception
	{
		Object[] idlist=this.getIdList("idlist");
		String sql="update user set ustate='0' where uid = ?";
		return this.batchUpdate(sql, idlist);
	}
	
	//启用方法
	public boolean ableStaffByID() throws Exception
	{
		Object uid=this.getFromDto("uid");
		String sql="update user set ustate = '1' where uid="+uid;
		return this.executeUpdate(sql)>0;
	}
	
	public boolean ableStaff() throws Exception
	{
		Object[] idlist=this.getIdList("idlist");
		String sql="update user set ustate='1' where uid = ?";
		return this.batchUpdate(sql, idlist);
	}
	
	public boolean email() throws Exception
	{
		boolean tag = false;
		Properties properties = new Properties();
		properties.put("mail.transport.protocol", "smtp");// 连接协议
		properties.put("mail.smtp.host", "smtp.163.com");// 主机名
		//properties.put("mail.smtp.port", 465);// 端口号
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.ssl.enable", "true");// 设置是否使用ssl安全连接 ---一般都使用
		properties.put("mail.debug", "true");// 设置是否显示debug信息 true 会在控制台显示相关信息
		// 得到回话对象
		Session session = Session.getInstance(properties);
		// 获取邮件对象
		Message message = new MimeMessage(session);
		// 设置发件人邮箱地址
		message.setFrom(new InternetAddress("rsh_whu@163.com"));
		// 设置收件人邮箱地址
		// message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new
		
		// 设置邮件标题
		message.setSubject("武汉大学论文答辩邀请");

		//获取角色仅为未注册专家的idlist
		String sql1="select uid from u_r_relation where rid='7'";
		List<Map<String,String>> maplist=this.queryForList(sql1);
		List<String> idlist=new ArrayList<>();
		for(Map<String,String> m:maplist)
		{
			idlist.add(m.get("uid"));
		}
		//去除含有别的角色的用户id
		Iterator it=idlist.iterator();
		while(it.hasNext())
		{
			String tuid=(String)it.next();
			String sql2="select rid from u_r_relation where uid="+tuid;
			if(this.queryForList(sql2).size()>1)
			{
				it.remove();
			}
		}
		
		
		/*************演示用唯一接收邮件专家**************/
		String uid=idlist.get(0);
		idlist.clear();
		idlist.add(uid);
		/*************演示用唯一接收邮件专家**************/
		
		
		for (String id : idlist)
		{
			StringBuilder sql3 = new StringBuilder()
					.append("SELECT b.uname,b.name, a.a609")
					.append("  from a06 a, user b")
					.append(" where a.uid=b.uid")
					.append(" and a.uid=?")
					;
			Map<String, String> mailinfo = this.queryForMap(sql3.toString(), Integer.parseInt(id));
			StringBuilder mess = new StringBuilder()
					.append("尊敬的"+mailinfo.get("name")+",您好\n")
					.append("武汉大学计算机学院真诚地邀请您参与今年的研究生论文答辩.")
					.append("您可以选择参与评审过程或答辩过程.")
					.append("如果您能在百忙之中接受邀请,请登录以下网址进行注册\n")
					.append("http://localhost:8080/project/activate.jsp?flag="+mailinfo.get("uname")+"\n")
					.append("武汉大学计算机学院欢迎您的到来")
					;
			// InternetAddress("xxx@qq.com"),new InternetAddress("xxx@qq.com"),new
			// InternetAddress("xxx@qq.com")});
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(mailinfo.get("a609")));//一个收件人
			// 设置邮件内容
			message.setText(mess.toString());
			// 得到邮差对象
			Transport transport = session.getTransport();
			// 连接自己的邮箱账户
			transport.connect("rsh_whu@163.com", "rshwhu1234");// 密码为QQ邮箱开通的stmp服务后得到的客户端授权码
			// 发送邮件
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		}
		return tag;
	}
}