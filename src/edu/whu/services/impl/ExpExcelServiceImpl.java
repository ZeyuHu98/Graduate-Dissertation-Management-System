package edu.whu.services.impl;

import java.util.Map;

import edu.whu.services.support.JdbcServicesSupport;
import edu.whu.system.db.DBUtils;
import edu.whu.system.tools.Tools;

public class ExpExcelServiceImpl extends JdbcServicesSupport 
{
    
    public boolean addEmp()throws Exception
    {
    	DBUtils.beginTransaction();
    	String username = Tools.getFormatNumber("2","Sta");
    	boolean tag=false;
    	//向DTO添加员工编号
    	
    	
    	try
    	{
    	String sql2 = "insert into user(uname,upassword,name,ustate) values(?,?,?,?)";
    	
    	Object args2[]= {
    			username,
    			Tools.getMd5(username),
    			this.getFromDto("姓名").toString(),
    			"0"
    	};
    	this.executeUpdate(sql2.toString(), args2);
    	
    	String[] rolelist=((String)this.getFromDto("专家角色")).split(",");
    	
    	String sql3  = "insert into u_r_relation (uid,rid,u_r_state) select user.uid,?,? from user where user.uname=?";
    	for(String s:rolelist)
    	{
    		
    		if(s.equals("导师"))
    		{
    			String sql8 = "insert into a02(uid) select user.uid from user where user.uname=?";
    			this.executeUpdate(sql8.toString(), username);
    			String sql9="update user set ustate='1' where uname="+username;
    			this.executeUpdate(sql9.toString());
    		}
    		String sql5="select fcode from syscode where fvalue=? and fname='rid'";
			Map<String,String> map=this.queryForMap(sql5,s);
			System.out.println("map = "+map);
    		Object args3[]= 
    			{
    					map.get("fcode"),
    					"1",
    					username
    			};
    		this.executeUpdate(sql3.toString(), args3);
    	}

    	String sql6="select fcode from syscode where fvalue=? and fname='a602'";
		Map<String,String> map2=this.queryForMap(sql6,this.getFromDto("专家类型"));
		String sql7="select fcode from syscode where fvalue=? and fname='a604'";
		Map<String,String> map3=this.queryForMap(sql7,this.getFromDto("职称"));
    	
    	//1.编写SQL语句
    	StringBuilder sql = new StringBuilder()
				.append("insert into a06(uid,a601,a602,a603,a604,a605,")
				.append("                 a606,a607,a608,a609) ")
				.append(" select user.uid,?,?,?,?,?,?,?,?,? ")
				.append(" from user     ")
		        .append(" where user.uname=?  ");

    	//2.编写参数数组
    	Object args[]={
    			this.getFromDto("所属大学"),
    			map2.get("fcode"),
    			this.getFromDto("研究领域"),
    			map3.get("fcode"),
    			this.getFromDto("身份证号"),
    			this.getFromDto("银行账户"),
    			this.getFromDto("开户银行"),
    			this.getFromDto("手机号码"),
    			this.getFromDto("邮箱"),
    			username
    	               };
    	this.executeUpdate(sql.toString(), args);
    	DBUtils.commit();
    	}
    	catch(Exception ex)
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

