package edu.whu.services.impl;

import java.util.List;
import java.util.Map;

import edu.whu.services.support.JdbcServicesSupport;

public class ExpertInfoServicesImpl extends JdbcServicesSupport
{
    /**
     * 专家查看自己关联的答辩信息
     * @return
     * @throws Exception
     */
	public List<Map<String,String>>query()throws Exception
	{
  		//还原页面查询条件
    	Object uid=this.getFromDto("uid");
		//定义SQL主体
    	//尚未编写SQL具体语句,因为答辩安排未确认,在test数据库测试
		StringBuilder sql=new StringBuilder()
				.append("select s.fvalue sb302,u.name name1,b.b102,x.name name2")
				.append("  from b01 b,b03 c,a05 d,user u,user x,syscode s")
				.append(" where u.uid=b.uid1 and c.b101=b.b101")
				.append("   and x.uid=b.uid2 and c.uid=d.uid")
				.append("   and s.fname='b302' and s.fcode=c.b302 and c.uid=?")
				.append(" order by u.uid");
		Object args[]={
				uid
		};
  		return this.queryForList(sql.toString(), args);
	}
}
