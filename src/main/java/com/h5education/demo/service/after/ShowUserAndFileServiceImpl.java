package com.h5education.demo.service.after;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.h5education.demo.dao.UserandfileDao;
import com.h5education.demo.pojo.PageResult;
import com.h5education.demo.pojo.UserAndFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ShowUserAndFileServiceImpl implements ShowUserAndFileService{

    @Resource
    UserandfileDao userandfileDao;


    @Override
    public PageResult<UserAndFile> select(String account,Integer page,Integer row) {

        Page ps = PageHelper.startPage(page,row);
        List<UserAndFile> userAndFiles = userandfileDao.select(account);
        PageResult<UserAndFile> pageResult = new PageResult();
        pageResult.setRow(userAndFiles);
        pageResult.setTotal(ps.getTotal());
        pageResult.setPages(ps.getPages());
        pageResult.setCurrentPage(page);
        return pageResult;
    }

    @Override
    public void insertCatagory(String account, String name, int fileId, String date) {
        userandfileDao.insertCatagory(account,name,fileId,date);
    }
}
