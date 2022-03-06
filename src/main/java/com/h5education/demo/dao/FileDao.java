package com.h5education.demo.dao;

import com.h5education.demo.pojo.Files;
import io.swagger.annotations.ApiModel;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface FileDao {

    @Select("select * from file")
    List<Files> findAll();

    @Insert("insert into file(name,introduce,url) values (#{name},#{introduce},#{url})")
    void insertFile(Files files);

    @Delete("delete from file where id = #{id}")
    void deleteFile(int id);

    @Select("select * from file where id = #{id}")
    Files selectById(int id);

    @Select("select * from file where pretendName = #{pretendName}")
    Files selectByName(String pretendName);

}
