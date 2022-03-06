package com.h5education.demo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "视频")
public class Video {
    @ApiModelProperty(value = "视频id",required = true)
    private int id;
    @ApiModelProperty(value = "视频路径",required = true)
    private String url;
    @ApiModelProperty(value = "视频标题",required = true)
    private String title;
    @ApiModelProperty(value = "视频介绍",required = true)
    private String introduce;
}
