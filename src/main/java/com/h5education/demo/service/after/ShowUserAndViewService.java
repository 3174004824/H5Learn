package com.h5education.demo.service.after;


import com.h5education.demo.pojo.PageResult;
import com.h5education.demo.pojo.UserAndVideo;

import java.util.Date;
import java.util.List;

public interface ShowUserAndViewService {
    PageResult<UserAndVideo> selectByAccount(String account,Integer page,Integer row);

    void insertCatagory(String account, String name, int videoId, String date);

}
