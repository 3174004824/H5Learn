package com.h5education.demo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@ApiModel(description = "上传返回值封装")
public class ResultMsg {
    @ApiModelProperty(value = "描述信息",required = true)
    private String PublicStr;
    @ApiModelProperty(value = "状态码",required = true)
    private int Code;
    @ApiModelProperty(value = "信息",required = true)
    private String Msg;
}
