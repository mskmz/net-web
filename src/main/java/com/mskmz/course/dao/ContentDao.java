package com.mskmz.course.dao;

import com.mskmz.course.bean.Content;
import com.mskmz.course.bean.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by mskmz on 2017/7/31.
 */
@Component
@Repository

public interface ContentDao {

    @Delete("delete content where id=#{id}")
    public int delForId(@Param("id") String id);


    @Select("select c.*,c.icon as image,c.abstract as summary,c.text as detail,(select count(*) from Trx where contentId=c.id and personId=#{id})as buyCount,(select count(*) from Trx where contentId=c.id)as sheelCount,(SELECT price from trx where id = (SELECT max(time) FROM Trx))as buyPrice from content as c")
    public List<Content> list(@Param("id") String id);


    @Select("select c.*,c.icon as image,c.abstract as summary,c.text as detail ,(select count(*) from Trx where contentId=c.id and personId=#{id})as buyCount,(select count(*) from Trx where contentId=c.id)as sheelCount from content as c where id=#{id}")
    public Content findForId(@Param("id") String id);

    @Insert("insert into content (title,icon,text,price,abstract) values (#{title},#{image},#{detail},#{price},#{summary})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    public Integer insert(@Param("title") String title,
                          @Param("image") String image,
                          @Param("detail") String detail,
                          @Param("price") String price,
                          @Param("summary") String summary);
    @Update("update content set " +
            "title=#{title}," +
            "image=#{image}," +
            "detail=#{detail}," +
            "price=#{price}," +
            "summary=#{summary}" +
            "where id=#{id}")
    public int update(@Param("id") String id,
                          @Param("title") String title,
                          @Param("image") String image,
                          @Param("detail") String detail,
                          @Param("price") String price,
                          @Param("summary") String summary);


    @Update("update content set " +
            "image=#{image}" +
            "where id=#{id}")
    public int updateImage(@Param("id") String id,
                          @Param("image") String image);
}
