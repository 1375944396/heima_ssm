package com.itheima.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.ssm.dao.IOrdersDao;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private IOrdersDao ordersDao;

    @Override                          // 当前页码   每页显示条数
    public List<Orders> findAll(int page,int size) throws Exception {

        //第一个参数是页码值， 第二个参数代表每页显示条数
        //紧跟着的第一个查询方法会被分页
        PageHelper.startPage(page, size);  //告诉它    这是哪一页，每页多少条
        return ordersDao.findAll();        //紧跟着的第一个查询方法会被分页


    }

    @Override
    public Orders findById(Integer id) throws Exception {
        return ordersDao.findById(id);
    }
}
