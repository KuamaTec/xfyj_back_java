package com.zgds.xfyj.dao;

import com.zgds.xfyj.domain.pojo.Inform;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by ZL on 2020/1/3.
 */
@org.apache.ibatis.annotations.Mapper
public interface InformMapper extends Mapper<Inform> {

    @Select("SELECT * FROM tbl_inform ORDER BY time DESC LIMIT 5")
    List<Inform> getInform();


    @Select("SELECT * FROM tbl_inform ORDER BY time DESC")
    List<Inform> getAll();

    @Insert("insert into tbl_inform values(#{id},#{content},#{time})")
    int insert(Inform inform);

    @Delete("delete from tbl_inform where id=#{id}")
    Integer deleteInform(String id);

    @Update("<script>" +
            "update tbl_inform set" +
            "<if test='content != null'>content=#{content},</if>" +
            "<if test='time != null'>time=#{time}</if>" +
            "where id=#{id}" +
            "</script>"
    )
    Integer updateInform(Inform inform);
}



