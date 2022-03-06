package com.h5education.demo.controller.before;

import com.h5education.demo.pojo.Files;
import com.h5education.demo.pojo.PageResult;
import com.h5education.demo.service.UserServiceImpl;
import com.h5education.demo.service.after.UpdateFileServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "载入动画、返回首页模块")
@Controller
public class MyHome {

    @Autowired
    UserServiceImpl userService;

    @ApiOperation(value = "载入动画接口")
    @RequestMapping({"/"})
    public String dialog(){
        return "before/loading";
    }

    @ApiOperation(value = "访问主界面接口")
    @RequestMapping("/index")
    public String index(){
        return "before/index";
    }


}