package com.h5education.demo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(description = "用户访问文件记录")
public class UserAndFile {
    @ApiModelProperty(value = "用户账号",required = true)
    private String account;
    @ApiModelProperty(value = "文件id",required = true)
    private int fileId;
    @ApiModelProperty(value = "文件名",required = true)
    private String name;
    @ApiModelProperty(value = "日期",required = true)
    private String date;
}
