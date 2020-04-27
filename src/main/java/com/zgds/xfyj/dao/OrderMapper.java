package com.zgds.xfyj.dao;

import com.zgds.xfyj.domain.pojo.Order;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by ZL on 2020/1/3.
 */
@org.apache.ibatis.annotations.Mapper
public interface OrderMapper extends Mapper<Order> {


    @Select("select * from tbl_order")
    List<Order> getAll();

    @Select("select * from tbl_order where user_id=#{user_id}")
    List<Order> getUserIdOrder(@Param(value = "user_id") String userId);

    @Select("select count(id) from tbl_order where user_id=#{user_id}")
    Integer allCount(@Param(value = "user_id") String userId);

    @Select("select * from tbl_order where order_status=#{order_status} and user_id=#{userId} order by update_time desc limit #{start},#{pageSize}")
    List<Order> getOrderByStatus(Integer order_status, String userId, Integer start, Integer pageSize);

    @Update("update tbl_order set order_status=#{order_status} where id=#{order_id} and user_id=#{userId}")
    Integer updateOrderStatus(Integer order_status, String userId, String order_id);

    @Delete("delete from tbl_order where id=#{id} and user_id=#{user_id}")
    Integer deleteOrderByIdUserId(String id, String user_id);

    @Delete("delete from tbl_order where id=#{id}")
    Integer deleteOrder(String id);

    @Update("<script>" +
            "update tbl_order set" +
            "<if test='order_name != null'>order_name=#{order_name},</if>" +
            "<if test='order_status != null'>order_status=#{order_status},</if>" +
            "<if test='order_price != null'>order_price=#{order_price},</if>" +
            "<if test='place_time != null'>place_time=#{place_time},</if>" +
            "<if test='update_time != null'>update_time=#{update_time},</if>" +
            "<if test='user_id != null'>user_id=#{user_id}</if>" +
            "where id=#{id}" +
            "</script>"
    )
    Integer updateOrder(Order order);
}



