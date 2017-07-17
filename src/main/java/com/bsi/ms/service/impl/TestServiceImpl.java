package com.bsi.ms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsi.ms.mapper.TestMapper;
import com.bsi.ms.model.Test;
import com.bsi.ms.model.TestKey;
import com.bsi.ms.service.TestService;

@Service
public class TestServiceImpl implements TestService {
	@Autowired
	private TestMapper mapper;
	@Override
	public int deleteByPrimaryKey(TestKey key) {
		return mapper.deleteByPrimaryKey(key);
	}

	@Override
	public int insert(Test record) {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(Test record) {
		return mapper.insertSelective(record);
	}

	@Override
	public Test selectByPrimaryKey(TestKey key) {
		return mapper.selectByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKeySelective(Test record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(Test record) {
		return mapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(Test record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Test> selectByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}

}
