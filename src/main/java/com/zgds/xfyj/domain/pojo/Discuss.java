package com.zgds.xfyj.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Xiang-ping li
 * @descition 用户
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
    private String imgs;
    private String content;
    private String discuss_time;
    private String goods_id;
    private String user_id;
}
