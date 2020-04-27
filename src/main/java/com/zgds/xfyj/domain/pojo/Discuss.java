package com.zgds.xfyj.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Xiang-ping li
 * @descition 评论
 * @date 2020/1/3 0003  9:33
 */
@Table(name = "tbl_discuss")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Discuss {

    @Id
    private String id;
    private String imgs;//评论图片
    private String content;//评论内容
    private String discuss_time;//评论时间
    private String goods_id;//商品id
    private String user_id;//用户id
}
