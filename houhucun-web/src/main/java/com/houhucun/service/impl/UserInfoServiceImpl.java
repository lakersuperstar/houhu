package com.houhucun.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.houhucun.domain.UserInfo;
import com.houhucun.domain.UserInfoQueryVO;
import com.houhucun.mapper.UserInfoMapper;
import com.houhucun.service.UserInfoService;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

	@Resource(name = "userInfoMapper")
	private UserInfoMapper userInfoMapper;

	@Override
	public List<UserInfo> getUserInfo(UserInfoQueryVO query) {
		return userInfoMapper.findByParam(query);
	}

	@Override
	public boolean addUserInfo(UserInfo userInfo) {
		return userInfoMapper.insert(userInfo) > 0 ? true : false;
	}

	@Override
	public boolean delUserInfo(int userId) {
		return userInfoMapper.deleteByPrimaryKey(userId) == 1 ? true : false;
	}

	@Override
	public int countUser(UserInfoQueryVO query) {
		return userInfoMapper.countUserByParam(query);
	}

	@Override
	public boolean updateUserInfo(UserInfo userInfo) {
		return userInfoMapper.updateByPrimaryKeySelective(userInfo) == 1 ? true
				: false;
	}

	@Override
	public UserInfo getUserInfoById(int userId) {
		return userInfoMapper.selectByPrimaryKey(userId);
	}

	@Override
	public UserInfo checkUser(UserInfo userInfo) {
		UserInfo checkUser = userInfoMapper.checkUser(userInfo);
		if (checkUser == null
				|| StringUtils.isBlank(checkUser.getUserAccount())
				|| StringUtils.isBlank(checkUser.getPassword())) {
			return null;
		}
		return checkUser;
	}

	@Override
	public UserInfo getUserInfoByAccount(String account) {
		return userInfoMapper.selectByAccount(account);
	}

	@Override
	public boolean updateUserInfoPwd(UserInfo userInfo) {
		return userInfoMapper.updatePwd(userInfo) == 1 ? true : false;
	}
}
