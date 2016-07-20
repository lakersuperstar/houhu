package com.houhucun.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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

	@Resource(name = "userInfoService")
	private UserInfoService userInfoService;

	@Resource(name="cookieService")
	private CookieService cookieService;
	@RequestMapping("list")
	public String getUserInfo(HttpServletResponse response,
			HttpServletRequest request,ModelMap map, UserInfoQueryVO userInfoQuery) {
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
	public Object add(UserInfo userInfo) {
		userInfo.setPassword(UserInitUtil.getInitPassword());
		return userInfoService.addUserInfo(userInfo);
	}

	@RequestMapping("reset")
	@ResponseBody
	public Object resetPassword(UserInfo userInfo) {
		userInfo.setPassword(UserInitUtil.getInitPassword());
		return userInfoService.addUserInfo(userInfo);
	}

	@RequestMapping("updatePwd")
	@ResponseBody
	public Object updatePassword(UserInfo userInfo) {
		userInfo.setPassword(UserInitUtil.getPassword(userInfo.getPassword()));
		return userInfoService.addUserInfo(userInfo);
	}

	@RequestMapping("update")
	@ResponseBody
	public Object update(UserInfo userInfo) {
		userInfo.setYn(1);
		return userInfoService.updateUserInfo(userInfo);
	}

	@RequestMapping("get")
	@ResponseBody
	public Object get(@RequestParam(value = "userId") int userId) {
		return userInfoService.getUserInfoById(userId);
	}

	@RequestMapping("del")
	@ResponseBody
	public Object del(UserInfo userInfo) {
		UserInfo userInfoNew = new UserInfo();
		userInfoNew.setUserId(userInfo.getUserId());
		userInfoNew.setYn(userInfo.getYn());
		if (userInfoService.delUserInfo(userInfo.getUserId())) {
			return "ok";
		}
		return "error";

	}
}
