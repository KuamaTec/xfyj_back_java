package com.zgds.xfyj.dao;

import com.zgds.xfyj.domain.pojo.CreditsConsume;
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
public interface CreditsConsumeMapper extends Mapper<CreditsConsume> {

    @Select("SELECT * FROM tbl_credits_consume_rule")
    List<CreditsConsume> getAll();

    @Insert("insert into tbl_credits_consume_rule values(#{id},#{min_order_price},#{consume_percent})")
    int insert(CreditsConsume creditsConsume);

    @Delete("delete from tbl_credits_consume_rule where id=#{id}")
    Integer deleteCreditsConsume(String id);

    @Update("<script>" +
            "update tbl_credits_consume_rule set" +
            "<if test='min_order_price != null'>min_order_price=#{min_order_price},</if>" +
            "<if test='consume_percent != null'>consume_percent=#{consume_percent}</if>" +
            "where id=#{id}"+
            "</script>"
    )
    Integer updateCreditsConsume(CreditsConsume creditsConsume);
}



