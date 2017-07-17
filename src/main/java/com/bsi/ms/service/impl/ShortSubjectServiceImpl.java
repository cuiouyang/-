package com.bsi.ms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsi.ms.mapper.shortSubjectMapper;
import com.bsi.ms.model.shortSubject;
import com.bsi.ms.model.shortSubjectWithBLOBs;
import com.bsi.ms.service.ShortSubjectService;
@Service
public class ShortSubjectServiceImpl  implements ShortSubjectService{
	@Autowired
	private shortSubjectMapper mapper;
	@Override
	public int deleteByPrimaryKey(Integer problemId) {
		
		return mapper.deleteByPrimaryKey(problemId);
	}

	@Override
	public int insert(shortSubjectWithBLOBs record) {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(shortSubjectWithBLOBs record) {
		return mapper.insertSelective(record);
	}

	@Override
	public shortSubjectWithBLOBs selectByPrimaryKey(Integer problemId) {
		return mapper.selectByPrimaryKey(problemId);
	}

	@Override
	public int updateByPrimaryKeySelective(shortSubjectWithBLOBs record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(shortSubjectWithBLOBs record) {
		return mapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(shortSubject record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<shortSubjectWithBLOBs> selectByCourseName(String courseName) {
		return mapper.selectByCourseName(courseName);
	}

	@Override
	public int deleteByCourseName(String courseName) {
		return mapper.deleteByCourseName(courseName);
	}

}
