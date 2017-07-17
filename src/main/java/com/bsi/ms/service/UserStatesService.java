package com.bsi.ms.service;

import com.bsi.ms.model.UserStates;
import com.bsi.ms.model.UserStatesKey;

public interface UserStatesService {
	int deleteByPrimaryKey(UserStatesKey key);

    int insert(UserStates record);

    int insertSelective(UserStates record);

    UserStates selectByPrimaryKey(UserStatesKey key);

    int updateByPrimaryKeySelective(UserStates record);

    int updateByPrimaryKey(UserStates record);

}
