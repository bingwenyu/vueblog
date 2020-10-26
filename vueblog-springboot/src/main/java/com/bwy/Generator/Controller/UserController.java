package com.bwy.Generator.Controller;


import com.bwy.Generator.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
    private UserMapper userMapper;

    @GetMapping("/{id}")
    public Object test(@PathVariable("id") Long id) {
        return userMapper.selectById(id);
    }

    //UserService userService;
//    @GetMapping("/{id}")
//    public Object test(@PathVariable("id") Long id) {
//        return userService.getById(id);
//    }

}
