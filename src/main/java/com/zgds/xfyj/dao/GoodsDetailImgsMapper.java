package com.zgds.xfyj.dao;

import com.zgds.xfyj.domain.pojo.GoodsDetailImgs;
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
public interface GoodsDetailImgsMapper extends Mapper<GoodsDetailImgs> {

    @Select("select * from tbl_goods_detail_imgs where goods_id=#{goods_id} GROUP BY img_order")
    List<GoodsDetailImgs> getAll(String goods_id);


    @Select("select * from tbl_goods_detail_imgs")
    List<GoodsDetailImgs> list();

    @Insert("insert into tbl_goods_detail_imgs values(#{id},#{img_url},#{img_order},#{goods_id})")
    int insert(GoodsDetailImgs goodsDetailImgs);

    @Delete("delete from tbl_goods_detail_imgs where id=#{id}")
    Integer deleteDetailImgs(String id);

    @Delete("delete from tbl_goods_detail_imgs where goods_id=#{goods_id}")
    Integer deleteGoodsDetailImgs(String goods_id);

    @Update("<script>" +
            "update tbl_goods_detail_imgs set" +
            "<if test='img_url != null'>img_url=#{img_url},</if>" +
            "<if test='img_order != null'>img_order=#{img_order},</if>" +
            "<if test='goods_id != null'>goods_id=#{goods_id}</if>" +
            "where id=#{id}" +
            "</script>"
    )
    Integer updateGoodsDetailImgs(GoodsDetailImgs goodsDetailImgs);
}



