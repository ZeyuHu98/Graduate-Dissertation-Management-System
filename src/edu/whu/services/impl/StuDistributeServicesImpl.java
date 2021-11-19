package edu.whu.services.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import edu.whu.services.support.JdbcServicesSupport;
import edu.whu.system.db.DBUtils;

public class StuDistributeServicesImpl extends JdbcServicesSupport 
{
	//全部学生分配
	public boolean distributeStu() throws Exception
	{
		//获取每个导师的uid和每一类学生的数量组成的Map
		Map<String,Integer> map=new HashMap<String,Integer>();
		String sql1="select uid2, count(uid2) num from a01 group by uid2;";
		List<Map<String,String>> list1=this.queryForList(sql1);
		for(Map<String,String> m:list1)
		{
			String key=m.get("uid2");
			Integer num=Integer.parseInt(m.get("num"));
			map.put(key, num);
		}
		//得到倒叙排列的List<Entry>
		List<Entry<String,Integer>> sortedlist=this.sortMap(map);
		int groupSize=sortedlist.size();
		int stuSize=0;
		for(Entry<String,Integer> e:sortedlist)
		{
			stuSize+=e.getValue();
		}
		//获取秘书uid列表
		String sql2="select uid from a03;";
		List<Map<String,String>> list2=this.queryForList(sql2);
		List<String> secIDList=new ArrayList<String>();
		for(Map<String,String> m:list2)
		{
			secIDList.add(m.get("uid"));
		}
		int secSize=secIDList.size();
		//设置每名秘书分配学生的上下界
//		int aveStuNum=stuSize/secSize;
//		int minStuNum=stuSize/secSize-5;
		int maxStuNum=stuSize/secSize+5;
		//为每名秘书在上下界范围内分配学生
		//当前分组数
		int groupNum=0;
L1:		for(int i=0;i<secSize;i++)
		{
			int disNum=0;
			while(disNum<maxStuNum && groupNum<groupSize)
			{
				Entry<String,Integer> e=sortedlist.get(groupNum);
				if(disNum+e.getValue()<maxStuNum)
				{
					String uid2=e.getKey();
					disNum+=e.getValue();
					String tsql="update a01 set uid3="+secIDList.get(i)+" where uid2="+uid2;
					this.executeUpdate(tsql);
					groupNum++;
				}
				else
				{
					continue L1;
				}
			}
		}
		return true;
	}
	
	public Object showDisResult() throws Exception
	{
		String sql="select uid3,count(uid3) num from a01 group by uid3";
		List<Map<String,String>> list=this.queryForList(sql);
		Map<String,Integer> result=new HashMap<>();
		for(Map<String,String> m:list)
		{
			result.put(m.get("uid3"), Integer.parseInt(m.get("num")));
		}
		return result;
	}
	
	public List<Map<String,String>> query() throws Exception
	{
		String sql1="set @mycnt=0;";
		DBUtils.prepareStatement(sql1).executeQuery();
		String sql="select @mycnt:=@mycnt+1 as num,w.name aname,v.name bname,u.name cname,a.a101 "
				 + "from a01 a,user u,user v,user w where a.uid=u.uid and v.uid=a.uid2 and w.uid=a.uid3 ";
		return this.queryForList(sql);
	}
	
	//学生导出方法
	public ResultSet outPutConResult() throws SQLException, Exception
	{
		String sql="select w.name 秘书名字,v.name 导师名字,u.name 学生名字 "
				+ "from a01 a,user u,user v,user w where a.uid=u.uid and v.uid=a.uid2 and w.uid=a.uid3;";
		return DBUtils.prepareStatement(sql).executeQuery();
	}
	
	//map排序方法
	private List<Entry<String,Integer>> sortMap(Map<String,Integer> map)
	{
        int size = map.size();
        // 通过map.entrySet()将map转换为"key=number"形式的list集合
        List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(size);
        list.addAll(map.entrySet());
        // 通过Collections.sort()排序
        Collections.sort(list, new Comparator<Entry<String, Integer>>()
        {
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2)
            {
                // compareTo方法 (x < y) ? -1 : ((x == y) ? 0 : 1)
                return o1.getValue().compareTo(o2.getValue());
            };
        });
        Collections.reverse(list);
        return list;
	}
	
    /**
     * 导出用户
     * 
     * @throws Exception
     */
    public void fillExcelData(ResultSet rs,Workbook wb,String[]headers)throws Exception 
    {
        int rowIndex=0; //第一行
        Sheet sheet=wb.createSheet(); //创建sheet页
        Row row=sheet.createRow(rowIndex++);
        //创建标题
        for (int i=0;i<headers.length;i++) 
        {
            row.createCell(i).setCellValue(headers[i]);
        }
        //导出数据库中的数据
        while(rs.next())
        {
            row=sheet.createRow(rowIndex++);
            for (int i=0;i<headers.length;i++)
            {
            	//rs.getObject(i + 1)得到一个对象，即数据库中一行的结果，每一列就是属性凑成一行变成对象。因为id是从1开始，所以要+1。
                row.createCell(i).setCellValue(rs.getObject(i+1).toString());
            }
        }
    }
}
