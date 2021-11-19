package edu.whu.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.whu.services.impl.MessageServicesImpl;
import edu.whu.web.support.BaseControllerSupport;


@WebServlet(urlPatterns = "*.html",name="BaseServlet")
public class BaseServlet extends HttpServlet 
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		 String toPath=null;   //跳转的目标页面
         try
         {
        	 
        	/************************************************************
        	 *      解析访问路径,获取目标类的名称
        	 ************************************************************/
     		//拦截请求的访问路径
     		String  uri=request.getRequestURI();
     		//获取请求资源的主文件名
     		String baseName=uri.substring(uri.lastIndexOf("/")+1).replace(".html", "");
     		
     		//定义变量,描述所有业务控制器的基础包名称
     		String basePackageName="edu.whu.web.impl.";
     		//获取控制器的前缀名
     		String controllerFirstName=baseName.substring(0,1).toUpperCase()+baseName.substring(1);
     		
     		/***********************************************************
     		 *                        实例化目标类---业务控制器
     		 ***********************************************************/
     		//实例化业务控制器
     		BaseControllerSupport controller=(BaseControllerSupport)Class.forName(basePackageName+controllerFirstName+"Servlet").newInstance();
     		
     		/***********************************************************
     		 *                        向业务控制器,填充页面数据     i
     		 ***********************************************************/
     		//为业务控制器织入DTO切片
     		request.setAttribute("cuid", request.getSession().getAttribute("cuid"));
     		request.setAttribute("crolelist",request.getSession().getAttribute("crolelist"));

     		controller.setDto(this.createDto(request));
     		
     		/***********************************************************
     		 *                        调用业务控制器的流程控制方法
     		 ***********************************************************/
     		//调用流程控制方法
     		toPath=controller.execute();

     		/***********************************************************
     		 *                      处理控制器向页面输出的数据     o
     		 ***********************************************************/
     		//解析属性
     		Map<String,Object> rueqestAttribute=controller.getAttribute();
     		//织入属性处理切片
     		this.parseRueqestAttribute(request, rueqestAttribute);
         }	
         catch(Exception ex)
         {
        	 request.setAttribute("msg", "提示:网络故障!");
        	 ex.printStackTrace();
         }
		request.getRequestDispatcher("/"+toPath+".jsp").forward(request, response);
	}
	
	
	private void parseRueqestAttribute(HttpServletRequest request,Map<String,Object> rueqestAttribute)
	{
		//1.还原所有的键值对,形成集合
		Set<Map.Entry<String, Object>> entrySet=rueqestAttribute.entrySet();
		//2.循环集合
		for(Map.Entry<String, Object> entry:entrySet)
		{
			//3.将map的每个键值对,转换成request的属性
			request.setAttribute(entry.getKey(), entry.getValue());
		}
		//清除所有的request级属性数据
		rueqestAttribute.clear();
	}
	
	
	/**
	 *  DTO切片
	 * @param request
	 * @return
	 */
	protected  Map<String,Object> createDto(HttpServletRequest request)
	{
		//1.获取页面数据
		Map<String,String[]> tem=request.getParameterMap();

		int initSize=((int)(tem.size()/0.75))+1;
		//2.导出所有键值对,形成键值对集合
		Set<Entry<String,String[]>> entrySet=tem.entrySet();
		//3.定义数组,表示Enetry的value部分
		String value[]=null;
		
		//4.定义DTO容器
		Map<String,Object> dto=new HashMap<>(initSize);
		//5.循环读取entrySet,获取每个键值对
		for(Entry<String,String[]> entry :entrySet)
		{
			//获取value部分的数组
			value=entry.getValue();
			//依据长度判断页面控件的类别
			if(value.length==1)  //非checkbox类控件
			{
		        //过滤掉页面未填充项目
				if(value[0]!=null && !value[0].equals(""))
				{
					dto.put(entry.getKey(), value[0]);
				}
			}
			else     //checkbox类控件
			{
				dto.put(entry.getKey(), value);
			}	
		}
		//System.out.println(dto);
		
		dto.put("cuid", request.getAttribute("cuid"));
		dto.put("crolelist", request.getAttribute("crolelist"));
		
		return dto;
	}

	protected void getMessage(HttpServletRequest request) throws Exception
	{
		MessageServicesImpl services=new MessageServicesImpl();
		
		Map<String,List<String>> messageContent=new HashMap<String,List<String>>();
		
		List<Map<String,String>> messageList=new ArrayList<>();
		String uid=(String) request.getSession().getAttribute("cuid");
		Map<String,List<String>> map=services.getMessage(uid);
		
		for(String key:map.keySet())
		{
			Map<String,String> temp=new HashMap<String,String>();
			
			temp.put("time", map.get(key).get(1));
			
			temp.put("href", map.get(key).get(0));
			//是否已读
			System.out.println(map.get(key).get(4));
			temp.put("state", (map.get(key).get(4).equals("1"))?"已读":"未读");
			List<String> list=new ArrayList<String>();
			//时间
			list.add(map.get(key).get(1));
			//标题
			list.add(map.get(key).get(2));
			//内容
			list.add(map.get(key).get(3));
			//是否已读
			list.add(map.get(key).get(4));
			messageList.add(temp);
			messageContent.put(key, list);
			System.out.println("href="+temp.get("href"));
		}
		//消息列表
		request.getSession().setAttribute("messageList", messageList);
		//消息内容
		request.getSession().setAttribute("messageContent", messageContent);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		this.doGet(request, response);
	}

}
