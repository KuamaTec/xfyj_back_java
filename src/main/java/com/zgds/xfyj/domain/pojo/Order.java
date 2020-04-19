package com.zgds.xfyj.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Xiang-ping li
 * @descition 订单
 * @date 2020/1/3 0003  16:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_order")
@Builder
public class Order {
    @Id
    private String id;
    private String order_name;
    private String order_no;//订单编号（平台编号）
    private Integer order_status;
    private Double order_price;//订单金额
    private String place_time;//下单时间
    private String update_time;//更新时间
    private String cart_ids;//外键—购物车商品id（逗号分开）
    private String user_id;

}