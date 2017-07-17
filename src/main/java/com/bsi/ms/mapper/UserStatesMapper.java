package com.bsi.ms.mapper;

import com.bsi.ms.model.UserStates;
import com.bsi.ms.model.UserStatesKey;

public interface UserStatesMapper {
    int deleteByPrimaryKey(UserStatesKey key);

    int insert(UserStates record);

    int insertSelective(UserStates record);

    UserStates selectByPrimaryKey(UserStatesKey key);

    int updateByPrimaryKeySelective(UserStates record);

    int updateByPrimaryKey(UserStates record);
}