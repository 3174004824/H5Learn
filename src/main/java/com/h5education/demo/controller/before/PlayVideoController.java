package com.h5education.demo.controller.before;

import com.h5education.demo.pojo.Video;
import com.h5education.demo.service.before.VideoServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;

@Api(tags = "视频播放模块")
@Controller
public class PlayVideoController {

    @Autowired
    VideoServiceImpl videoService;

    //---------------------------------------播放视频--------------------------------------------
    @ApiOperation(value = "视频播放页面接口")
    @RequestMapping("/videoplay")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "model",value = "视图",dataTypeClass = Long.class),
            @ApiImplicitParam(name = "id",value = "视频id",dataTypeClass = Long.class)
    })
    public String videoplya(Model model, @RequestParam("id") int id){
        Video video = videoService.selectById(id);
        model.addAttribute("id",video.getId());
        model.addAttribute("introduce",video.getIntroduce());
        return "before/videoplay";
    }

    @ApiOperation(value = "获取播放资源并播放接口")
    @RequestMapping(value = "/getVideo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "request",value = "请求协议包",dataTypeClass = Long.class),
            @ApiImplicitParam(name = "response",value = "相应协议包",dataTypeClass = Long.class),
            @ApiImplicitParam(name = "videoId",value = "视频id",dataTypeClass = Long.class)
    })
    public void getVideo(HttpServletRequest request, HttpServletResponse response, @RequestParam("videoId") int videoId) {
        //视频资源存储信息
        Video videoSource = videoService.selectById(videoId);
        response.reset();
        //获取从那个字节开始读取文件
        String rangeString = request.getHeader("Range");

        try {
            //获取响应的输出流
            OutputStream outputStream = response.getOutputStream();
            File file = new File(videoSource.getUrl());
            if(file.exists()){
                RandomAccessFile targetFile = new RandomAccessFile(file, "r");
                long fileLength = targetFile.length();

                if(rangeString != null){

                    long range = Long.valueOf(rangeString.substring(rangeString.indexOf("=") + 1, rangeString.indexOf("-")));
                    //设置内容类型
                    response.setHeader("Content-Type", "video/mp4");
                    //设置此次相应返回的数据长度
                    response.setHeader("Content-Length", String.valueOf(fileLength - range));
                    //设置此次相应返回的数据范围
                    response.setHeader("Content-Range", "bytes "+range+"-"+(fileLength-1)+"/"+fileLength);
                    //返回码需要为206，而不是200
                    response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
                    //设定文件读取开始位置（以字节为单位）
                    targetFile.seek(range);
                }else {//下载

                    //设置响应头，把文件名字设置好
                    response.setHeader("Content-Disposition", "attachment; filename="+videoSource.getTitle());
                    //设置文件长度
                    response.setHeader("Content-Length", String.valueOf(fileLength));
                    //解决编码问题
                    response.setHeader("Content-Type","application/octet-stream");
                }


                byte[] cache = new byte[1024 * 300];
                int flag;
                while ((flag = targetFile.read(cache))!=-1){
                    outputStream.write(cache, 0, flag);
                }
            }else {
                String message = "file:"+videoSource.getTitle()+" not exists";
                //解决编码问题
                response.setHeader("Content-Type","application/json");
                outputStream.write(message.getBytes(StandardCharsets.UTF_8));
            }
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }
    }
}
