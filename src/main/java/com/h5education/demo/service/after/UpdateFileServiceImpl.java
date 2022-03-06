package com.h5education.demo.service.after;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.h5education.demo.dao.FileDao;
import com.h5education.demo.Util.FileUtil;
import com.h5education.demo.pojo.Files;
import com.h5education.demo.pojo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UpdateFileServiceImpl implements UpdateFileService{

    @Autowired
    FileDao fileDao;



    public PageResult findFile(Integer page, Integer row){
        Page pg = PageHelper.startPage(page,row);
        List<Files> list = fileDao.findAll();
        PageResult pageResult = new PageResult();
        pageResult.setRow(list);
        pageResult.setPages(pg.getPages());
        pageResult.setTotal(pg.getTotal());
        pageResult.setCurrentPage(page);
        return pageResult;
    }

    @Override
    public Boolean insertFile(Files files) {
        fileDao.insertFile(files);
        return null;
    }

    @Override
    public Boolean deleteFile(int id) {
        Files files = fileDao.selectById(id);
        if (files == null){
            return false;
        }
        boolean b = FileUtil.deleteFile(files.getUrl());
        if (b){
            fileDao.deleteFile(id);
            return true;
        }
        return false;
    }

    @Override
    public Files selectById(int id) {
        Files files = fileDao.selectById(id);
        return files;
    }
}
