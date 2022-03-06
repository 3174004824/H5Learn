package com.h5education.demo.controller.after;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(tags = "后台访问模块")
@Controller
public class tologin {

    @ApiOperation(value = "访问后台接口")
    @RequestMapping("/root")
    public String tologin(){
        return "login";
    }
}
