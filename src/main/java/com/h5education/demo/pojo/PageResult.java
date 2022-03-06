package com.h5education.demo.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
@ApiModel(description = "分页")
public class PageResult<T> implements Serializable {
    @ApiModelProperty(value = "每页的数据",required = true)
    private List<T> row; //每页的数据
    @ApiModelProperty(value = "总共页数",required = true)
    private Long total; //总共记录数
    @ApiModelProperty(value = "目前页数",required = true)
    private Integer currentPage; //当前页数(从1开始)
    @ApiModelProperty(value = "总共页数",required = true)
    private Integer pages; //总共页数

}
