package com.bsi.ms.service;

import java.util.List;
import java.util.Map;

import com.bsi.ms.model.Course;
import com.bsi.ms.model.Problem;
import com.bsi.ms.model.ProblemWithBLOBs;

public interface ProblemService {
	
	int deleteByPrimaryKey(Integer problemId);
   
    int insert(ProblemWithBLOBs record);

    int insertSelective(ProblemWithBLOBs record);

    ProblemWithBLOBs selectByPrimaryKey(Integer problemId);

    int updateByPrimaryKeySelective(ProblemWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ProblemWithBLOBs record);

    int updateByPrimaryKey(Problem record);
    //查条件试题（空则查所有）
    List<ProblemWithBLOBs> selectByAll(Map<String,Object> map);
    //获取所有科目名字
    List<Course> selectByAllCourse();
}
