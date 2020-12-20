package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.IUserDao;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
/*这就间接地实现了UserDetailsService*/
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
            userInfo = userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //去理解我们怎么处理自己的用户对象

        //处理自己的用户对象封装成UserDetails

        //我们可以使用UserDetails的一个实现User    用这个User来封装我们自己的用户对象
        // 把我们查询出来的用户信息封装到这里面
        //这样spring-sectrty框架就可以获得我们的信息了
        //  User user=new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),getAuthority(userInfo.getRoles()));
        User user =    new User(userInfo.getUsername(),  userInfo.getPassword(), userInfo.getStatus() == 0 ? false : true, true, true, true, getAuthority(userInfo.getRoles()));
      //  User user = new User(userInfo.getUsername(),"{noop}" +userInfo.getPassword(),getAuthority(userInfo.getRoles()));
                                                //用户名                      密码                                       用户权限集合
        return user;
    }

    // 作用就是返回一个List集合，集合中装入的是角色描述（就是权限）
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }

        return  list;
    }


    @Override
    public List<UserInfo> findAll() throws Exception {
        return userDao.findAll();
    }

    @Override
    public void save(UserInfo userInfo) throws Exception {
        //对我的密码进行加密
        //bCryptPasswordEncoder.encode(userInfo.getPassword())
        // 对我的密码进行加密后在 set 回去
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        //现在下面save的密码就是加密的了
        userDao.save(userInfo);
    }

    @Override
    public UserInfo findById(String id) throws Exception {
        return userDao.findById(id);
    }

    @Override
    public List<Role> findOtherRoles(String userId) throws Exception {
        return userDao.findOtherRoles(userId);
    }

    @Override
    public void addRoleToUser(Integer userId, Integer[] roleIds) throws Exception {
        for (Integer roleId : roleIds) {
            userDao.addRoleToUser(userId, roleId);
        }
    }

    public void insert(Integer user_id, List<String> collect){
        for (String s : collect) {
           userDao.insert(user_id,Integer.valueOf(s));
        }
   }


}
