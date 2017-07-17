package com.bsi.ms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsi.ms.mapper.ResultsMapper;
import com.bsi.ms.model.Results;
import com.bsi.ms.model.ResultsKey;
import com.bsi.ms.service.ResultsService;

@Service
public class ResultsServiceImpl implements ResultsService {
	@Autowired
	private ResultsMapper mapper;

	@Override
	public int deleteByPrimaryKey(ResultsKey key) {
		return mapper.deleteByPrimaryKey(key);
	}

	@Override
	public int insert(Results record) {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(Results record) {
		return mapper.insertSelective(record);
	}

	@Override
	public Results selectByPrimaryKey(ResultsKey key) {
		return mapper.selectByPrimaryKey(key);
	}

	@Override
	public int updateByPrimaryKeySelective(Results record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Results record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Results> selectByMap(Map<String, Object> map) {
		return mapper.selectByMap(map);
	}

}
