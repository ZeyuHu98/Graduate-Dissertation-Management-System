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
		//??????????????????????syscode????????????????
		//??rid????????????????????????????????????union????????????????????????
		StringBuilder sql=new StringBuilder()
				.append("select b.b603 ????????,b.b602 ????????,b.b604 ????,")
				.append("       u.name ????,s.fvalue ????,a.a601 ????,'200' as '????'")
				.append("  from a06 a,b06 b,user u,syscode s")
				.append(" where b.uid=u.uid and a.uid=u.uid")
				.append("   and s.fname='a604' and b.a604=s.fcode")
				.append("   and rid in('4','5')")
				.append(" union ")
				.append("select b.b603 ????????,b.b602 ????????,b.b604 ????,")
				.append("       u.name ????,s.fvalue ????,a.a601 ????,'70' as '????'")
				.append("  from a06 a,b06 b,user u,syscode s")
				.append(" where b.uid=u.uid and a.uid=u.uid")
				.append("   and s.fname='a604' and b.a604=s.fcode")
				.append("   and rid='3'");
	    return DBUtils.prepareStatement(sql.toString()).executeQuery();
	}

	public ResultSet financialListForInner()throws Exception
	{
		//??????navicat??????x.name??any_value()????
		//union??????????????????????????????????????group by uid????????????????????????????????????
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
		//??????navicat??????x.name??any_value()????
		//union????????????????????????????group by uid??????????????????????????????????????
	    StringBuilder sql=new StringBuilder()
//	    		.append(" select any_value(x.name) ????????,any_value(x.a601) ????????,any_value(x.a605) ??????????,any_value(x.a606) ????????,any_value(x.a607) ????????????,")
//	    		.append("        any_value(x.a608) ????????,count(x.b101) ????????????,count(x.b101)*'200' ???? from ")
	    		.append(" select x.name ????????,x.a601 ????????,x.a605 ??????????,x.a606 ????????,x.a607 ????????????,")
	    		.append("        x.a608 ????????,count(x.b101) ????????????,count(x.b101)*'200' ???? from ")
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
	
	//????????????---??????????????????????????????????excel??????????????
	//??????????????????????????????
	public ResultSet OutputpreviewExcel1()throws Exception
	{
		String sql="select u.name ???????? from user u,u_r_relation r where r.rid='4' and r.u_r_state='1' and u.uid=r.uid;";
	    return DBUtils.prepareStatement(sql.toString()).executeQuery();
	}
	public ResultSet OutputpreviewExcel2()throws Exception
	{
	    String sql="select u.name ???????? from user u,u_r_relation r where r.rid='4' and r.u_r_state='1' and u.uid=r.uid;";
	    return DBUtils.prepareStatement(sql.toString()).executeQuery();
	}
	public ResultSet OutputpreviewExcel3()throws Exception
	{
	    String sql="select b102 from b01;";
	    return DBUtils.prepareStatement(sql.toString()).executeQuery();
	}
	
	
    /**
     * ????????
     * 
     * @throws Exception
     */
    public void fillExcelData(ResultSet rs,Workbook wb,String[]headers)throws Exception 
    {
        int rowIndex=0; //??????
        Sheet sheet=wb.createSheet(); //????sheet??
        Row row=sheet.createRow(rowIndex++);
        //????????
        for (int i=0;i<headers.length;i++) 
        {
            row.createCell(i).setCellValue(headers[i]);
        }
        //??????????????????
        while(rs.next())
        {
            row=sheet.createRow(rowIndex++);
            for (int i=0;i<headers.length;i++)
            {
            	//rs.getObject(i + 1)????????????????????????????????????????????????????????????????????????id????1????????????+1??
                row.createCell(i).setCellValue(rs.getObject(i+1).toString());
            }
        }
    }
    
    //??????????????String????
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
    	
    	//??????????list??????????????
    	String[] arr=new String[list.size()+1];
    	//??????????????
    	arr[0]="";
    	//????list????????????????????????
    	if(list != null && list.size()>0)
    	{
    		for(int i=0;i<list.size();i++)
    		{
    			//????????
    			arr[i+1]=list.get(i);
    			System.out.println(arr[i]);
    		}
    	}
    	return arr;
    }
    
    public void fillExcelData2(ResultSet rs1,ResultSet rs2,ResultSet rs3,Workbook wb)throws Exception 
    {
        int rowIndex=0; //??????
        Sheet sheet=wb.createSheet(); //????sheet??
        Row row=sheet.createRow(rowIndex++);
        //????rs1??????
        int count=0;
    	while(rs1.next())
    	{
    		count++;
    	}
        System.out.println(count);
        String[] headers=RsToString(rs2, count);
        for (int i=0;i<headers.length;i++) 
        {
        	//????????????????????
            row.createCell(i).setCellValue(headers[i]);
        }
        //????rs3??????
        while(rs3.next())
        {
            row=sheet.createRow(rowIndex++);
        	//????????????????????????????????
            row.createCell(0).setCellValue(rs3.getObject(1).toString());
            System.out.println("------------");
            System.out.println(rs3.getObject(1));
        }
    }
}
