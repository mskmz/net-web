package com.mskmz.course.dao;


import com.mskmz.course.bean.Trx;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mskmz on 2017/7/31.
 */
@Component
@Repository
public interface TrxDao {

    @Select("select count(*) from Trx where contentId=#{contentId} and personId=#{personId}")
    public int getTotalForIdUser(@Param("contentId") String contentId,@Param("personId") String personId);

    @Select("select count(*) from Trx where contentId=#{contentId}")
    public int getTotalForId(@Param("contentId") String contentId);

    @Select("select t.*,t.price as buyPrice,t.number as buyNum ,t.time as buyTime" +
            "c.icon as image," +
            "c.title as title," +
            "'1' as buyNum" +
            "from Trx as t,Content as c where personId=#{personId} and t.personId = c.id")
    public List<Trx> list(@Param("personId") String personId);

    @Insert("insert into" +
            "(contentid,personid,price,time,number)" +
            "values" +
            "(#{contentid},#{personId},(select price from content where id=#{contentid} ),now(),#{number})")
    public int insert(
            @Param("contentid") String contentid,
            @Param("number") String number,
            @Param("personId") String personId
    );
}
