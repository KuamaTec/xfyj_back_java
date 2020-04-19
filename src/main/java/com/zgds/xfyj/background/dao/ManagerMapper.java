package com.zgds.xfyj.background.dao;

import com.zgds.xfyj.background.pojo.Manager;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 商家mapper
 */
@org.apache.ibatis.annotations.Mapper
public interface ManagerMapper extends Mapper<Manager> {

    @Select("select * from tbl_manager")
    List<Manager> getAll();

    //根据用户名、密码查询
    @Select("SELECT * FROM tbl_manager where username=#{username} and password=#{pwd}")
    List<Manager> selectByMobilePwd(@Param("username") String username, @Param("pwd") String pwd);
}



