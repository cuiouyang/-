package com.bsi.ms.mapper;

import java.util.List;
import java.util.Map;

import com.bsi.ms.model.Course;
import com.bsi.ms.model.Problem;
import com.bsi.ms.model.ProblemWithBLOBs;

public interface ProblemMapper {
    int deleteByPrimaryKey(Integer problemId);

    int insert(ProblemWithBLOBs record);

    int insertSelective(ProblemWithBLOBs record);

    ProblemWithBLOBs selectByPrimaryKey(Integer problemId);

    int updateByPrimaryKeySelective(ProblemWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ProblemWithBLOBs record);

    int updateByPrimaryKey(Problem record);
    
    List<ProblemWithBLOBs> selectByAll(Map<String, Object> map);
    
    List<Course> selectByAllCourse();
}