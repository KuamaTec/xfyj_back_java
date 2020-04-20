package com.zgds.xfyj.dao;

import com.zgds.xfyj.domain.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by fengqiwei on 2019/5/6.
 */
@org.apache.ibatis.annotations.Mapper
public interface UserMapper extends Mapper<User> {

    @Select("select * from tbl_user")
    List<User> getAll();

    /**
     * 分页
     *
     * @param start
     * @param pageSize
     * @return
     */
//    @Select("SELECT * FROM tbl_user order by forum_time desc limit #{start},#{pageSize}")
    @Select("SELECT * FROM tbl_user limit #{start},#{pageSize}")
    List<User> selectByPage(@Param("start") Integer start, @Param("pageSize") Integer pageSize);


    @Select("select count(*) from tbl_user")
    Integer allNumber();

    //根据手机号、密码查询
    @Select("SELECT * FROM tbl_user where mobile=#{mobile} and pwd=#{pwd}")
    List<User> selectByMobilePwd(@Param("mobile") String mobile, @Param("pwd") String pwd);

    //根据手机号
    @Select("SELECT * FROM tbl_user where mobile=#{mobile}")
    List<User> selectByMobile(@Param("mobile") String mobile);

    //根据用户ID
    @Select("SELECT * FROM tbl_user where id=#{id}")
    User selectById(@Param("id") String id);

    //上传头像
    @Update("update tbl_user set user_logo=#{user_logo} where id=#{id}")
    Integer upUserLogo(@Param("id") String id, @Param("user_logo") String user_logo);

    //修改昵称
    @Update("update tbl_user set username=#{username} where id=#{id}")
    Integer upUserName(@Param("id") String id, @Param("username") String username);
}



