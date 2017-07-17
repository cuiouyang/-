package com.bsi.ms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bsi.ms.mapper.ProblemMapper;
import com.bsi.ms.model.Course;
import com.bsi.ms.model.Problem;
import com.bsi.ms.model.ProblemWithBLOBs;
import com.bsi.ms.service.ProblemService;
@Service
public class ProblemServiceImpl implements ProblemService {
	@Autowired
    private ProblemMapper  mapper ;
	@Override
	public int deleteByPrimaryKey(Integer problemId) {
		return mapper.deleteByPrimaryKey(problemId);
	}

	@Override
	public int insert(ProblemWithBLOBs record) {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(ProblemWithBLOBs record) {
		return mapper.insertSelective(record);
	}

	@Override
	public ProblemWithBLOBs selectByPrimaryKey(Integer problemId) {
		return mapper.selectByPrimaryKey(problemId);
	}

	@Override
	public int updateByPrimaryKeySelective(ProblemWithBLOBs record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(ProblemWithBLOBs record) {
		return mapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(Problem record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<ProblemWithBLOBs> selectByAll(Map<String,Object> map) {
		return mapper.selectByAll(map);
	}

	@Override
	public List<Course> selectByAllCourse() {
		
		return mapper.selectByAllCourse();
	}

}
