package com.h5education.demo.dao;

import com.h5education.demo.pojo.Video;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface VideoDao {

    @Insert("insert into video(url,title,introduce) values (#{url},#{name},#{introduce})")
    void insertVideo(String name, String url,String introduce);

    @Select("select * from video")
    ArrayList<Video> selectAll();

    @Select("select * from video")
    List<Video> selAllVideo();

    @Select("select * from video where id = #{id}")
    Video selectById(int id);

    @Delete("delete from video where id = #{id}")
    boolean deleteById(int id);

}
