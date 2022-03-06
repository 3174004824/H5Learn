package com.h5education.demo.dao;

import com.h5education.demo.pojo.UserAndFile;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserandfileDao {

    @Select("select * from userfile where account = #{account}")
    List<UserAndFile> select(String account);


    @Insert("insert into userfile(account,name,fileId,date) values(#{account},#{name},#{fileId},#{date})")
    void insertCatagory(String account, String name, int fileId, String date);

}
