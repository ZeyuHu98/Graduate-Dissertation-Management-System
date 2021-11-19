package edu.whu.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import edu.whu.services.support.JdbcServicesSupport;
import edu.whu.system.db.DBUtils;
import edu.whu.system.tools.Tools;

public class SetTimeServicesImpl extends JdbcServicesSupport 
{

    public boolean settime()throws Exception
    {


        int datesub = Integer.parseInt(this.getFromDto("datesub").toString());
        
        //int uid2 = 2;
        //session.setAttribute("cuid", uid2);
        //String rolelist[] = {"1","3"};
        //ession.setAttribute("rolelist", rolelist);
        
        int uid =  Integer.parseInt(this.getFromDto("cuid").toString());
        String roles = this.getFromDto("crolelist").toString();
        boolean tag=false;
        
        char[] a3 = new char[datesub*2];
        DBUtils.beginTransaction();
        String[] asd = (String[]) this.getFromDto("settime");
        for(int i=0;i<datesub*2;i++)
        {
        	a3[i] = '0';
        }
        for(String s:asd)
        {
        	a3[Integer.parseInt(s)-1]= '1';
        }
        String a301 = String.valueOf(a3);
        //System.out.println(a301);
        try {
        	Object args[]= {    			
        			a301,
        			uid
        	};
        	for(char c:roles.toCharArray())
        	{
        		//System.out.println("c="+c);
        		StringBuilder sql = new StringBuilder();
        		if(c=='1')
        		{
        			sql.append( "INSERT INTO a02(a201,uid) VALUES(?,?) ON DUPLICATE KEY UPDATE uid=VALUES(uid), a201=VALUES(a201)  ");
        			//System.out.println("1111111");
        			this.executeUpdate(sql.toString(), args);
        		}
        		if(c=='3')
        		{
        			sql.append("INSERT INTO a03(a301,uid) VALUES(?,?) ON DUPLICATE KEY UPDATE uid=VALUES(uid), a301=VALUES(a301)  ");
        			//System.out.println("3333333");
        			this.executeUpdate(sql.toString(), args);
        		}
        		if(c=='5')
        		{
        			sql.append("update a05 set a504=? where uid=? ");
        			//System.out.println("5555555");
        			this.executeUpdate(sql.toString(), args);
        		}
        	}
        	
    	DBUtils.commit();
    	tag=true;
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
