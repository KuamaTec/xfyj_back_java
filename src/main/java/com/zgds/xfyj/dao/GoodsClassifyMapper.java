package com.zgds.xfyj.dao;

import com.zgds.xfyj.domain.pojo.GoodsClassify;
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
public interface GoodsClassifyMapper extends Mapper<GoodsClassify> {

    @Select("select * from tbl_goods_classify")
    List<GoodsClassify> getAll();


    @Select("select * from tbl_goods_classify where id=#{id}")
    List<GoodsClassify> getClassifyName(String id);

    @Insert("insert into tbl_goods_classify values(#{id},#{classify_name})")
    int insert(GoodsClassify goodsClassify);

    @Update("update tbl_goods_classify set classify_name=#{classify_name} where id=#{id}")
    Integer updateGoodsClassify(GoodsClassify goodsClassify);

    @Delete("delete from tbl_goods_classify where id=#{id}")
    Integer deleteGoodsClassify(String id);
}



