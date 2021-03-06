package com.his.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;


/**
 * @Author liyan
 * @Date 2021-02-08
 * @Version ： V1.0
 * @Description: 登陆的数据输出对象
 */
@Data
public class LoginBodyDto {

    //  用户名
  @NotNull(message = "用户名不能为空")
    private String username;
    //  密码
  @NotNull(message = "密码不能为空")
    private String password;
    // 验证码
    private String code;

}
