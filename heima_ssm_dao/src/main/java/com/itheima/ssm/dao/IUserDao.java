package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {

    public UserInfo findByUsername(String username) throws Exception;


    List<UserInfo> findAll() throws Exception;

    void save(UserInfo userInfo) throws Exception;

    UserInfo findById(String id) throws Exception;


    List<Role> findOtherRoles(String userId) throws Exception;

    //@Param("orId") String orId
    //这里orId是前端传过来的参数名，不加@Param("orId")  默认就是找orId，
    //也可以@Param("orId")  String nb，前端传入的orId参数的值就赋值到nb中
//不使用@Param注解时，参数只能有一个，
// 并且是Javabean。在SQL语句里可以引用JavaBean的属性，
// 而且只能引用JavaBean的属性。
    void addRoleToUser(@Param("userId")Integer userId, @Param("roleId")Integer roleId) throws Exception;

    @Insert("insert into users_role(userId,roleId) values(#{user_id},#{roleId})")
    void insert(@Param("user_id")Integer user_id,@Param("s")Integer s);
}
