package com.bsi.ms.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bsi.ms.model.User;
import com.bsi.ms.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @ClassName: LoginController
 * @Description: 登录验证controller文件
 * @author 崔欧阳
 * @date 2017.4.19
 * 
 */
@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	
	protected static Logger logger = LogManager.getLogger(LoginController.class);
	@RequestMapping(value = "/manageSystem/index", method = { RequestMethod.GET, RequestMethod.POST })
	public String index(HttpServletRequest request, HttpServletResponse response,Model model) {
		User user  = (User) request.getSession().getAttribute("user");
			Integer type= user.getType();
			if(3==type){
				request.getSession().setAttribute("role","admin");
			}else if(2==type){
				request.getSession().setAttribute("role","teacher");
			}else if(1==type){
				request.getSession().setAttribute("role","student");
			}
		return "redirect:/userInfo/list";
	}
	
	@RequestMapping(value = "/manageSystem/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpServletRequest request, HttpServletResponse response,Model model) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "login";
	}

	@RequestMapping(value = "/manageSystem/loginFilter", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginFilter(HttpServletRequest request, HttpServletResponse response) {
		return "login";
	}

	@RequestMapping(value = "/manageSystem/login", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String login(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		if (StringUtils.isBlank(userId) || StringUtils.isBlank(password)) {
			return "nullError";
		} else {
				HttpSession session = request.getSession();
				User user = userService.selectByPrimaryKey(userId) ;
				if(password.equals(user.getPassword())){
					session.setAttribute("user", user);
					return "success";
				}
				return "pwdError";
				
			} 
				
			}
		
	}
