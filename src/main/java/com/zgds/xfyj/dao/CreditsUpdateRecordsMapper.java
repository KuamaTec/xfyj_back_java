package com.zgds.xfyj.dao;

import com.zgds.xfyj.domain.pojo.CreditsUpdateRecords;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by ZL on 2020/1/12
 */
@org.apache.ibatis.annotations.Mapper
public interface CreditsUpdateRecordsMapper extends Mapper<CreditsUpdateRecords> {

    @Select("SELECT * FROM tbl_credits_update_records where user_id=#{user_id} order by update_time desc limit #{currPage},#{pageSize}")
    List<CreditsUpdateRecords> getAll(String user_id, Integer currPage, Integer pageSize);

    @Select("SELECT current_credits FROM tbl_credits_update_records where user_id=#{user_id} order by update_time desc limit 1")
    Integer getCredits(String user_id);

    @Select("SELECT count(*) FROM tbl_credits_update_records where user_id=#{user_id}")
    Integer getCount(String user_id);

}



