package com.bwy.shiro;

import lombok.Data;

import java.io.Serializable;

//登录后可以公开的个人信息
@Data
public class AccountProfile implements Serializable {

    private Long id;

    private String username;

    private String avatar;

    private String email;

}
