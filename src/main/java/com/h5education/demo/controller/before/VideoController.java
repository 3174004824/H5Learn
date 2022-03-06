package com.h5education.demo.controller.before;

import com.h5education.demo.pojo.Files;
import com.h5education.demo.pojo.PageResult;
import com.h5education.demo.pojo.Video;
import com.h5education.demo.service.after.UpdateFileServiceImpl;
import com.h5education.demo.service.before.VideoServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Api(tags = "查询视频列表、查询文件列表模块")
@Controller
public class VideoController {

    @Autowired
    UpdateFileServiceImpl fileService;

    @Autowired
    VideoServiceImpl videoService;

    @ApiOperation(value = "查询视频列表接口")
    @RequestMapping("/showAllCustomer")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页号",required = true,dataTypeClass = Long.class),
            @ApiImplicitParam(name = "row",value = "列数",required = true,dataTypeClass = Long.class),
            @ApiImplicitParam(name = "model",value = "视图",required = true,dataTypeClass = Long.class)
    })
    public String itemList(@RequestParam(defaultValue = "1")Integer page, @RequestParam(defaultValue = "10")Integer row, Model model){
        PageResult<Video> pageResult = videoService.selAllVideo(page, row);
        model.addAttribute("pages", pageResult);
        return "before/showVideo";
    }

    @ApiOperation(value = "查询文件列表接口")
    @RequestMapping("/showFile")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页号",required = true,dataTypeClass = Long.class),
            @ApiImplicitParam(name = "row",value = "列数",required = true,dataTypeClass = Long.class),
            @ApiImplicitParam(name = "model",value = "视图",required = true,dataTypeClass = Long.class)
    })
    public String showFile( @RequestParam(defaultValue = "1")Integer page, @RequestParam(defaultValue = "10")Integer row, Model model){
        PageResult<Files> pageResult = fileService.findFile(page,row);
        model.addAttribute("pages", pageResult);
        return "before/showFile";
    }

}