package com.h5education.demo.dao;

import com.h5education.demo.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserDao {

    @Select("select * from user where account = #{account}")
    User selectInfo(String account);

    @Insert("insert into user(account,password,role,name) values (#{account},#{password},#{role},#{name})")
    void insertUser(String account,String password,String role,String name);

    @Select("select * from user where account = #{account} and password = #{password}")
    User loginUser(String account,String password);

    @Update("update user set name = #{name} , college = #{themplate} , classes = #{classes} where account = #{account}")
    void updateUser(String account,String themplate,String name,String classes);

    @Select("select * from user")
    List<User> selectAll();
}
