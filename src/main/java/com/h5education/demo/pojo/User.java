package com.h5education.demo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel(description = "用户")
public class User {
    @ApiModelProperty(value = "用户账号",required = true)
    String account;
    @ApiModelProperty(value = "用户密码",required = true)
    String password;
    @ApiModelProperty(value = "用户名",required = true)
    String name;
    @ApiModelProperty(value = "学院",required = true)
    String college;
    @ApiModelProperty(value = "年级",required = true)
    String classes;
    @ApiModelProperty(value = "角色(权限)",required = true)
    String role;
}
