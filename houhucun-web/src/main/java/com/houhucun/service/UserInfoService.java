package com.houhucun.service;

import java.util.List;

import com.houhucun.domain.UserInfo;
import com.houhucun.domain.UserInfoQueryVO;

public interface UserInfoService {

	List<UserInfo> getUserInfo(UserInfoQueryVO query);

	boolean addUserInfo(UserInfo userInfo);

	boolean delUserInfo(int userId);

	int countUser(UserInfoQueryVO query);

	boolean updateUserInfo(UserInfo userInfo);

	UserInfo getUserInfoById(int userId);

	UserInfo getUserInfoByAccount(String account);

	UserInfo checkUser(UserInfo userInfo);

	boolean updateUserInfoPwd(UserInfo userInfo);
}
