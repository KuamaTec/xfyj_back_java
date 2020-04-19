package com.zgds.xfyj.dao;

import com.zgds.xfyj.domain.pojo.GoodsBrand;
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
public interface GoodsBrandMapper extends Mapper<GoodsBrand> {

    @Select("select * from tbl_goods_brand")
    List<GoodsBrand> getAll();

    @Select("select * from tbl_goods_brand where id=#{brand_id}")
    GoodsBrand getBrand(String brand_id);

    @Select("select * from tbl_goods_brand where classify_id=#{classify_id}")
    List<GoodsBrand> getGoodsBrand(String classify_id);

    @Select("select * from tbl_goods_brand where show_home=1 order by rank")
    List<GoodsBrand> getShowHome();


    @Insert("insert into tbl_goods_brand values(#{id},#{classify_id},#{brand_name},#{img},#{show_home},#{rank})")
    int insert(GoodsBrand goodsClassify);

    @Update("<script>" +
            "update tbl_goods_brand set " +
            "<if test='classify_id != null'>classify_id=#{classify_id},</if>" +
            "<if test='brand_name != null'>brand_name=#{brand_name},</if>" +
            "<if test='img != null'>img=#{img},</if>" +
            "<if test='show_home != null'>show_home=#{show_home},</if>" +
            "<if test='rank != null'>rank=#{rank}</if>" +
            "where id=#{id}" +
            "</script>"
    )
    Integer updateGoodsBrand(GoodsBrand goodsClassify);

    @Delete("delete from tbl_goods_brand where id=#{id}")
    Integer deleteGoodsBrand(String id);
}



