package com.work.service.impl;

import com.work.entity.UserInfo;
import com.work.mapper.Test1Mapper;
import com.work.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private Test1Mapper test1Mapper;


    @Override
    public  List<UserInfo> listAll() {
       List<UserInfo> userInfos=test1Mapper.queryUserInfo();
        return userInfos;
    }
}
