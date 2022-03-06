package com.h5education.demo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "用户访问视频记录")
public class UserAndVideo {
    @ApiModelProperty(value = "用户账号",required = true)
    private String account;
    @ApiModelProperty(value = "视频名",required = true)
    private String name;
    @ApiModelProperty(value = "视频id",required = true)
    private int videoId;
    @ApiModelProperty(value = "日期",required = true)
    private String date;
}
