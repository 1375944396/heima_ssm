package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Permission;

import java.util.List;

public interface PermissionDao {

    List<Permission> findAll() throws Exception;

    void save(Permission permission) throws Exception;;
}
