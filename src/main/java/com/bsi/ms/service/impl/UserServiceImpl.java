package com.bsi.ms.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bsi.ms.mapper.UserMapper;
import com.bsi.ms.model.User;
import com.bsi.ms.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;
	// 根据用账号查询人员
	@Override
	public User selectByPrimaryKey(String userId) {
		
		return mapper.selectByPrimaryKey(userId);
	}
	@Override
	public int insert(User user) {
		
		return mapper.insert(user);
	}
	@Override
	public List<User> selectBySelective(Map<String, Object> map) {
		
		return mapper.selectBySelective(map);
	}
	@Override
	public int del(String userId) {
		return mapper.deleteByPrimaryKey(userId);
	}
	@Override
	public int updateByPrimaryKeySelective(User record) {
		
		return mapper.updateByPrimaryKeySelective(record);
	}

}
