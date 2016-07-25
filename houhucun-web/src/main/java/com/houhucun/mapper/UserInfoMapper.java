package com.houhucun.mapper;

import java.util.List;

import com.houhucun.domain.UserInfo;
import com.houhucun.domain.UserInfoQueryVO;

public interface UserInfoMapper {
	int deleteByPrimaryKey(int userId);

	int insert(UserInfo record);

	int insertSelective(UserInfo record);

	UserInfo selectByPrimaryKey(int userId);

	UserInfo selectByAccount(String account);

	int updateByPrimaryKeySelective(UserInfo record);

	int updateByPrimaryKey(UserInfo record);

	List<UserInfo> findByParam(UserInfoQueryVO query);

	int countUserByParam(UserInfoQueryVO query);

	UserInfo checkUser(UserInfo record);

	int updatePwd(UserInfo record);

}