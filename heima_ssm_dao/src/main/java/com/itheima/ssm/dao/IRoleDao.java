package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IRoleDao {


    public List<Role> findAll();

    void save(Role role);

    Role findById(Integer roleId);

    List<Permission> findOtherPermissions(Integer roleId);

    void addPermissionToRole(@Param("roleId")Integer roleId,@Param("permissionId")Integer permissionId);
}
