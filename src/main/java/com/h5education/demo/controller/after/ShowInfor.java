package com.h5education.demo.controller.after;

import com.h5education.demo.pojo.PageResult;
import com.h5education.demo.pojo.UserAndFile;
import com.h5education.demo.pojo.UserAndVideo;
import com.h5education.demo.service.after.ShowUserAndFileServiceImpl;
import com.h5education.demo.service.after.ShowUserAndViewServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Api(tags = "查询用户访问视频、文件的记录模块")
@Controller
@RequestMapping("/show")
public class ShowInfor {

    @Autowired
    ShowUserAndFileServiceImpl showUserAndFileService;

    @Autowired
    ShowUserAndViewServiceImpl showUserAndViewService;


    @ApiOperation(value = "查询访问文件记录接口")
    @RequestMapping("/showFileInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account",value = "账号",required = true,dataTypeClass = Long.class),
            @ApiImplicitParam(name = "page",value = "页号",required = true,dataTypeClass = Long.class),
            @ApiImplicitParam(name = "row",value = "列数",required = true,dataTypeClass = Long.class)
    })
    public String showFileInfo(@RequestParam("account")String account,
                               @RequestParam(defaultValue = "1")Integer page,
                               @RequestParam(defaultValue = "10")Integer row,
                               Model model){
        PageResult<UserAndFile> select = showUserAndFileService.select(account, page, row);
        if (select.getTotal() == 0){
            return "after/NoFileInfo";
        }
        model.addAttribute("info",select);
        return "after/fileInformation";
    }



    @ApiOperation(value = "查询访问视频记录接口")
    @RequestMapping("/showVideoInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account",value = "账号",required = true,dataTypeClass = Long.class),
            @ApiImplicitParam(name = "page",value = "页号",required = true,dataTypeClass = Long.class),
            @ApiImplicitParam(name = "row",value = "列数",required = true,dataTypeClass = Long.class),
            @ApiImplicitParam(name = "model",value = "视图",required = true,dataTypeClass = Long.class)
    })
    public String showVideoInfo(@RequestParam("account")String account,
                                @RequestParam(defaultValue = "1")Integer page,
                                @RequestParam(defaultValue = "10")Integer row,
                                Model model){
        PageResult<UserAndVideo> userAndVideoPageResult = showUserAndViewService.selectByAccount(account, page, row);
        if (userAndVideoPageResult.getTotal() == 0){
            return "after/NoVideoInfo";
        }
        model.addAttribute("info",userAndVideoPageResult);
        return "after/videoInformation";
    }
}
