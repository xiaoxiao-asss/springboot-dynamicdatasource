package com.work.controller;


import com.work.TargetDataSource;
import com.work.entity.UserInfo;
import com.work.enums.DataSource;
import com.work.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user1")
public class UserController2 {

    @Autowired
    private UserService userServiceImpl;

    /**
     * 使用主数据源
     * @return
     */
    @GetMapping("/test1")
    @TargetDataSource(DataSource.MASTER)
    public   List<UserInfo> listAll(){
        List<UserInfo> result = userServiceImpl.listAll();
        return result;

    }

    /**
     * 使用从数据源
     * @return
     */
    @PostMapping("/test2")
    @TargetDataSource(DataSource.SLAVE)
    public ResponseEntity<Object> listAlla(){
        List<UserInfo> result = userServiceImpl.listAll();
        return ResponseEntity.ok(result);

    }

    /**
     * 不写数据源使用默认设置的master数据源
     * @return
     */
    @GetMapping("/test3")
    public   List<UserInfo> listAll3(){
        List<UserInfo> result = userServiceImpl.listAll();
        return result;

    }

}
