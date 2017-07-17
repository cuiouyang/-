package com.bsi.ms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsi.ms.mapper.TestSetMapper;
import com.bsi.ms.model.TestSet;
import com.bsi.ms.service.TestSetService;

@Service
public class TestSetServiceImpl implements TestSetService {
	@Autowired
    private TestSetMapper  mapper ;
	@Override
	public int deleteByPrimaryKey(String courseName) {
		
		return mapper.deleteByPrimaryKey(courseName);
	}

	@Override
	public int insert(TestSet record) {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(TestSet record) {
		return mapper.insertSelective(record);
	}

	@Override
	public TestSet selectByPrimaryKey(String courseName) {
		return mapper.selectByPrimaryKey(courseName);
	}

	@Override
	public int updateByPrimaryKeySelective(TestSet record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(TestSet record) {
		return mapper.updateByPrimaryKey(record);
	}

}
