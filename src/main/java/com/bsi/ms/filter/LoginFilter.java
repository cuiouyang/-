package com.bsi.ms.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bsi.ms.model.User;

public class LoginFilter implements Filter {

	private List<Pattern> excludeList = new ArrayList<Pattern>(5);

	/**
	 * @param exclude
	 */
	private void setExcludeList(String exclude) {
		if (exclude != null) {
			String[] exStrs = exclude.split(";");
			if (exStrs != null)
				for (String exStr : exStrs)
					excludeList.add(Pattern.compile(exStr.trim()));
		}
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 判断是否在排除列表
		String ctxPath = req.getContextPath();
		String requestUri = req.getRequestURI().replaceFirst(ctxPath, "");
		boolean matcher = false;
		for (Pattern pattern : excludeList) {
			matcher = pattern.matcher(requestUri).matches();
			if (matcher) {
				chain.doFilter(req, res);
				return;
			}
		}
		// 获得session中的对象
		HttpSession session = req.getSession();
		// 获取登录用户的账户
		User user = (User)session.getAttribute("user");
		// 使用endsWith()判断url结尾的字符串
		// 如果登录用户信息不存在
		if (null == user) {
			req.getRequestDispatcher("/manageSystem/loginFilter").forward(req, res);
		} else {
			// 已经登陆,继续此次请求
			chain.doFilter(req, res);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		setExcludeList(fConfig.getInitParameter("exclude"));
	}
}