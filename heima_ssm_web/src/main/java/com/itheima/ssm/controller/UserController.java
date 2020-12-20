package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private IUserService userService;

    //给用户添加角色 addRoleToUser
    //给用户添加角色
    @RequestMapping("/addRoleToUser.do")                                                                    // 第一个参数  用户id  第二个参数 你可以添加的角色ID
    public String addRoleToUser(@RequestParam(name = "userId") Integer userId, @RequestParam(name = "roleIds") Integer[] roleIds) throws Exception {
       // userService.addRoleToUser(userId, roleIds);
        userService.addRoleToUser(userId,roleIds);
        return "redirect:findAll.do";
    }


    //查询用户以及用户可以添加的角色
    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView  findUserByIdAndAllRole(
            @RequestParam(name="id",required = true) String userId) throws Exception{

        ModelAndView mv = new ModelAndView();
        //1，根据用户id查用户
        UserInfo userInfo = userService.findById(userId);

        //2，根据用户id查询可以添加的角色 (角色可能很多所以用list)
        // ( 排除掉了该userid的用户本身所带有的角色  ）
        //我们这个方法可以将这个用户可以添加的其他角色查询出来
        List<Role> otherRoles = userService.findOtherRoles(userId);
        mv.addObject("user",userInfo);
        mv.addObject("roleList",otherRoles);
        mv.setViewName("user-role-add");
        return  mv;



    }




    //查询指定id的用户
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception {
        ModelAndView mv = new ModelAndView();
       UserInfo userInfo = userService.findById(id);
       mv.addObject("user" , userInfo);
       mv.setViewName("user-show1");
        return mv;
    }



    //查询所有用户
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> list = userService.findAll();
       mv.addObject("userList",list);
       mv.setViewName("user-list");
        return  mv;
    }
    //添加用户
    @RequestMapping("/save.do")
    public String save(UserInfo userInfo) throws Exception {
        userService.save(userInfo);

        return  "redirect:findAll.do";
    }



}
