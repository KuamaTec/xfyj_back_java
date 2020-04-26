package com.zgds.xfyj.dao;

import com.zgds.xfyj.domain.pojo.Express;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by Xiang ping Li on 2020/1/6.
 */
@org.apache.ibatis.annotations.Mapper
public interface ExpressMapper extends Mapper<Express> {

    @Select("select * from tbl_express where user_id=#{user_id} and order_ids=#{order_id}")
    List<Express> getExpressByUserIdOrderId(@Param(value = "user_id") String userId,@Param(value = "order_id") String orderId);



}



