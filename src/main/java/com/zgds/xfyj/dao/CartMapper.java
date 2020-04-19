package com.zgds.xfyj.dao;

import com.zgds.xfyj.domain.pojo.Cart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by lxp on 2020/1/9.
 */
@org.apache.ibatis.annotations.Mapper
public interface CartMapper extends Mapper<Cart> {

    @Select("select * from tbl_shop_cart where user_id=#{user_id}")
    List<Cart> getAllByUserId(@Param(value = "user_id") String userId);

    @Select("SELECT * FROM tbl_shop_cart where user_id=#{user_id} order by insert_time desc limit #{start},#{pageSize}")
    List<Cart> selectByPage(@Param("user_id") String userId, @Param("start") Integer start, @Param("pageSize") Integer pageSize);

    @Select("select count(*) from tbl_shop_cart where user_id=#{user_id}")
    Integer allNumber(@Param(value = "user_id") String userId);

    @Select("update tbl_shop_cart set count = #{count} where user_id=#{user_id} and id=#{id}")
    void updateCountByIdUserId(@Param(value = "id") String id,
                               @Param(value = "user_id") String userId,
                               @Param(value = "count") Integer count);

    @Delete("delete from tbl_shop_cart where id=#{cart_id} and user_id=#{user_id}")
    Integer del(@Param(value = "user_id") String userId, @Param(value = "cart_id") String cart_id);


    @Select("select * from tbl_shop_cart where user_id=#{user_id} and goods_id=#{goods_id}")
    List<Cart> getByUserIdGoodsId(@Param(value = "user_id") String userId, @Param(value = "goods_id") String goodsId);

    /**
     * 再次添加相同的商品到购物车更新 该商品数量、总价、更新时间 而不是新增一条记录
     *
     * @param count
     * @param update_time
     * @param userId
     * @param goods_id
     * @return
     */
    @Update("update tbl_shop_cart set count=#{count},update_time=#{update_time} where goods_id=#{goods_id} and user_id=#{user_id}")
    Integer addSameGoods2Cart(@Param(value = "count") Integer count,
                              @Param(value = "update_time") String update_time,
                              @Param(value = "user_id") String userId,
                              @Param(value = "goods_id") String goods_id);

}



