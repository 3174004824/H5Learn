package com.h5education.demo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "文件")
public class Files {
    @ApiModelProperty(value = "文件id",required = true)
    private int id;
    @ApiModelProperty(value = "文件名",required = true)
    private String name;
    @ApiModelProperty(value = "文件介绍",required = true)
    private String introduce;
    @ApiModelProperty(value = "文件类型",required = true)
    private String type;
    @ApiModelProperty(value = "文件路径",required = true)
    private String url;
}
