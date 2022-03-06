package com.h5education.demo.controller;

import com.h5education.demo.pojo.Files;
import com.h5education.demo.service.after.UpdateFileServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;

@Api(tags = "文件下载模块")
@Controller
public class DownloadFile {


    @Autowired
    UpdateFileServiceImpl fileService;

    @ApiOperation(value = "文件下载接口")
    @RequestMapping("/download")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "response",value = "相应协议包",required = true,dataTypeClass = Long.class),
            @ApiImplicitParam(name = "id",value = "下载文件的id",required = true,dataTypeClass = Long.class)
    })
    public void downloadFile(HttpServletResponse response, @RequestParam("id") int id) {

        Files files1 = fileService.selectById(id);

        String url = files1.getUrl();
        String filename = files1.getName();

        System.out.println("url = " + url);



        if (url != null) {
            //设置文件路径
            File files = new File(url);

            response.reset();


            //File file = new File(realPath , fileName);
            if (files.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开





                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;


                try {


                    response.setHeader( "Content-Disposition", "attachment;filename=" + new String( filename.getBytes("gb2312"), "ISO8859-1" ) );// 设置文件名


                    fis = new FileInputStream(files);
                    bis = new BufferedInputStream(fis);


                    OutputStream os = response.getOutputStream();


                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    //return "success";
                }
                catch (Exception e) {
                    e.printStackTrace();
                } finally { // 做关闭操作
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        //return "fail";
    }
}
