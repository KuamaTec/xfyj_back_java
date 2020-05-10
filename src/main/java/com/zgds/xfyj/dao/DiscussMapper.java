package com.zgds.xfyj.dao;

import com.zgds.xfyj.domain.pojo.Discuss;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by lxp on 2020/1/5
 */
@org.apache.ibatis.annotations.Mapper
public interface DiscussMapper extends Mapper<Discuss> {

    @Select("select count(id) from tbl_discuss where goods_id=#{goods_id}")
    Integer allCounts(@Param(value = "goods_id") String goodsId);

    @Select("select count(*) from tbl_discuss where user_id=#{user_id}")
    Integer number(String user_id);

    @Select("select * from tbl_discuss where user_id=#{user_id} limit #{page},#{pageSize}")
    List<Discuss> getUserIdDiscuss(@Param(value = "user_id") String user_id,Integer page,Integer pageSize);

    @Delete("delete from tbl_discuss where id=#{id} and user_id=#{user_id}")
    Integer deleteByUserIdDiscussId(@Param(value = "user_id") String userId, @Param(value = "id") String id);

    /**
     * 分页
     *
     * @param start
     * @param pageSize
     * @return
     */
    @Select("SELECT * FROM tbl_discuss where goods_id=#{goods_id} order by discuss_time desc limit #{start},#{pageSize}")
    List<Discuss> selectByPage(@Param(value = "goods_id") String goodsId,
                               @Param("start") Integer start,
                               @Param("pageSize") Integer pageSize);


}



