package com.h5education.demo.dao;

import com.h5education.demo.pojo.UserAndVideo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserandVideoDao {
    @Select("select * from uservideo where account = #{account}")
    List<UserAndVideo> selectByAccount(String account);

    @Insert("insert into uservideo(account,name,videoId,date) values(#{account},#{name},#{videoId},#{date})")
    void insertCatagory(String account, String name, int videoId, String date);
}
