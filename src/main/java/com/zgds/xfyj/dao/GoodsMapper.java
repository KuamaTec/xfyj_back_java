package com.zgds.xfyj.dao;

import com.zgds.xfyj.domain.pojo.Goods;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by ZL on 2020/1/3.
 */
@org.apache.ibatis.annotations.Mapper
public interface GoodsMapper extends Mapper<Goods> {

    @Select("select * from tbl_goods limit #{currPage},#{pageSize}")
    List<Goods> getAllGoods(Integer currPage,Integer pageSize);

    @Select("select * from tbl_goods where brand_id=#{brand_id}")
    List<Goods> getAllBrand(String brand_id);

    @Select("select * from tbl_goods where id=#{id}")
    Goods getGoodsId(String id);

    @Select("select * from tbl_goods where recommend=1 and classify_id=#{classify_id} limit #{currPage},#{pageSize}")
    List<Goods> getRecommend(String classify_id, Integer currPage, Integer pageSize);

    @Select("select count(*) from tbl_goods")
    Integer number();

    @Select("select count(*) from tbl_goods where recommend=1")
    Integer getCountRecommend();

    @Select("select * from tbl_goods where is_discount='1' and classify_id=#{classify_id} limit #{currPage},#{pageSize}")
    List<Goods> getIsDiscount(String classify_id, Integer currPage, Integer pageSize);

    @Select("select count(*) from tbl_goods where is_discount='1'")
    Integer getCountIsDiscount();

    @Insert("insert into tbl_goods values(#{id},#{goods_name},#{sale_price}," +
            "#{standard},#{produce_addr},#{main_img},#{recommend}," +
            "#{classify_id},#{brand_id},#{grade},#{is_discount}," +
            "#{discount},#{discount_price},#{capacity},#{time})")
    int insert(Goods goods);

    @Delete("delete from tbl_goods where id=#{id}")
    Integer deleteGoods(String id);


    @Update("<script>" +
            "update tbl_goods set" +
            "<if test='goods_name != null'>goods_name=#{goods_name},</if>" +
            "<if test='sale_price != null'>sale_price=#{sale_price},</if>" +
            "<if test='standard != null'>standard=#{standard},</if>" +
            "<if test='produce_addr != null'>produce_addr=#{produce_addr},</if>" +
            "<if test='main_img != null'>main_img=#{main_img},</if>" +
            "<if test='recommend != null'>recommend=#{recommend},</if>" +
            "<if test='classify_id != null'>classify_id=#{classify_id},</if>" +
            "<if test='brand_id != null'>brand_id=#{brand_id},</if>" +
            "<if test='grade != null'>grade=#{grade},</if>" +
            "<if test='is_discount != null'>is_discount=#{is_discount},</if>" +
            "<if test='discount != null'>discount=#{discount},</if>" +
            "<if test='discount_price != null'>discount_price=#{discount_price},</if>" +
            "<if test='capacity != null'>capacity=#{capacity},</if>" +
            "<if test='time != null'>time=#{time}</if>" +
            "where id=#{id}" +
            "</script>"
    )
    Integer updateGoods(Goods goods);

}



