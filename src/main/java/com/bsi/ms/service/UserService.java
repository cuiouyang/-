package com.bsi.ms.service;


import java.util.List;
import java.util.Map;

import com.bsi.ms.model.User;

public interface UserService {
	//根据登陆界面输入的账号 查出该账号的所有信息
	User selectByPrimaryKey(String userId);
	//保存用户信息
	int insert(User user);
	   //根据条件查找用户
    List<User> selectBySelective(Map<String,Object> map);
	//删除指定用户
    int del(String userId);
    //编辑项目
    int updateByPrimaryKeySelective(User record);

}