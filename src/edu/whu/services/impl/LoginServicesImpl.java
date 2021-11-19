package edu.whu.services.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import edu.whu.services.support.JdbcServicesSupport;

/**
 * 从数据库user表中确认登录信息
 * 同时通过联表查询，获取用户功能、角色清单
 * @author Hzy
 */

public class LoginServicesImpl extends JdbcServicesSupport{
	

	
	public Map<String, Object> queryUser(String username,String password) throws Exception{
		//从表中查询uid,name
		String sql1="select uid,name,ustate from user where uname=? and upassword=?";
		Map<String,Object> map=new HashMap<String,Object>();
		Object[] list1=
		{
			username,password
		};
		Map<String,String> temp1 = this.queryForMap(sql1, list1);
		
		//登陆失败控制
		if(temp1==null)
		{
			return null;
		}
		else if(temp1.get("ustate").equals("0"))
		{
			map.put("isActivated", false);
			return map;
		}
		else
		{
			map.put("isActivated", true);
		}
		for(Entry<String, String> e:temp1.entrySet())
		{
			map.put(e.getKey(), e.getValue());
		}
		//获取查询出的uid
		String uid=temp1.get("uid");
		
		//通过uid查找rid列表
		String sql2="select rid from u_r_relation where uid="+uid;
		List<Map<String,String>> temp2=this.queryForList(sql2);
		if(temp2.size()==0)
		{
			map.put("hasrole",false);
		}
		else
		{
			map.put("hasrole",true);
		}
		List<String> rid=new ArrayList<String>();
		for(Map<String,String> m:temp2)
		{
			rid.add(m.get("rid"));
		}
		//生成rid
		
		//存入返回值
		map.put("rid",rid);
		System.out.println(map);
		return map;
	}
	
}
