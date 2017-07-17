package com.bsi.ms.mapper;

import com.bsi.ms.model.TestSet;

public interface TestSetMapper {
    int deleteByPrimaryKey(String courseName);

    int insert(TestSet record);

    int insertSelective(TestSet record);

    TestSet selectByPrimaryKey(String courseName);

    int updateByPrimaryKeySelective(TestSet record);

    int updateByPrimaryKey(TestSet record);
}