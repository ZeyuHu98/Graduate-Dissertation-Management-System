package edu.whu.system.tools;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import edu.whu.system.db.DBUtils;

import java.security.MessageDigest;
import java.sql.*;

public class Tools {
	public Tools() {
	}

	public static String joinArray(Object o) {
		// 空值拦截
		if (o == null || o.equals("")) {
			return "";
		}
		// 判断element到底是什么?是字符串还是字符串数组
		if (o instanceof java.lang.String[]) {
			// 将参数转换成字符串数组
			String arr[] = (String[]) o;
			// 计算数组长度
			int len = arr.length;
			// 构造动态字符串对象,装载数组的每个元素
			StringBuilder tem = new StringBuilder(arr[0]);
			// 循环读取数组的后继元素
			for (int i = 1; i < len; i++) {
				tem.append(",").append(arr[i]);
			}
			return tem.toString();
		} else {
			return o.toString();
		}
	}

	
	/**
	 * 获取员工流水号
	 * @return  String number="yyyy"+4位流水码
	 * @throws Exception
	 */
	// 获取年码
	private static String getCurrentYear() 
	{
//		//创建简单日期格式
//		SimpleDateFormat spf= new SimpleDateFormat("yyyy");
//		//生成日期
//		Date date = new Date();
//		//用简单日期格式生成日期
//		String currentYear = spf.format(date);
//		return currentYear;

		return new SimpleDateFormat("yyyy").format(new Date());
	}

	// 获取尾码
	private static int getNumnberForYear(String firstName) throws Exception
	{
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		ResultSet set = null;
		try {
			StringBuilder sql1 = new StringBuilder();
			sql1.append("select pkvalue from sequence where date_format(sdate,'%Y')"
					+ "=date_format(current_date(),'%Y') and pkname=?");
			ps1 = DBUtils.prepareStatement(sql1.toString());
			ps1.setObject(1, firstName);
			set = ps1.executeQuery();

			int currentVal = 0;
			StringBuilder sql2 = new StringBuilder();
			if (set.next()) {
				currentVal = set.getInt(1);
				sql2.append("update sequence ").append("   set pkvalue=?")
						.append(" where date_format(sdate,'%Y')=date_format(current_date,'%Y')")
						.append("   and pkname=?");
			} else {
				sql2.append("insert into sequence(pkvalue,pkname,sdate)")
						.append("              values(?,?,current_date)");
			}
			ps2 = DBUtils.prepareStatement(sql2.toString());
			ps2.setObject(1, ++currentVal);
			ps2.setObject(2, firstName);
			ps2.executeUpdate();
			return currentVal;
		} finally {
			DBUtils.close(set);
			DBUtils.close(ps1);
			DBUtils.close(ps2);
		}
	}
	
	// 截取后几位
	public static String getFormatNumber(String rid,String firstName) throws Exception
	{
		// 1.获取流水号
		int lastNumber = Tools.getNumnberForYear(firstName);
		// 2.计算流水号的宽度
		int size = String.valueOf(lastNumber).length();

		return Tools.getCurrentYear() +rid+ new String("0000").substring(size) + lastNumber;
	}

	/***************************************************************************
	 * MD5Begin
	 ***************************************************************************/

	public static String getMd5(Object pwd) throws Exception
	{
		/**
		 * MD5二次混淆加密
		 */
		// 完成原始加密
		String md5pwd1 = Tools.MD5Encode(pwd);
		// 生成混淆明文
		String pwd2 = md5pwd1 + "隐技フャゥソツ巧ΧΤΚㄕㄣˇΒ于无形:以無にはたコをっㄘㄗㄡεωぅ法為有法,以無ㄤㄆмязр限為有限" + md5pwd1;
		String md5pwd2 = Tools.MD5Encode(pwd2);
		return md5pwd2;

	}

	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };

	/**
	 * 转换字节数组为16进制字串
	 * 
	 * @param b 字节数组
	 * @return 16进制字串
	 */
	private static String byteArrayToHexString(byte[] b) 
	{
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	/**
	 * 转换字节为16进制字符串
	 * 
	 * @param b byte
	 * @return String
	 */
	private static String byteToHexString(byte b)
	{
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	/**
	 * 得到MD5的秘文密码
	 * 
	 * @param origin String
	 * @throws Exception
	 * @return String
	 */
	private static String MD5Encode(Object origin) throws Exception
	{
		String resultString = null;
		try {
			resultString = new String(origin.toString());
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
			return resultString;
		} catch (Exception ex) {
			throw ex;
		}
	}

	public static void main(String[] arg)
	{
		try 
		{
			System.out.println(Tools.getMd5("201910001"));
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	/***************************************************************************
	 * MD5End
	 ***************************************************************************/
	public static Date addDate(Date a) throws Exception
	{
		Format f = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(a);
		c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天

		Date tomorrow = c.getTime();
		return tomorrow;
	}

	public static String outputDate(Date a) throws Exception
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		String format = sdf.format(a);
		return format;
	}

	public static long datesub(Date a, Date b) throws Exception
	{
		long betweenDate = (b.getTime() - a.getTime()) / (60 * 60 * 24 * 1000) + 1;

		return betweenDate;
	}
}
