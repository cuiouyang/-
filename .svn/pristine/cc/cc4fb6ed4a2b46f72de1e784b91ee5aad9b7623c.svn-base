package com.bsi.ms.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RouterFilter implements Filter {
	private Map<Integer,List<String>> includeMap = new HashMap<Integer,List<String>>(5);

	/**
	 * @param exclude
	 */
	private void setIncludeMap() {
		List<String> list = new ArrayList<String>();
		list.add("/partakeitems/list");
		list.add("/project/list");
		list.add("/items/newAddItems");
		includeMap.put(0, list);
		List<String> list1 = new ArrayList<String>();
		list1.add("/workItem/addWorkItem");
		list1.add("/workItem/list");
		list1.add("/workItem/index");
		includeMap.put(1, list1);
	}

	private int getKey(String uri){
		for(Entry<Integer, List<String>> entry:includeMap.entrySet()){
			int key = entry.getKey();
			List<String> value = entry.getValue();
			if(value.contains(uri)){
				return key;
			}
		}
		return 0;
	}
	
	private boolean containsValue(String uri){
		for(Entry<Integer, List<String>> entry:includeMap.entrySet()){
			List<String> value = entry.getValue();
			if(value.contains(uri)){
				return true;
			}
		}
		return false;
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
		// 获得session中的对象
		HttpSession session = req.getSession();
		if(containsValue(requestUri)){
			int buttonId = getKey(requestUri);
			session.setAttribute("currentMenu", buttonId);
			session.setAttribute("currentURL", requestUri);
		}
		// 已经登陆,继续此次请求
		chain.doFilter(req, res);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		setIncludeMap();
	}
}