package com.zgds.xfyj.dao;

import com.zgds.xfyj.domain.pojo.OrderDetail;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by ZL on 2020/1/3.
 */
@org.apache.ibatis.annotations.Mapper
public interface OrderDetailMapper extends Mapper<OrderDetail> {


    @Select("select * from tbl_order")
    List<OrderDetail> getAll();

    @Select("select * from tbl_order where order_id=#{order_id}")
    List<OrderDetail> getOrderId(String order_id);

}



