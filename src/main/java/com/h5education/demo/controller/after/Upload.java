package com.h5education.demo.controller.after;

import com.h5education.demo.Util.FileUtil;
import com.h5education.demo.pojo.Files;
import com.h5education.demo.pojo.ResultMsg;
import com.h5education.demo.service.after.UpdateFileServiceImpl;
import com.h5education.demo.service.after.UpdateVideoServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Api(tags = "视频上传、文件上传模块")
@RequestMapping(value = "/upload", method = RequestMethod.POST)
@Controller
public class Upload {

    @Autowired
    FileUtil fileUtil;

    @Autowired
    UpdateVideoServiceImpl videoService;
    @Value("/Video/")
    String videoSavePath;
    @Value("/File/")
    String fileSavePath;

    @Autowired
    UpdateFileServiceImpl fileService;


    @ApiOperation(value = "视频上传接口")
    @ResponseBody
    @RequestMapping("/video")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file",value = "前端上传过来的视频",required = true,dataTypeClass = Long.class),
            @ApiImplicitParam(name = "name",value = "视频名",required = true,dataTypeClass = Long.class),
            @ApiImplicitParam(name = "introduce",value = "视频介绍",required = true,dataTypeClass = Long.class)
    })
    public ResultMsg uploadVideo(MultipartFile file, String name, String introduce) {


        Logger logger = LoggerFactory.getLogger(Upload.class);
        ResultMsg msg = new ResultMsg();
        try{
            byte[] bytes = file.getBytes();
            // 后缀
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

            if (!suffix.equals(".mp4")){
                msg.setCode(401);
                msg.setMsg("请上传视频文件");

                return msg;
            }

            // 得到新的视频名
            String newFileName = UUID.randomUUID().toString().replaceAll("-", "") + suffix;

            name = name + suffix;

            videoService.saveVideo(name,videoSavePath +newFileName,introduce);

            logger.info("新视频的名字："+newFileName);

            fileUtil.uploadFile(bytes,videoSavePath,newFileName);

            msg.setCode(200);
            msg.setMsg("上传视频成功！");
            msg.setPublicStr(newFileName);
            System.out.println(newFileName + "======");

            return msg;
        }catch(Exception e){
            e.printStackTrace();
            msg.setCode(500);
            msg.setMsg("上传视频时IO异常！" + e);
            return msg;
        }
    }

    @ApiOperation(value = "文件上传接口")
    @RequestMapping("/addFile")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file",value = "前端上传过来的文件",required = true,dataTypeClass = Long.class),
            @ApiImplicitParam(name = "name",value = "文件名",required = true,dataTypeClass = Long.class),
            @ApiImplicitParam(name = "introduce",value = "文件介绍",required = true,dataTypeClass = Long.class)
    })
    public String addFile(@RequestParam("file")MultipartFile file, String name, String introduce){

        Logger logger = LoggerFactory.getLogger(Manager.class);

        try{
            byte[] bytes = file.getBytes();
            // 后缀
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));






            // 得到新的文件名
            String newFileName = UUID.randomUUID().toString().replaceAll("-", "") + suffix;

            Files addFile = new Files();

            addFile.setName(name + suffix);
            addFile.setIntroduce(introduce);
            addFile.setUrl(fileSavePath + newFileName);


            fileService.insertFile(addFile);

            //videoService.saveVideo(name,"../static/video/"+newFileName,introduce);

            logger.info("新文件的名字："+newFileName);

            fileUtil.uploadFile(bytes,fileSavePath,newFileName);
            return "success";
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e);
            return "fail";
        }
    }

}
