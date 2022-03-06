package com.h5education.demo.service.after;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.h5education.demo.dao.UserandVideoDao;
import com.h5education.demo.pojo.PageResult;
import com.h5education.demo.pojo.UserAndVideo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
@Service
public class ShowUserAndViewServiceImpl implements ShowUserAndViewService{

    @Autowired
    UserandVideoDao dao;


    @Override
    public PageResult<UserAndVideo> selectByAccount(String account,Integer page,Integer row) {
        Page ps = PageHelper.startPage(page,row);
        List<UserAndVideo> userAndVideos = dao.selectByAccount(account);
        PageResult pageResult = new PageResult();
        pageResult.setRow(userAndVideos);
        pageResult.setPages(ps.getPages());
        pageResult.setTotal(ps.getTotal());
        pageResult.setCurrentPage(page);
        return pageResult;
    }

    @Override
    public void insertCatagory(String account, String name, int videoId, String date) {
        dao.insertCatagory(account,name,videoId,date);
    }
}
