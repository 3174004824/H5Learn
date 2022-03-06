package com.h5education.demo.service.after;

import com.h5education.demo.pojo.PageResult;
import com.h5education.demo.pojo.UserAndFile;

import java.util.Date;

public interface ShowUserAndFileService {

    PageResult<UserAndFile> select(String account,Integer page,Integer row);

    void insertCatagory(String account, String name, int fileId, String date);

}
