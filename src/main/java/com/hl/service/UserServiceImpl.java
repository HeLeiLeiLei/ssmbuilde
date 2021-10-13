package com.hl.service;

import com.hl.dao.UserMapper;
import com.hl.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    public void setUserMapper(UserMapper userMapper){
        this.userMapper=userMapper;
    }

    public User queryUser(Map map) {
        return userMapper.queryUser(map);
    }
}
