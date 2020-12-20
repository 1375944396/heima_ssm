package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Orders;


import java.util.List;

public interface IOrdersDao {

    public List<Orders> findAll() throws Exception;

    public  Orders findById(Integer id) throws Exception;
}
