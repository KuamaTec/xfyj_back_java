package com.zgds.xfyj.dao;

import com.zgds.xfyj.domain.pojo.OrderItems;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by ZL on 2020/1/3.
 */
@org.apache.ibatis.annotations.Mapper
public interface OrderItemsMapper extends Mapper<OrderItems> {


    @Select("select * from tbl_order")
    List<OrderItems> getAll();

    @Select("select * from tbl_order where order_id=#{order_id}")
    List<OrderItems> getOrderId(String order_id);

}



