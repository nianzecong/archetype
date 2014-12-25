package com.yesway.quickstart.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yesway.quickstart.entity.User;
import com.yesway.quickstart.service.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		model.addAttribute("loginName", "admin");
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String validUser(String loginName, String password, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		logger.debug("validUser {loginName:" + loginName + ",password:" + password + "}");
		boolean valid = false;
		String message = "";
		try {
			User user = userService.getUserByLoginName(loginName);
			if (user != null && user.getPassword().equals(password)) {
				valid = true;
			} else {
				message = "用户验证失败";
			}
		} catch (Exception e) {
			logger.error("validUser error:", e);
			message = "用户验证失败";
		}
		if (!valid) {
			model.addAttribute("message", message);
			return "login";
		} else {
			return "redirect:/user/list";
		}
	}

	// 查询所有用户
	@RequestMapping(value = "/list")
	public String goUserList(ModelMap model) {
		List<User> userList = null;
		try {
			userList = userService.getUserListAll();
		} catch (Exception e) {
			logger.error("goUserList error:", e);
		}
		model.addAttribute("userList", userList);
		return "userList";
	}

	// 通过userId查询用户并返回json格式，用于ajax调用
	@RequestMapping(value = "/get/{userId}")
	public @ResponseBody
	User getUserById(@PathVariable("userId") long userId) {
		logger.debug("getUserById {userId:" + userId + "}");
		User user = null;
		try {
			user = userService.getUserByUserId(userId);
		} catch (Exception e) {
			logger.error("getUserById error:", e);
		}
		return user;
	}

	// 查询所有用户并返回json格式，用于ajax调用
	@RequestMapping(value = "/getall")
	public @ResponseBody
	List<User> getUserListAll() {
		List<User> list = null;
		try {
			list = userService.getUserListAll();
		} catch (Exception e) {
			logger.error("getUserListAll error:", e);
		}
		return list;
	}

	// 删除用户并返回用户列表
	@RequestMapping(value = "/delete/{userId}")
	public String deleteUserById(@PathVariable("userId") long userId, RedirectAttributes redirectAttributes) {
		logger.debug("deleteUserById {userId:" + userId + "}");
		boolean result = false;
		try {
			int count = userService.deleteUser(userId);
			if (count > 0) {
				result = true;
			}
		} catch (Exception e) {
			logger.error("deleteUserById error:", e);
		}
		if (result) {
			redirectAttributes.addFlashAttribute("message", "删除用户成功");
		} else {
			redirectAttributes.addFlashAttribute("message", "删除用户失败");
		}
		return "redirect:/user/list";
	}
}
