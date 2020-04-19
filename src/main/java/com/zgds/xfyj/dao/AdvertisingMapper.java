package com.zgds.xfyj.dao;

import com.zgds.xfyj.domain.pojo.Advertising;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by ZL on 2020/1/3.
 */
@org.apache.ibatis.annotations.Mapper
public interface AdvertisingMapper extends Mapper<Advertising> {

    @Select("select * from tbl_advertising")
    List<Advertising> getAll();

}



