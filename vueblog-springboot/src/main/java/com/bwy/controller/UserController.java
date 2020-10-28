package com.bwy.controller;

import com.bwy.common.lang.Result;
import com.bwy.entity.User;
import com.bwy.mapper.UserMapper;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author bwy
 * @since 2020-10-21
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserMapper userMapper;

    @RequiresAuthentication
    @GetMapping("/index")
    public Result index() {
        User user = userMapper.selectById(1L);
        return Result.succ(user);
    }

    @PostMapping("/save")
    public Result save(@Validated @RequestBody User user) {
        return Result.succ(user);
    }

}
