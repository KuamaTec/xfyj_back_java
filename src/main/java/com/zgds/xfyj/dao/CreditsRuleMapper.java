package com.zgds.xfyj.dao;

import com.zgds.xfyj.domain.pojo.CreditsRule;
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
public interface CreditsRuleMapper extends Mapper<CreditsRule> {

    @Select("SELECT * FROM tbl_credits_increase_rule")
    List<CreditsRule> getAll();

    @Insert("insert into tbl_credits_increase_rule values(#{id},#{present_percent},#{min_order_price},#{max_order_price})")
    int insert(CreditsRule creditsRule);

    @Delete("delete from tbl_credits_increase_rule where id=#{id}")
    Integer deleteCreditsRule(String id);

    @Update("<script>" +
            "update tbl_credits_increase_rule set" +
            "<if test='present_percent != null'>present_percent=#{present_percent},</if>" +
            "<if test='min_order_price != null'>min_order_price=#{min_order_price},</if>" +
            "<if test='max_order_price != null'>max_order_price=#{max_order_price}</if>" +
            "where id=#{id}" +
            "</script>"
    )
    Integer updateCreditsRule(CreditsRule creditsRule);
}


