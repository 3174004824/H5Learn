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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Api(tags = "用户登陆、用户注册、用户退出模块")
@Controller
public class LoginUser {

    @Autowired
    UserServiceImpl userService;

    @ApiOperation(value = "登陆界面接口")
    //访问登陆界面
    @RequestMapping("/login")
    public String login(){
        return "before/login";
    }

    //用户登陆
    @ApiOperation(value = "登陆接口")
    @RequestMapping("/logins")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account",value = "账号",required = true,dataTypeClass = Long.class),
            @ApiImplicitParam(name = "password",value = "密码",required = true,dataTypeClass = Long.class),
            @ApiImplicitParam(name = "request",value = "请求协议包",required = true,dataTypeClass = Long.class)
    })
    public ModelAndView logins(@RequestParam("username") String account, @RequestParam("password") String password,HttpServletRequest request){
        //登陆
        ModelAndView modelAndView = new ModelAndView();
        if (account == null){
            modelAndView.addObject("information","用户名不能为空");
            modelAndView.setViewName("before/login");
        }

        User user = userService.loginUser(account, password);
        if (user != null){
            modelAndView.addObject("user",user);
            modelAndView.setViewName("before/index");
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
        }else {
            modelAndView.setViewName("error/loginerror");
        }
        return modelAndView;
    }

    //用户注册
    @ApiOperation(value = "用户注册接口")
    @RequestMapping("/register")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account",value = "用户名",dataTypeClass = Long.class),
            @ApiImplicitParam(name = "password",value = "密码",dataTypeClass = Long.class),
            @ApiImplicitParam(name = "request",value = "请求协议包",dataTypeClass = Long.class)
    })
    public ModelAndView register(@RequestParam("username") String account,@RequestParam("password") String password,HttpServletRequest request){
        //注册

        Boolean aBoolean = userService.insertUser(account, password);
        ModelAndView modelAndView = new ModelAndView();
        if (aBoolean == false){
            modelAndView.addObject("information","用户已存在");
            modelAndView.setViewName("before/login");
        }else {
            User user = userService.selectUserInfo(account);
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            modelAndView.addObject("user",user);
            modelAndView.setViewName("before/index");
        }
        return modelAndView;
    }

    //用户退出
    @ApiOperation(value = "用户退出登陆接口")
    @RequestMapping("/quit")
    @ApiImplicitParam(name = "request",value = "请求协议包",dataTypeClass = Long.class)
    public String quit(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "before/index";
    }
}