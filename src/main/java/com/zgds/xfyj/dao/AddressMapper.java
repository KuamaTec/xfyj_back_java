package com.zgds.xfyj.dao;

import com.zgds.xfyj.domain.pojo.Address;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by Xiang ping Li on 2020/1/6.
 */
@org.apache.ibatis.annotations.Mapper
public interface AddressMapper extends Mapper<Address> {

    @Select("select * from tbl_address where user_id=#{user_id} order by is_default desc")
    List<Address> getAddressByUserId(@Param(value = "user_id") String userId);

    @Select("select * from tbl_address where user_id=#{user_id} and is_default='1' order by is_default desc limit 0,1")
    Address getDeafultAddressByUserId(@Param(value = "user_id") String userId);

    @Select("select * from tbl_address where user_id=#{user_id} and id=#{addr_id}")
    Address selectAddressByUserIdAddrId(@Param(value = "user_id") String userId, @Param(value = "addr_id") String addrId);

    /**
     * 更新指定用户的所有收货地址is_default为0
     *
     * @param userId
     * @return
     */
    @Update("update tbl_address set is_default=0 where user_id=#{user_id}")
    Integer updateIsDefault0(@Param(value = "user_id") String userId);

    /**
     * 更新指定用户的指定收货地址is_default为1
     *
     * @param id
     * @param userId
     * @return
     */
    @Update("update tbl_address set is_default=1 where user_id=#{user_id} and id=#{id}")
    Integer updateIsDefault1ByIdUserId(@Param(value = "id") String id, @Param(value = "user_id") String userId);
    /**
     * 获取 指定用户的 指定收货地址 的 详情
     *
     * @param id
     * @param userId
     * @return
     */
    @Select("select * from tbl_address where user_id=#{user_id} and id=#{id}")
    Address getByUserIdAddrId(@Param(value = "user_id") String userId, @Param(value = "id") String id);


}



