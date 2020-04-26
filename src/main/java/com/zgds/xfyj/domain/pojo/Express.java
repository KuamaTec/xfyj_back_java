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
@Table(name = "tbl_express")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Express {

    @Id
    private String id;
    private String express_company;
    private String express_no;
    private String update_time;
    private String order_ids;
    private String user_id;
}
