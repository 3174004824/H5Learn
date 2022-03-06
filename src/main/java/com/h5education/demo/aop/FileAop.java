package com.h5education.demo.aop;

import com.h5education.demo.dao.FileDao;
import com.h5education.demo.dao.UserandfileDao;
import com.h5education.demo.pojo.Files;
import com.h5education.demo.pojo.User;
import com.h5education.demo.service.after.ShowUserAndFileService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Aspect
@Component
public class FileAop {

    @Autowired
    ShowUserAndFileService service;

    @Resource
    FileDao fileDao;

    @Pointcut("execution(public void downloadFile(..))")
    public void LogAspect(){}
    @Before(" LogAspect()")
    public void beforeDownLoadFile(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

        HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);

        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        String ids = request.getParameter("id");
        int id = Integer.parseInt(ids);

        Files files = fileDao.selectById(id);

        User user = (User) session.getAttribute("user");

        String account = user.getAccount();

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("YYYY-MM-dd:HH:mm:ss");
        String dateTime = localDateTime.format(dateTimeFormatter);
        System.out.println(dateTime);

        service.insertCatagory(account,files.getName(),files.getId(),dateTime);
    }
}
