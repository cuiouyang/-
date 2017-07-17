package com.bsi.ms.mapper;

import java.util.List;
import java.util.Map;

import com.bsi.ms.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    //根据条件查找用户
    List<User> selectBySelective(Map<String,Object> map);
}