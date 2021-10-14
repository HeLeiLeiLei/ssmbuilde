package com.hl.service;

import com.hl.pojo.User;
import org.springframework.stereotype.Service;

import java.util.Map;


public interface UserService {
    User queryUser(Map map);
    int addUser(User user);
}
