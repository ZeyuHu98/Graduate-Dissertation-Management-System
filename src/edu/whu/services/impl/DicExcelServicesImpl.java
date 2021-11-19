package edu.whu.services.impl;

import java.util.List;
import java.util.Map;
import edu.whu.services.support.JdbcServicesSupport;
import edu.whu.system.db.DBUtils;
import edu.whu.system.tools.News;

public class DicExcelServicesImpl extends JdbcServicesSupport 
{
	public boolean addEmp() throws Exception 
	{
		boolean tag = false;
			DBUtils.beginTransaction();
			try {
				String sql0 = "delete from b03 where b03.b101 in (select b01.b101 from b01,a01 where b01.uid1=a01.uid and a01.a101=?)";
				this.executeUpdate(sql0.toString(), this.getFromDto("学号"));
				
				// 1.编写SQL语句
				StringBuilder sql = new StringBuilder()
						.append("replace into b04(b101,user06,b402,b403,b404,b405,b406,b407,b408,b409,b410) ")
						.append("select b01.b101, a03.uid,?,?,?,?,?,?,?,?,? ")
						.append("from b01 ,a01, a03, user ")
						.append("where a01.uid=b01.uid1 ")
						.append("and user.uid = a03.uid ")
						.append("and user.name=? ")
						.append("and a01.a101=?  ");
				// 2.编写参数数组
				Object args[] = { 
						this.getFromDto("答辩时间"), 
						" ",
						this.getFromDto("优秀"), 
						this.getFromDto("良好"), 
						this.getFromDto("合格"), 
						this.getFromDto("不合格"),
						this.getFromDto("评语"),
						this.getFromDto("同意授予"), 
						this.getFromDto("同意重新答辩"), 
						this.getFromDto("答辩秘书"), 
						this.getFromDto("学号"),
				};
				this.executeUpdate(sql.toString(), args);
				
				String qsql = "select b01.b101 from b01 ,a01 where a01.uid=b01.uid1 and a01.a101=? ";
				Map<String,String> map = this.queryForMap(qsql, this.getFromDto("学号"));
				int unpass = Integer.parseInt(this.getFromDto("不合格").toString());
				System.out.println("pass = "+unpass);
				if(unpass>=2)
				{
					String sqlp = "update b01 set b01.b109='2' where b01.b101=?";
					this.executeUpdate(sqlp.toString(),map.get("b101"));
				}
				else
				{
					String sqlp = "update b01 set b01.b109='1' where b01.b101=?";
					this.executeUpdate(sqlp.toString(),map.get("b101"));
				}
				
				StringBuilder sql2 = new StringBuilder()
						.append("insert into b03(uid,b101,b302) ")
						.append("select user.uid,b01.b101,'1' ")
						.append("from user,b01,a01 ")
						.append("where user.name=? ")
						.append("and b01.uid1=a01.uid ")
						.append("and a01.a101=? ")
						;
				
				Object args2[] = { 
						this.getFromDto("答辩主席"), 
						this.getFromDto("学号"), 	
				};
				this.executeUpdate(sql2.toString(), args2);
				
				StringBuilder sql3 = new StringBuilder()
						.append("insert into b03(uid,b101,b302) ")
						.append("select user.uid,b01.b101,'0' ")
						.append("from user,b01,a01 ")
						.append("where user.name=? ")
						.append("and b01.uid1=a01.uid ")
						.append("and a01.a101=? ")
						;
				
				Object args3[] = { 
						this.getFromDto("委员一"), 
						this.getFromDto("学号"), 	
				};
				this.executeUpdate(sql3.toString(), args3);
				
				StringBuilder sql4 = new StringBuilder()
						.append("insert into b03(uid,b101,b302) ")
						.append("select user.uid,b01.b101,'0' ")
						.append("from user,b01,a01 ")
						.append("where user.name=? ")
						.append("and b01.uid1=a01.uid ")
						.append("and a01.a101=? ")
						;
				
				Object args4[] = { 
						this.getFromDto("委员二"), 
						this.getFromDto("学号"), 	
				};
				this.executeUpdate(sql4.toString(), args4);
				
				StringBuilder sql5 = new StringBuilder()
						.append("insert into b03(uid,b101,b302) ")
						.append("select user.uid,b01.b101,'0' ")
						.append("from user,b01,a01 ")
						.append("where user.name=? ")
						.append("and b01.uid1=a01.uid ")
						.append("and a01.a101=? ")
						;
				
				Object args5[] = { 
						this.getFromDto("委员三"), 
						this.getFromDto("学号"), 	
				};
				this.executeUpdate(sql5.toString(), args5);
				
				
				StringBuilder sql6 = new StringBuilder()
						.append("insert into b03(uid,b101,b302) ")
						.append("select user.uid,b01.b101,'0' ")
						.append("from user,b01,a01 ")
						.append("where user.name=? ")
						.append("and b01.uid1=a01.uid ")
						.append("and a01.a101=? ")
						;
				
				Object args6[] = { 
						this.getFromDto("委员四"), 
						this.getFromDto("学号"), 	
				};
				this.executeUpdate(sql6.toString(), args6);
				
				String qusql = "select a01.uid from a01 where a01.a101="+this.getFromDto("学号");
				Map<String,String> map3 = this.queryForMap(qusql);
				String title = "答辩结果已发布";
				String text = "答辩结果已经录入系统,您可以通过论文查询页面查看结果,如有疑问请联系秘书";
				
				String[] obj = {
						map3.get("uid")
				};
				News.sendNews(title, text, obj);
				
				
				DBUtils.commit();
			} 
			catch (Exception ex) 
			{
				DBUtils.rollback();
				ex.printStackTrace();
			} finally
			{
				tag = true;
				DBUtils.endTransaction();
			}
		return tag;
	}
	
	public List<Map<String,String>> query() throws Exception
	{
		StringBuilder sql=new StringBuilder()
				.append("select a.a101,u.name name1,y.name name2,c.b402,v.name name3,")
				.append("       d.b202,w.name name4,x.name name5,c.b404,c.b405,count(distinct a.a101),")
				.append("       c.b406,c.b407")
				.append("  from b04 c,user u,a01 a,b01 b,b02 d,user v,b03 e,user w,user x,user y")
				.append(" where a.uid=b.uid1  and b.b101=c.b101 and a.uid=u.uid and d.b101=b.b101 and d.uid=v.uid")
				.append("   and e.b101=b.b101 and e.uid=w.uid   and e.b302='1'  and c.user06=x.uid and a.uid2=y.uid group by a.a101");
		return this.queryForList(sql.toString());
	}
}
