package com.h5education.demo.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.h5education.demo.dao.UserDao;
import com.h5education.demo.pojo.PageResult;
import com.h5education.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserDao userDao;

    //查询用户
    @Override
    public User selectUserInfo(String account) {
        User user = userDao.selectInfo(account);
        return user;
    }
    //注册用户
    @Override
    public Boolean insertUser(String account, String password) {
        if (selectUserInfo(account) != null){
            return false;
        }
        userDao.insertUser(account,password,"student","未定义");
        return true;
    }
    //登陆用户
    @Override
    public User loginUser(String account, String password) {
        User user = userDao.loginUser(account, password);
        if (user != null){
            return user;
        }
        return null;
    }
    @Override
    public User upDateUser(String account, String themplate, String name, String classes) {
        String college;
        switch (themplate){
            case "IE":
                college = "信息科学与工程学院";
                break;
            case "MX":
                college = "马克思主义学院";
                break;
            case "FI":
                college = "外国语学院";
                break;
            case "MATH":
                college = "数学学院";
                break;
            case "LIFE":
                college = "生命科学学院";
                break;
            case "SPORT":
                college = "体育学院";
            case "TEACHER":
                college = "教师教育学院";
                break;
            case "History":
                college = "历史与社会发展学院";
                break;
            case "PE":
                college = "物理与电子工程学院";
                break;
            case "FA":
                college = "美术学院";
                break;
            case "Art":
                college = "文学院";
                break;
            case "AI":
                college = "人工智能产业学院";
                break;
            case "EC":
                college = "经济与管理学院";
                break;
            case "CH":
                college = "化学化工学院";
                break;
            case "Education":
                college = "继续教育学院";
                break;
            case "Music":
                college = "音乐学院";
                break;
            default:
                college = "未选择";
        }
        userDao.updateUser(account, college, name, classes);
        User user = userDao.selectInfo(account);
        return user;
    }

    @Override
    public PageResult<User> selectAll(Integer page, Integer row) {
        Page ps = PageHelper.startPage(page,row);
        List<User> list = userDao.selectAll();
        PageResult<User> result = new PageResult<User>();
        result.setRow(list);
        result.setTotal(ps.getTotal());
        result.setPages(ps.getPages());
        result.setCurrentPage(page);
        return result;
    }
}
