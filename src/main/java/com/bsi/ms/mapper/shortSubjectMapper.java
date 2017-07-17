package com.bsi.ms.mapper;

import java.util.List;

import com.bsi.ms.model.shortSubject;
import com.bsi.ms.model.shortSubjectWithBLOBs;

public interface shortSubjectMapper {
    int deleteByPrimaryKey(Integer problemId);

    int insert(shortSubjectWithBLOBs record);

    int insertSelective(shortSubjectWithBLOBs record);

    shortSubjectWithBLOBs selectByPrimaryKey(Integer problemId);

    int updateByPrimaryKeySelective(shortSubjectWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(shortSubjectWithBLOBs record);

    int updateByPrimaryKey(shortSubject record);
    
    List<shortSubjectWithBLOBs> selectByCourseName(String courseName);
    
    int deleteByCourseName(String courseName);
}