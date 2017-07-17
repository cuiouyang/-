package com.bsi.ms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsi.ms.mapper.UserStatesMapper;
import com.bsi.ms.model.UserStates;
import com.bsi.ms.model.UserStatesKey;
import com.bsi.ms.service.UserStatesService;
@Service
public class UserStatesServiceImpl implements UserStatesService {
	@Autowired
	private UserStatesMapper mapper;
	@Override
	public int deleteByPrimaryKey(UserStatesKey key) {
		return mapper.deleteByPrimaryKey(key);
	}

	@Override
	public int insert(UserStates record) {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(UserStates record) {
		return mapper.insertSelective(record);
	}

	@Override
	public UserStates selectByPrimaryKey(UserStatesKey key) {
		return mapper.selectByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKeySelective(UserStates record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(UserStates record) {
		return mapper.updateByPrimaryKey(record);
	}

}
