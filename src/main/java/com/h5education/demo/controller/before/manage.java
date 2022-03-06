package com.h5education.demo.controller.before;

import com.h5education.demo.pojo.User;
import com.h5education.demo.service.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Api(tags = "修改个人信息模块")
@Controller
public class manage {

    @Autowired
    UserServiceImpl userService;

    @ApiOperation(value = "访问个人信息接口")
    @RequestMapping("/myhome")
    public String myhome(){
        return "before/Myhome";
    }

    @ApiOperation(value = "修改个人信息界面接口")
    @RequestMapping("/toupdate")
    public String toupdate(){
        return "before/updateMyhome";
    }


    @ApiOperation(value = "更新用户信息接口")
    @RequestMapping("/updateUser")
    @ApiImplicitParam(name = "request",value = "请求协议包",required = true,dataTypeClass = Long.class)
    public String updateUser(HttpServletRequest request){
        String themplate = request.getParameter("themplate");
        String name = request.getParameter("name");
        String classes = request.getParameter("classes");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String account = user.getAccount();
        User user1 = userService.upDateUser(account, themplate, name, classes);
        session.setAttribute("user",user1);
        return "before/Myhome";
    }


}
