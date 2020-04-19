package com.zgds.xfyj.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Xiang-ping li
 * @descition 收货地址
 * @date 2020/1/6 0003  9:33
 */
@Table(name = "tbl_address")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {

    @Id
    private String id;
    private String recieve_name;
    private String detail_address;
    private String mobile1;
    private String mobile2;
    private String province;
    private String city;
    private String region;
    private String update_time;
    private Integer is_default;//是否默认收货地址 0-否 1-是
    private String user_id;
}
