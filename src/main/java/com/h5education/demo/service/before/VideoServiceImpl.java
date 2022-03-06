package com.h5education.demo.service.before;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.h5education.demo.dao.VideoDao;
import com.h5education.demo.pojo.PageResult;
import com.h5education.demo.pojo.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Transactional
@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    VideoDao videoDao;

    @Override
    public PageResult<Video> selAllVideo(Integer page, Integer row) {
        Page ps = PageHelper.startPage(page,row);

        List<Video> list = videoDao.selAllVideo();
        PageResult<Video> result = new PageResult<Video>();
        result.setRow(list);
        result.setTotal(ps.getTotal());
        result.setPages(ps.getPages());
        result.setCurrentPage(page);
        return result;
    }

    @Override
    public Video selectById(int id) {
        Video video = videoDao.selectById(id);
        return video;
    }
}
