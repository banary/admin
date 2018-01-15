package com.banary.admin.service;

import com.banary.admin.entity.User;
import com.banary.admin.entity.UserExample;
import com.banary.admin.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据用户名count用户
     *
     * @author xiyongchun
     * @date 2017/7/8 上午1:35
     * @param
     * @return
     */
    public int countUserWithUserName(String userName){
        UserExample example = new UserExample();
        if(!StringUtils.isEmpty(userName)){
            example.createCriteria().andUserNameLike(userName + "%");
        }
        return this.userMapper.countByExample(example);
    }

    /**
     * 根据用户名查询用户
     *
     * @author xiyongchun
     * @date 2017/7/8 上午1:35
     * @param
     * @return
     */
    public List<User> listWithUserName(String userName){
        UserExample example = new UserExample();
        if(!StringUtils.isEmpty(userName)){
            example.createCriteria().andUserNameLike(userName + "%");
        }
        return this.userMapper.selectByExample(example);
    }

    public List<User> loadAllUsers() {
        return this.userMapper.selectByExample(null);
    }

    public void save(User user) {
        //密码加密
        user.setPassword((new BCryptPasswordEncoder(4)).encode(user.getPassword()));
        this.userMapper.insertSelective(user);
    }

    public User findUserByUsername(String username){
        UserExample example = new UserExample();
        example.createCriteria().andUserNameEqualTo(username);
        List<User> userList = this.userMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(userList)){
            return null;
        }
        return userList.get(0);
    }

}
