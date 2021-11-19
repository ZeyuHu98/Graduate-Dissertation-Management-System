package edu.whu.system.tools;

import java.io.IOException;
 
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class RedirectFilter
 */
@WebFilter(servletNames={"SecQueryServlet"})
public class RedirectFilter implements Filter 
{
	public void destroy() 
	{
		
	}
 
	public void doFilter(ServletRequest servletRequest,ServletResponse servletResponse, FilterChain filterChain)throws IOException, ServletException 
	{
		HttpServletRequest req = (HttpServletRequest) servletRequest;
		HttpSession session = req.getSession();
		//获取cuid
		String cuid = (String)session.getAttribute("cuid");
		System.out.println("RedirectFilter:cuid = "+cuid);
		if (cuid != null && cuid!="") 
		{
			// 如果现在用户已经登录，则请求向下继续传递
			filterChain.doFilter(servletRequest, servletResponse);
		}
		else 
		{
			// 跳转到提示登陆页面
			servletRequest.getRequestDispatcher("/redirect.jsp").forward(servletRequest, servletResponse);
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException 
	{
		
	}
}
