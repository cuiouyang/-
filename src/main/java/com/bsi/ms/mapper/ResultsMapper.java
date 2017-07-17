package com.bsi.ms.mapper;

import java.util.List;
import java.util.Map;

import com.bsi.ms.model.Results;
import com.bsi.ms.model.ResultsKey;

public interface ResultsMapper {
    int deleteByPrimaryKey(ResultsKey key);

    int insert(Results record);

    int insertSelective(Results record);

    Results selectByPrimaryKey(ResultsKey key);

    int updateByPrimaryKeySelective(Results record);

    int updateByPrimaryKey(Results record);
    
    List<Results> selectByMap(Map<String, Object> map);
}