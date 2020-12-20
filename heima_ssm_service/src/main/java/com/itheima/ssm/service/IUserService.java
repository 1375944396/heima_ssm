package com.itheima.ssm.service;


import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/*
*让IUserService这个接口去扩展UserDetailsService这个接口
**/
public interface IUserService extends UserDetailsService {
    /*查询所有用户*/
    List<UserInfo> findAll() throws Exception;

    void save(UserInfo userInfo) throws Exception ;

    UserInfo findById(String id) throws Exception ;

    List<Role> findOtherRoles(String userId) throws Exception ;

    void addRoleToUser(Integer userId, Integer[] roleIds) throws Exception ;

    void insert(Integer user_id, List<String> collect);
}
