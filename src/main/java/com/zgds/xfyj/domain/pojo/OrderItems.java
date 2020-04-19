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
@Table(name = "tbl_order_item")
@Builder
public class OrderItems {
    @Id
    private String id;
    private String goods_name;//商品名称
    private String standard;//规格
    private Double sale_price;//价格
    private Integer is_discount;//是否打折（0-否，1-是）
    private Double dis_price;//折后价
    private Integer count;//数量
    private Double total_price;//小计
    private String main_img;//主图
    private String goods_id;//商品id
    private String order_id;//订单id
    private String cart_id;//购物车id
    private String user_id;//用户id

}