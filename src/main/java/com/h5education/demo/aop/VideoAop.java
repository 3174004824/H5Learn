package com.h5education.demo.aop;

import com.h5education.demo.dao.UserandVideoDao;
import com.h5education.demo.dao.VideoDao;
import com.h5education.demo.pojo.User;
import com.h5education.demo.pojo.Video;
import com.h5education.demo.service.after.ShowUserAndViewServiceImpl;
import com.h5education.demo.service.before.VideoServiceImpl;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Aspect
@Component
public class VideoAop {

    @Autowired
    ShowUserAndViewServiceImpl showUserAndViewService;

    @Autowired
    VideoServiceImpl videoService;

    @Pointcut("execution(public String videoplya(..))")
    public void video(){}
    @Before("video()")
    public void beforeVideo(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        String ids = request.getParameter("id");
        int id = Integer.parseInt(ids);
        User user = (User) session.getAttribute("user");
        Video video = videoService.selectById(id);
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY-MM-dd:HH:mm:ss");
        String dateTime = localDateTime.format(dateTimeFormatter);
        showUserAndViewService.insertCatagory(user.getAccount(),video.getTitle(),video.getId(),dateTime);
    }

}
