package com.houhucun.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.houhucun.controller.vo.Page;
import com.houhucun.domain.UserInfo;
import com.houhucun.domain.UserInfoQueryVO;
import com.houhucun.service.CookieService;
import com.houhucun.service.UserInfoService;
import com.houhucun.util.UserInitUtil;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

	private static Logger LOGGER = LoggerFactory
			.getLogger(UserInfoController.class);

	@Resource(name = "userInfoService")
	private UserInfoService userInfoService;

	@Resource(name = "cookieService")
	private CookieService cookieService;

	@RequestMapping("list")
	public String getUserInfo(HttpServletResponse response,
			HttpServletRequest request, ModelMap map,
			UserInfoQueryVO userInfoQuery) {

		String usr = cookieService.getCookieUser(request);
		String role = cookieService.getUserRole(request);

		if (Integer.valueOf(role) != 1) {
			userInfoQuery.setCreateAccount(usr);
		}

		int counts = userInfoService.countUser(userInfoQuery);
		Page page = new Page();
		if (userInfoQuery.getPageNo() == 0) {
			page.setPageNo(1);
		} else {
			page.setPageNo(userInfoQuery.getPageNo());
		}
		userInfoQuery.setPageSize(page.getSize());
		userInfoQuery.setNowPage();
		page.setTotalRecord(counts);
		List<UserInfo> userInfos = userInfoService.getUserInfo(userInfoQuery);
		map.put("userInfos", userInfos);
		map.put("pageBut", page.sizeToString(userInfoQuery.getFunctionName()));
		map.put("userInfoQuery", userInfoQuery);
		String user = cookieService.getCookieUser(request);
		UserInfo ui = userInfoService.getUserInfoByAccount(user);
		map.put("nowuser", ui);
		return "/userinfo/userlist";
	}

	@RequestMapping("add")
	@ResponseBody
	public Object add(UserInfo userInfo, HttpServletRequest request) {
		try {
			String usr = cookieService.getCookieUser(request);
			userInfo.setCreateAccount(usr);
			userInfo.setPassword(UserInitUtil.getInitPassword());
			return userInfoService.addUserInfo(userInfo);
		} catch (Exception e) {
			LOGGER.error(userInfo.getUserAccount() + "添加失败!", e);
			if (e.getMessage().contains("Duplicate entry")) {
				return userInfo.getUserAccount() + "账号已经存在，请重新填写账号!";
			} else {
				return "添加失败.";
			}

		}

	}

	@RequestMapping("reset")
	@ResponseBody
	public Object resetPassword(UserInfo userInfo) {
		try {
			userInfo.setPassword(UserInitUtil.getInitPassword());
			if (userInfoService.updateUserInfoPwd(userInfo)) {
				return "ok";
			}
		} catch (Exception e) {
			LOGGER.error(userInfo.getUserId() + "重置密码失败!", e);
			return "error";
		}
		return "error";
	}

	@RequestMapping("preupdatePwd")
	public Object preupdatePassword(ModelMap map, UserInfo userInfo,
			HttpServletRequest request) {
		return "/userinfo/updatepwd";
	}

	@RequestMapping("updatePwd")
	@ResponseBody
	public Object updatePassword(UserInfo userInfo, HttpServletRequest request) {
		String usr = cookieService.getCookieUser(request);
		UserInfo userOld = userInfoService.getUserInfoByAccount(usr);
		try {
			if (userInfo == null || userInfo.getPassword() == null) {
				return "error";
			}
			if (userOld.getPassword().equals(
					UserInitUtil.getPassword(userInfo.getPassword()))) {
				userOld.setPassword(UserInitUtil.getPassword(userInfo
						.getNewPassword()));
				boolean flag = userInfoService.updateUserInfoPwd(userOld);
				if (flag) {
					return "ok";
				} else {
					return "error";
				}
			} else {
				return "error";
			}
		} catch (Exception e) {
			LOGGER.error(usr + "更新密码失败", e);
			return "error";
		}
	}

	@RequestMapping("update")
	@ResponseBody
	public Object update(UserInfo userInfo) {
		try {
			userInfo.setYn(1);
			return userInfoService.updateUserInfo(userInfo);
		} catch (Exception e) {
			LOGGER.error(userInfo.getUserAccount() + "更新成功！", e);
			return "false";
		}
	}

	@RequestMapping("get")
	@ResponseBody
	public Object get(@RequestParam(value = "userId") int userId) {
		return userInfoService.getUserInfoById(userId);
	}

	@RequestMapping("getInfo")
	public String getInfo(HttpServletRequest request, ModelMap map) {
		map.put("userInfos", userInfoService.getUserInfoByAccount(cookieService
				.getCookieUser(request)));
		return "/userinfo/userInfo";
	}

	@RequestMapping("del")
	@ResponseBody
	public Object del(UserInfo userInfo) {
		if (userInfo == null || userInfo.getUserId() <= 0) {
			LOGGER.error("传入的用户为空，删除失败!");
			return "error";
		}
		try {
			UserInfo userInfoNew = new UserInfo();
			userInfoNew.setUserId(userInfo.getUserId());
			userInfoNew.setYn(userInfo.getYn());
			if (userInfoService.delUserInfo(userInfo.getUserId())) {
				return "ok";
			}
		} catch (Exception e) {
			LOGGER.error(userInfo.getUserId() + "删除失败!", e);
		}
		return "error";

	}
}
