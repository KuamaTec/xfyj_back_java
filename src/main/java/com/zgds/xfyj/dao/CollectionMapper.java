package com.zgds.xfyj.dao;

import com.zgds.xfyj.domain.pojo.Collection;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by Lxp on 2020/1/3.
 */
@org.apache.ibatis.annotations.Mapper
public interface CollectionMapper extends Mapper<Collection> {

    @Select("select count(id) from tbl_collection where user_id=#{user_id}")
    Integer allCollectionCounts(@Param("user_id") String userId);

    @Select("SELECT * FROM tbl_collection where user_id=#{user_id} order by collect_time desc limit #{start},#{pageSize}")
    List<Collection> getCollections(@Param("user_id") String userId, @Param("start") Integer start, @Param("pageSize") Integer pageSize);

    @Delete("delete from tbl_collection where user_id = #{user_id} and goods_id = #{goods_id}")
    Integer deleteCollection(@Param("goods_id") String goods_id, @Param("user_id") String userId);

    @Select("SELECT count(id) FROM tbl_collection where user_id=#{user_id} and goods_id=#{goods_id}")
    Integer isCollected(@Param("goods_id") String goodsId, @Param("user_id") String userId);

    @Select("select * from tbl_collection where user_id=#{user_id} limit #{page},#{pageSize}")
    List<Collection> getUserIdCollection(String user_id,Integer page,Integer pageSize);

    @Select("select count(*) from tbl_collection")
    Integer number();
}



