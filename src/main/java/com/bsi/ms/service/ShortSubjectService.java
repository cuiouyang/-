package com.bsi.ms.service;

import java.util.List;

import com.bsi.ms.model.shortSubject;
import com.bsi.ms.model.shortSubjectWithBLOBs;

public interface ShortSubjectService {
	int deleteByPrimaryKey(Integer problemId);

    int insert(shortSubjectWithBLOBs record);

    int insertSelective(shortSubjectWithBLOBs record);

    shortSubjectWithBLOBs selectByPrimaryKey(Integer problemId);

    int updateByPrimaryKeySelective(shortSubjectWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(shortSubjectWithBLOBs record);

    int updateByPrimaryKey(shortSubject record);
    //拿到本科目所有简答题
    List<shortSubjectWithBLOBs> selectByCourseName(String courseName);
    //删除本科目所有简答题
    int deleteByCourseName(String courseName);

}
