package com.h5education.demo.controller.before;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Api(tags = "跳转目录模块")
@Controller
public class catagory {
    @ApiOperation(value = "目录接口")
    @RequestMapping("/category")
    @ApiImplicitParam(name = "id",value = "目录的id",dataTypeClass = Long.class)
    public String resendCategory(@RequestParam("id") String id){
        String url = "before/catalogue/"+id;
        return url;
    }

    @ApiOperation(value = "案例接口")
    @RequestMapping("/example")
    @ApiImplicitParam(name = "id",value = "案例的id",dataTypeClass = Long.class)
    public String example(@RequestParam("id")String id){
        return "before/catalogue/"+id;
    }
}