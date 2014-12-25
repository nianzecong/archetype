package com.yesway.quickstart.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/*")
public class MainController {
	// 进入登录页面
	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public String goLogin(HttpServletRequest req, HttpServletResponse res, ModelMap model) {

		return "login";
	}

	// 权限验证
	@RequestMapping(value = "/login.html", method = RequestMethod.POST)
	public String login(String loginName, String password, HttpServletRequest req, HttpServletResponse res, ModelMap model) {
		UsernamePasswordToken token = new UsernamePasswordToken(loginName, password);
		Subject currentUser = SecurityUtils.getSubject();
		String errorMessage = "";
		try {
			currentUser.login(token);
			return "redirect:main.html";
		} catch (UnknownAccountException e) {
			errorMessage = "未知用户";
		} catch (IncorrectCredentialsException e) {
			errorMessage = "密码错误";
		} catch (LockedAccountException e) {
			errorMessage = "用户锁定";
		} catch (DisabledAccountException e) {
			errorMessage = "用户被禁止";
		} catch (AuthenticationException e) {
			errorMessage = "未知错误";
		}
		model.addAttribute("errorMessage", errorMessage);
		return "error";
	}

	@RequestMapping("/logout.html")
	public String logout(HttpServletRequest req, HttpServletResponse res, ModelMap model) {
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		return "redirect:login.html";
	}

	@RequestMapping("/main.html")
	public String main(HttpServletRequest req, HttpServletResponse res, ModelMap model) {

		return "main";
	}

	@RequestMapping("/unauthorized.html")
	public String unauthorized(HttpServletRequest req, HttpServletResponse res, ModelMap model) {
		// ajax访问，可修改为返回自定义json数据
		String s = req.getHeader("X-Requested-With");
		if (s != null && s.equalsIgnoreCase("XMLHttpRequest")) {
			try {
				// res.addHeader("loginStatus", "accessDenied");
				// res.sendError(403);
				res.setCharacterEncoding("UTF-8");
				res.setContentType("application/json");
				PrintWriter out = res.getWriter();
				out.println("{\"errmsg\":\"用户无权访问\"}");
				out.flush();
				out.close();
			} catch (Exception e) {

			}
			return null;
		} else {
			return "unauthorized";
		}
	}

	@RequestMapping("/account/add.html")
	public String goAccoountAdd(HttpServletRequest req, HttpServletResponse res, ModelMap model) {

		return "account/add";
	}

	@RequestMapping("/account/update.html")
	public String goAccoountUpdate(HttpServletRequest req, HttpServletResponse res, ModelMap model) {

		return "account/update";
	}

	@RequestMapping("/account/delete.html")
	@RequiresPermissions("account:delete")
	// 使用注解方式进行权限设置
	public String goAccoountDelete(HttpServletRequest req, HttpServletResponse res, ModelMap model) {

		return "account/delete";
	}

	@RequestMapping("/account/noauth.html")
	public String goAccoountNoauth(HttpServletRequest req, HttpServletResponse res, ModelMap model) {

		return "account/noauth";
	}

	@RequestMapping("/account/authc.html")
	public String goAccoountAuthc(HttpServletRequest req, HttpServletResponse res, ModelMap model) {

		return "account/authc";
	}

	@RequestMapping("/account/visit")
	public @ResponseBody
	Map<String, Object> visit(HttpServletRequest req, HttpServletResponse res, ModelMap model) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		return map;
	}

}
