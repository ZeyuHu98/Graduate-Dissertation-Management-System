package edu.whu.services.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Workbook;

import edu.whu.services.support.JdbcServicesSupport;
import edu.whu.system.db.DBUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class OutputExcelServicesImpl extends JdbcServicesSupport 
{
	public boolean financialAdd() throws Exception
	{
		String sql1="delete from b06;";
		this.executeUpdate(sql1);
		StringBuilder sql2=new StringBuilder()
				.append("insert into b06(b602,b603,b604,b605,uid,rid,a602,a604)")
				.append("select u1.name b602,a.a101 b603,u2.name b604,d.a601 b605,u3.uid,")
				.append("       '4' as rid,d.a602,d.a604")
				.append("  from user u1,a01 a,user u2,b01 b,user u3,b02 c,a06 d")
				.append(" where u1.uid=a.uid and a.uid2=u2.uid")
				.append("   and b.uid1=a.uid and b.b101=c.b101 and c.uid=u3.uid")
				.append("   and c.uid=d.uid union ")
				.append("select u1.name b602,a.a101 b603,u2.name b604,d.a601 b605,u3.uid,")
				.append("       '5' as rid,d.a602,d.a604")
				.append("  from user u1,a01 a,user u2,b01 b,user u3,b03 c,a06 d")
				.append(" where u1.uid=a.uid and a.uid2=u2.uid")
				.append("   and b.uid1=a.uid and b.b101=c.b101 and c.uid=u3.uid")
				.append("   and c.uid=d.uid union ")
				.append("select u1.name,a.a101,u2.name,d.a601,u3.uid,'3' as rid,d.a602,d.a604 ")
				.append("  from user u1,user u2,user u3,a01 a,b01 b,b04 c,a06 d ")
				.append(" where a.uid=u1.uid and b.b101=c.b101 and b.uid1=u1.uid and u2.uid=b.uid2 ")
				.append("   and c.user06=u3.uid and d.uid=u3.uid ");
		return this.executeUpdate(sql2.toString())>0;
	}
	
	public List<Map<String,String>> query()throws Exception
	{
		StringBuilder sql=new StringBuilder()
				.append("select b.b603,b.b602,b.b604,")
				.append("       u.name,s.fvalue,a.a601,'200' as money")
				.append("  from a06 a,b06 b,user u,syscode s")
				.append(" where b.uid=u.uid and a.uid=u.uid")
				.append("   and s.fname='a604' and b.a604=s.fcode")
				.append("   and rid in('4','5')")
				.append(" union ")
				.append("select b.b603,b.b602,b.b604,")
				.append("       u.name,s.fvalue,a.a601,'70' as money")
				.append("  from a06 a,b06 b,user u,syscode s")
				.append(" where b.uid=u.uid and a.uid=u.uid")
				.append("   and s.fname='a604' and b.a604=s.fcode")
				.append("   and rid='3'");
		return this.queryForList(sql.toString());
	}
	
	
	public ResultSet financialList()throws Exception
	{
		//若用数字代表信息的，用syscode表的编码进行转换
		//用rid区分专家和秘书，给予不同的酬金，再用union连接专家和秘书的财务信息
		StringBuilder sql=new StringBuilder()
				.append("select b.b603 学生学号,b.b602 学生姓名,b.b604 导师,")
				.append("       u.name 姓名,s.fvalue 职称,a.a601 单位,'200' as '酬金'")
				.append("  from a06 a,b06 b,user u,syscode s")
				.append(" where b.uid=u.uid and a.uid=u.uid")
				.append("   and s.fname='a604' and b.a604=s.fcode")
				.append("   and rid in('4','5')")
				.append(" union ")
				.append("select b.b603 学生学号,b.b602 学生姓名,b.b604 导师,")
				.append("       u.name 姓名,s.fvalue 职称,a.a601 单位,'70' as '酬金'")
				.append("  from a06 a,b06 b,user u,syscode s")
				.append(" where b.uid=u.uid and a.uid=u.uid")
				.append("   and s.fname='a604' and b.a604=s.fcode")
				.append("   and rid='3'");
	    return DBUtils.prepareStatement(sql.toString()).executeQuery();
	}

	public ResultSet financialListForInner()throws Exception
	{
		//新版本navicat需要在x.name用any_value()包被
		//union连接评审信息、答辩信息和秘书信息，再用group by uid分组累加获得答辩评审次数，得到总酬金
	    StringBuilder sql=new StringBuilder()
//	    		.append(" select any_value(x.name),any_value(x.b605),any_value(x.num),any_value(x.money) from ")
//	    		.append("(select any_value(u.uid),any_value(u.name),any_value(b.b605),count(b.uid) as num,count(b.uid)*'70' as money ")
//	    		.append("   from user u,b06 b ")
//	    		.append("  where u.uid=b.uid and b.rid='3' ")
//	    		.append("  group by b.uid union ")	
//	    		.append(" select any_value(u.uid),any_value(u.name),any_value(a.a601),count(c.b101) as num,count(c.b101)*'200' as money ")
//	    		.append("   from a06 a,user u,b03 c ")
//	    		.append("  where u.uid=a.uid and u.uid=c.uid and a.a602='1' ")
//	    		.append("  group by c.uid union ")
//	    		.append(" select any_value(u.uid),any_value(u.name),any_value(a.a601),count(c.b101) as num,count(c.b101)*'200' as money ")
//	    		.append("   from a06 a,user u,b02 c ")
//	    		.append("  where u.uid=a.uid and u.uid=c.uid and a.a602='1' ")
//	    		.append("  group by c.uid)x ")
//	    		.append("  group by x.uid");
				.append(" select x.name,x.b605,x.num,x.money from ")
				.append("(select u.uid,u.name,b.b605,count(b.uid) as num,count(b.uid)*'70' as money ")
				.append("   from user u,b06 b ")
				.append("  where u.uid=b.uid and b.rid='3' ")
				.append("  group by b.uid union ")
				.append(" select u.uid,u.name,a.a601,count(c.b101) as num,count(c.b101)*'200' as money ")
				.append("   from a06 a,user u,b03 c ")
				.append("  where u.uid=a.uid and u.uid=c.uid and a.a602='1' ")
				.append("  group by c.uid union ")
				.append(" select u.uid,u.name,a.a601,count(c.b101) as num,count(c.b101)*'200' as money ")
				.append("   from a06 a,user u,b02 c ")
				.append("  where u.uid=a.uid and u.uid=c.uid and a.a602='1' ")
				.append("  group by c.uid)x ")
				.append("  group by x.uid");
	    return DBUtils.prepareStatement(sql.toString()).executeQuery();
	}
	
	public ResultSet financialListForOuter()throws Exception
	{
		//新版本navicat需要在x.name用any_value()包被
		//union连接评审信息和答辩信息，再用group by uid分组累加获得答辩评审次数，再分配总酬金
	    StringBuilder sql=new StringBuilder()
//	    		.append(" select any_value(x.name) 专家名称,any_value(x.a601) 所属院校,any_value(x.a605) 身份证号码,any_value(x.a606) 银行账户,any_value(x.a607) 具体开户银行,")
//	    		.append("        any_value(x.a608) 手机号码,count(x.b101) 答辩评审次数,count(x.b101)*'200' 酬金 from ")
	    		.append(" select x.name 专家名称,x.a601 所属院校,x.a605 身份证号码,x.a606 银行账户,x.a607 具体开户银行,")
	    		.append("        x.a608 手机号码,count(x.b101) 答辩评审次数,count(x.b101)*'200' 酬金 from ")
	    		.append("(select n.uid,n.name,n.a601,n.b101,n.a605,n.a606,n.a607,n.a608 from ")
	    		.append("(select u.uid,u.name,a.a601,b.b101,a.a605,a.a606,a.a607,a.a608")
	    		.append("   from a06 a,user u,b02 b")
	    		.append("  where u.uid=a.uid and u.uid=b.uid and a.a602='2')n")
	    		.append("  union ")
	    		.append(" select m.uid,m.name,m.a601,m.b101,m.a605,m.a606,m.a607,m.a608 from ")
	    		.append("(select u.uid,u.name,a.a601,c.b101,a.a605,a.a606,a.a607,a.a608")
	    		.append("   from a06 a,user u,b03 c")
	    		.append("  where u.uid=a.uid and u.uid=c.uid and a.a602='2')m)x")
	    		.append("  group by uid;");
	    return DBUtils.prepareStatement(sql.toString()).executeQuery();
	}
	
	//导出评审信息---查出待评审论文和评审专家，分别作为excel表的横轴和纵轴
	//用于测量第一个结果集长度的工具
	public ResultSet OutputpreviewExcel1()throws Exception
	{
		String sql="select u.name 专家姓名 from user u,u_r_relation r where r.rid='4' and r.u_r_state='1' and u.uid=r.uid;";
	    return DBUtils.prepareStatement(sql.toString()).executeQuery();
	}
	public ResultSet OutputpreviewExcel2()throws Exception
	{
	    String sql="select u.name 专家姓名 from user u,u_r_relation r where r.rid='4' and r.u_r_state='1' and u.uid=r.uid;";
	    return DBUtils.prepareStatement(sql.toString()).executeQuery();
	}
	public ResultSet OutputpreviewExcel3()throws Exception
	{
	    String sql="select b102 from b01;";
	    return DBUtils.prepareStatement(sql.toString()).executeQuery();
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
    
    //把结果集转化为String数组
    private String[] RsToString(ResultSet rs,int length) throws Exception
    {
    	List<String> list=new ArrayList<>();
    	while(rs.next())
    	{
    		String currentRow=new String();
    		for(int i=0;i<length;i++)
    		{
    			currentRow=rs.getObject(1).toString();
    		}
    		list.add(currentRow);
    	}
    	
    	//创建一个和list长度一样的数组
    	String[] arr=new String[list.size()+1];
    	//设置第一格为空
    	arr[0]="";
    	//如果list中存入了数据，转化为数组
    	if(list != null && list.size()>0)
    	{
    		for(int i=0;i<list.size();i++)
    		{
    			//数组赋值
    			arr[i+1]=list.get(i);
    			System.out.println(arr[i]);
    		}
    	}
    	return arr;
    }
    
    public void fillExcelData2(ResultSet rs1,ResultSet rs2,ResultSet rs3,Workbook wb)throws Exception 
    {
        int rowIndex=0; //第一行
        Sheet sheet=wb.createSheet(); //创建sheet页
        Row row=sheet.createRow(rowIndex++);
        //得到rs1的长度
        int count=0;
    	while(rs1.next())
    	{
    		count++;
    	}
        System.out.println(count);
        String[] headers=RsToString(rs2, count);
        for (int i=0;i<headers.length;i++) 
        {
        	//输出专家姓名到第一行
            row.createCell(i).setCellValue(headers[i]);
        }
        //导出rs3的数据
        while(rs3.next())
        {
            row=sheet.createRow(rowIndex++);
        	//输出论文标题到第二行开始的第一列
            row.createCell(0).setCellValue(rs3.getObject(1).toString());
            System.out.println("------------");
            System.out.println(rs3.getObject(1));
        }
    }
}
