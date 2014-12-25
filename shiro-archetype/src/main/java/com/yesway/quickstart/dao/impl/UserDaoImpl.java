package com.yesway.quickstart.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yesway.quickstart.dao.UserDao;
import com.yesway.quickstart.entity.User;

@Service
public class UserDaoImpl implements UserDao {

	@Override
	public User getUserByLoginName(String loginName) {
		User user = null;
		if ("admin".equalsIgnoreCase(loginName)) {
			user = new User();
			user.setUserId(1);
			user.setLoginName("admin");
			user.setFullName("管理员");
			user.setPassword("admin");
			user.setAddTime(new Date());

			user.setIsEnabled(true);
			user.setIsLocked(false);
			user.setRoleName("admin");

			List<String> permissionList = new ArrayList<String>();
			permissionList.add("account:add");
			permissionList.add("account:update");
			permissionList.add("account:delete");
			permissionList.add("account:ajax");
			
			user.setPermissionList(permissionList);
		} else if ("user".equalsIgnoreCase(loginName)) {
			user = new User();
			user.setUserId(1);
			user.setLoginName("user");
			user.setFullName("用户");
			user.setPassword("user");
			user.setAddTime(new Date());

			user.setIsEnabled(true);
			user.setIsLocked(false);
			user.setRoleName("user");

			List<String> permissionList = new ArrayList<String>();
			permissionList.add("account:add");
			permissionList.add("account:update");
			
			user.setPermissionList(permissionList);
		}
		return user;
	}

}
