package com.bwy;

import com.bwy.entity.User;
import com.bwy.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class VueblogApplicationTests {

//    @Test
//    void contextLoads() {
//    }

    //方法来自继承的父类 BaseMapper
    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        //查询所有用户
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

}
