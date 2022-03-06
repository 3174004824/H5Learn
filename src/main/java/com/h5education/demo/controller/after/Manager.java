package com.h5education.demo.controller.after;

import com.h5education.demo.Util.FileUtil;
import com.h5education.demo.pojo.*;
import com.h5education.demo.service.UserServiceImpl;
import com.h5education.demo.service.after.UpdateFileServiceImpl;
import com.h5education.demo.service.after.UpdateVideoServiceImpl;
import com.h5education.demo.service.before.VideoServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;
import java.util.UUID;

@Api(tags = "查询用户列表、查询视频列表、查询文件列表、删除视频、删除文件模块")
@Controller
@RequestMapping("/root")
@Transactional
public class Manager {

    @Autowired
    UserServiceImpl service;

    @Autowired
    VideoServiceImpl videoService;

    @Autowired
    UpdateVideoServiceImpl updateVideoService;

    @Autowired
    UpdateFileServiceImpl fileService;

    @Value("../File/")
    String fileSavePath;

    @ApiOperation(value = "查询用户接口")
    @RequestMapping("/Manager")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页号",dataTypeClass = Long.class),
            @ApiImplicitParam(name = "row",value = "列数",dataTypeClass = Long.class)
    })
    public String userManager(@RequestParam(defaultValue = "1")Integer page,
                              @RequestParam(defaultValue = "10")Integer row,
                              Model model){
        PageResult<User> users = service.selectAll(page, row);
        model.addAttribute("users",users);
        return "after/userManager";
    }


    @ApiOperation(value = "查询视频接口")
    @RequestMapping("/managerVideo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页号",dataTypeClass = Long.class),
            @ApiImplicitParam(name = "row",value = "列数",dataTypeClass = Long.class),
            @ApiImplicitParam(name = "model",value = "视图",dataTypeClass = Long.class)
    })
    public String managerVideo(@RequestParam(defaultValue = "1")Integer page, @RequestParam(defaultValue = "10")Integer row, Model model) {
        PageResult<Video> pageResult = videoService.selAllVideo(page, row);
        model.addAttribute("pages", pageResult);
        return "after/updateVideo";
    }

    @ApiOperation(value = "删除视频接口")
    @RequestMapping("/deleteVideo")
    @ApiImplicitParam(name = "id",value = "要删除的视频的id",dataTypeClass = Long.class)
    public String deleteVideo(@RequestParam int id){
        Boolean aBoolean = updateVideoService.deleteVideo(id);
        if (aBoolean){
            return "after/index";
        }
        return "fail";
    }

    @ApiOperation(value = "查询文件接口")
    @RequestMapping("/showFile")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页号",required = true,dataTypeClass = Long.class),
            @ApiImplicitParam(name = "row",value = "列数",required = true,dataTypeClass = Long.class),
            @ApiImplicitParam(name = "model",value = "视图",required = true,dataTypeClass = Long.class)
    })
    public String showFile(@RequestParam(defaultValue = "1")Integer page, @RequestParam(defaultValue = "10")Integer row, Model model){
        PageResult<Files> pageResult = fileService.findFile(page,row);
        model.addAttribute("pages", pageResult);
        return "after/FileUpdate";
    }

    @ApiOperation(value = "删除文件接口")
    @RequestMapping("/deleteFile")
    @ApiImplicitParam(name = "id",value = "要删除文件的id",required = true,dataTypeClass = Long.class)
    public String deleteFile(int id){
        Boolean aBoolean = fileService.deleteFile(id);
        if (aBoolean){
            return "success";
        }
        return "fail";
    }
}