package com.zgds.xfyj.dao;

import com.zgds.xfyj.domain.pojo.GoodsDetailSwiper;
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
public interface GoodsDetailSwiperMapper extends Mapper<GoodsDetailSwiper> {

    @Select("select * from tbl_goods_detail_swiper where goods_id=#{goods_id}")
    List<GoodsDetailSwiper> getAll(String goods_id);

    @Select("select * from tbl_goods_detail_swiper")
    List<GoodsDetailSwiper> list();

    @Insert("insert into tbl_goods_detail_swiper values(#{id},#{img_url},#{goods_id})")
    int insert(GoodsDetailSwiper goodsDetailSwiper);

    @Delete("delete from tbl_goods_detail_swiper where id=#{id}")
    Integer deleteDetailSwiper(String id);

    @Delete("delete from tbl_goods_detail_swiper where goods_id=#{goods_id}")
    Integer deleteGoodsDetailSwiper(String goods_id);

    @Update("<script>" +
            "update tbl_goods_detail_swiper set" +
            "<if test='img_url != null'>img_url=#{img_url},</if>" +
            "<if test='goods_id != null'>goods_id=#{goods_id}</if>" +
            "where id=#{id}" +
            "</script>"
    )
    Integer updateGoodsDetailSwiper(GoodsDetailSwiper goodsDetailSwiper);
}



