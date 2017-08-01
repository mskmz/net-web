package com.mskmz.course.dao;

import com.mskmz.course.bean.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created by mskmz on 2017/7/31.
 */
@Component
@Repository
public interface UserDao {

    @Select("select * from person where userName = #{userName} and password = #{password}")
    public User getUser(@Param("userName") String userName, @Param("password") String password);
}
