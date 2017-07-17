package com.bsi.ms.mapper;

import java.util.List;
import java.util.Map;

import com.bsi.ms.model.Test;
import com.bsi.ms.model.TestKey;

public interface TestMapper {
    int deleteByPrimaryKey(TestKey key);

    int insert(Test record);

    int insertSelective(Test record);

    Test selectByPrimaryKey(TestKey key);

    int updateByPrimaryKeySelective(Test record);

    int updateByPrimaryKeyWithBLOBs(Test record);

    int updateByPrimaryKey(Test record);
    
    List<Test> selectByMap(Map<String, Object> map);
}