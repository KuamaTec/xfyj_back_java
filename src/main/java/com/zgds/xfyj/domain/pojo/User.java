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
@Table(name = "tbl_user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    private String id;
    private String nick;
    private String open_id;
    private String head_photo;
    private String mobile;
    private String shipping_address;
    private String rigist_time;
}
