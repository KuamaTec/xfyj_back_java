package com.zgds.xfyj.dao;

import com.zgds.xfyj.domain.pojo.MerchantInfo;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by fengqiwei on 2019/5/6.
 */
@org.apache.ibatis.annotations.Mapper
public interface MerchantInfoMapper extends Mapper<MerchantInfo> {

    @Select("select * from tbl_merchant_info")
    List<MerchantInfo> getAll();

}



