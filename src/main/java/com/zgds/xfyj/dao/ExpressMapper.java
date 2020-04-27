package com.zgds.xfyj.dao;

import com.zgds.xfyj.domain.pojo.Express;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by ZL on 2020/4/26.
 */
@org.apache.ibatis.annotations.Mapper
public interface ExpressMapper extends Mapper<Express> {

    @Select("select * from tbl_express where user_id=#{user_id} and order_ids=#{order_id}")
    List<Express> getExpressByUserIdOrderId(@Param(value = "user_id") String userId,@Param(value = "order_id") String orderId);


    /**
     * 查询所有物流信息
     * @param start
     * @param pageSize
     * @return
     */
    @Select("select * from tbl_express limit #{start},#{pageSize}")
    List<Express> getAll(Integer start, Integer pageSize);

    /**
     * 根据id查询物流信息
     * @param id
     * @return
     */
    @Select("select * from tbl_express where id=#{id}")
    List<Express> getUserIdExpress(@Param(value = "id") String id);

    /**
     * 添加物流信息
     * @param express
     * @return
     */
    @Insert("insert into tbl_express values(#{id},#{express_company},#{express_no},#{update_time},#{order_ids},#{user_id})")
    int insert(Express express);

    /**
     * 根据id删除物流信息
     * @param id
     * @return
     */
    @Delete("delete from tbl_express where id=#{id}")
    Integer deleteExpress(String id);

    /**
     * 修改物流信息
     * @param express
     * @return
     */
    @Update("<script>" +
            "update tbl_express set" +
            "<if test='express_company != null'>express_company=#{express_company},</if>" +
            "<if test='express_no != null'>express_no=#{express_no},</if>" +
            "<if test='update_time != null'>update_time=#{update_time},</if>" +
            "<if test='order_ids != null'>order_ids=#{order_ids},</if>" +
            "<if test='user_id != null'>user_id=#{user_id}</if>" +
            "where id=#{id}" +
            "</script>"
    )
    Integer updateExpress(Express express);
}



