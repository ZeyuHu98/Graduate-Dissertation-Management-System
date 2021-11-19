package edu.whu.web.support;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.whu.services.support.BaseServices;

public abstract class BaseControllerSupport implements BaseController{
	
	/**
	 * 封装共有方法
	 */
	
	/*****************************************
	 * 	        业务逻辑组件及架构注入
	 *****************************************/
	private BaseServices services=null;
	
	/**
	 * 子类通过该方法,为Services变量传递引用的具体类对象
	 * @param services
	 */
	
	protected void setServices(BaseServices services) {
		this.services=services;
	}
	
	protected BaseServices getServices() {
		return this.services;
	}
	
	/*****************************************
	 * 	        子类业务流程封装
	 *****************************************/
	
	/*****************************************
	 * 	        查询方法
	 *****************************************/
	
	/**
	 * 数据批量查询
	 * @throws Exception
	 */
	
	protected final void query()throws Exception{
		List<Map<String,String>> dataList=this.services.query();
		if(dataList.size()>0) {
			System.out.println(dataList);
			this.saveAttribute("dataList", dataList);
		}else {
			this.saveAttribute("msg", "没有符合条件的数据！");
		}
	}
	
	/**
	 * 单一实例 查询
	 * @throws Exception
	 */
	
	protected final void queryByID()throws Exception{
		Map<String,String> dataMap=this.services.queryByID();
		if(dataMap!=null) {
			this.saveAttribute("dataMap", dataMap);
		}else {
			this.saveAttribute("msg", "该数据已删除或禁止访问！");
		}
	}

	/*****************************************
	 * 	       更新方法
	 *****************************************/
	
	/**
	 * 更新行为的总开关
	 * <
	 *   简单消息提示
	 * >
	 * @param utype
	 * @param msgText
	 * @throws Exception
	 */
	
	/**
	 * 通过反射执行更新方法
	 * @param methodName
	 * @return
	 * @throws Exception
	 */

	private boolean executeUpdateMethod(String methodName)throws Exception{
		//1.获取需要调用的方法对象
		System.out.println(methodName);
		Method method=this.services.getClass().getDeclaredMethod(methodName);
		System.out.println(method);
		method.setAccessible(true);
		//2.调用方法
		return (boolean)method.invoke(services);
	}
	
	
	protected final boolean update(String actionName)throws Exception
	{
		return this.executeUpdateMethod(actionName);
	}
	
	
	
	protected final void update(String actionName,String msgText)throws Exception
	{
		String msg=this.executeUpdateMethod(actionName)?"成功!":"失败!";
		this.saveAttribute("msg", msgText+msg);
	}
	
	/**
	 * 带有编号的消息提示的更新行为
	 * @param utype
	 * @param typeText
	 * @param msgText
	 * @param key
	 * @throws Exception
	 */
	
	protected final void update(String actionName,String actionText,String msgText,String key)throws Exception
	{
    	String msg=actionText+"失败!";
    	if(this.executeUpdateMethod(actionName))
    	{
    		msg=msgText+"[ <msg> "+this.dto.get(key)+" </msg> ]";
    	}
    	//Servlet向页面输出数据
    	this.saveAttribute("msg", msg);
	}

	/*****************************************
	 * 	        数据输入流
	 *****************************************/
	protected Map<String,Object> dto=null;
    @Override
    public final void setDto(Map<String, Object> dto) 
    {
        this.dto=dto;
        this.services.setDto(dto);
    }
    
    protected final void showDto()
    {
    	System.out.println(this.dto);
    }

	/*****************************************
	 * 	        数据输出流
	 *****************************************/
    private Map<String,Object> attribute=new HashMap<>();
    protected final void  saveAttribute(String key,Object value)
    {
    	this.attribute.put(key, value);
    }
    
    @Override
    public final Map<String, Object> getAttribute() 
    {
    	return this.attribute;
    }
}
