package com.houhucun.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.houhucun.controller.vo.Page;
import com.houhucun.domain.UserInfo;
import com.houhucun.domain.UserInfoQueryVO;
import com.houhucun.service.UserInfoService;
import com.houhucun.util.UserInitUtil;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

	@Resource(name = "userInfoService")
	private UserInfoService userInfoService;

	@RequestMapping("list")
	public String getUserInfo(ModelMap map, UserInfoQueryVO userInfoQuery) {

		// userInfoQuery.setStartrownum((userInfoQuery.getPageNo() - 1)
		// * userInfoQuery.getPageSize());
		// userInfoQuery.setEndrownum(userInfoQuery.getPageSize());

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
		return "/userinfo/userlist";
	}

	@RequestMapping("add")
	@ResponseBody
	public Object add(UserInfo userInfo) {
		userInfo.setPassword(UserInitUtil.getInitPassword());
		return userInfoService.addUserInfo(userInfo);
	}

	@RequestMapping("del")
	@ResponseBody
	public Object del(UserInfo userInfo) {
		UserInfo userInfoNew = new UserInfo();
		userInfoNew.setUserId(userInfo.getUserId());
		userInfoNew.setYn(userInfo.getYn());
		if (userInfoService.delUserInfo(userInfo.getUserId())) {
			return "OK";
		}
		return "error";

	}
}
