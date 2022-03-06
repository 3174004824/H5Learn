package com.h5education.demo.service.before;

import com.h5education.demo.pojo.PageResult;
import com.h5education.demo.pojo.Video;

public interface VideoService {
    PageResult<Video> selAllVideo(Integer page, Integer row);

    Video selectById(int id);

}
