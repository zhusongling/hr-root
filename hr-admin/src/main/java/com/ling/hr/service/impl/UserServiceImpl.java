package com.ling.hr.service.impl;

import com.ling.hr.domain.User;
import com.ling.hr.mapper.UserMapper;
import com.ling.hr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public int save(User user) {
        int num = userMapper.insert(user);
        return num;
    }
}
