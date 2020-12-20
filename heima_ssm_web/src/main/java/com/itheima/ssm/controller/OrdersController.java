package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;

    //查询全部订单---未分页
/*    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = ordersService.findAll();
        mv.addObject("ordersList", ordersList);
        mv.setViewName("orders-list");
        return mv;
    }*/

   @RequestMapping("/findAll.do")
    public ModelAndView findAll(
            /*page 当前页码   size每页显示条数*/
            @RequestParam(name = "page", required = true, defaultValue = "1") int page,
            @RequestParam(name = "size", required = true, defaultValue = "4") int size)
                                                throws Exception {
       ModelAndView mv = new ModelAndView();
       List<Orders> ordersList = ordersService.findAll(page, size);
       //注意Pagehelper 为我们提供了一个PageInfo这个类
       //PageInfo的作用就是一个分页bean （就是分页类，相当于以前的分页工具类），里面包含了分页的所需数据

       //PageInfo就是一个分页Bean
       PageInfo pageInfo = new PageInfo(ordersList);
       mv.addObject("total", pageInfo.getTotal());
       mv.addObject("last", pageInfo.getPages());//终页
       mv.addObject("size", size);//当前页
       mv.addObject("pageInfo", pageInfo);
       mv.setViewName("orders-page-list");
       return mv;

   }

       @RequestMapping("/findById.do")
       public ModelAndView findById(
               @RequestParam(name = "id", required = true) Integer id
   ) throws Exception {
           ModelAndView mv = new ModelAndView();
           Orders orders = ordersService.findById(id);
           mv.addObject("orders",orders);
           mv.setViewName("orders-show");
           return mv;
       }












    }


