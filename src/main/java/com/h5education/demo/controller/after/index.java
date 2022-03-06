package com.h5education.demo.controller.after;

import com.h5education.demo.pojo.User;
import com.h5education.demo.service.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Api(tags = "用户登陆、访问主页面模块")
@Controller
@RequestMapping("/After")
public class index {

    @Autowired
    UserServiceImpl service;

    @ApiOperation(value = "后台登陆接口")
    @RequestMapping("/MyRoot")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "账号",required = true,dataTypeClass = Long.class),
            @ApiImplicitParam(name = "password",value = "密码",required = true,dataTypeClass = Long.class),
            @ApiImplicitParam(name = "request",value = "请求协议包",required = true,dataTypeClass = Long.class)
    })
    public String roots(@RequestParam("username") String username, @RequestParam("password") String password,HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try {
            subject.login(token);
            User user = service.selectUserInfo(username);
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            return "after/index";
        }catch (UnknownAccountException e){
            return "login";
        }catch (IncorrectCredentialsException e){
            return "login";
        }
    }


    @ApiOperation(value = "访问后台主页面接口")
    @RequestMapping("/toIndex")
    public String toIndex(){
        return "after/index";
    }

    @ApiOperation(value = "无权限处理接口")
    @RequestMapping("/notRoot")
    public String notRoot(){
        return "after/NotRole";
    }
}
