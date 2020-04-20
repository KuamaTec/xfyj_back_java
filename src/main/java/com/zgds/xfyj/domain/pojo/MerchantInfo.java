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
@Table(name = "tbl_merchant_info")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MerchantInfo {

    @Id
    private String id;
    private String merchant_name;
    private String password;
    private String tel_phone;
    private String merchant_intro;
}
