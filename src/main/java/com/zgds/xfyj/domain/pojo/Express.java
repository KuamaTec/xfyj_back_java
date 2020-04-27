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
    private String express_company;//快递公司
    private String express_no;//快递单号
    private String update_time;//更新时间（商家操作）
    private String order_ids;//订单id
    private String user_id;//用户id

}
