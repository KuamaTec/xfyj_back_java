package com.zgds.xfyj.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

/**
 * @author Xiang-ping li
 * @descition 购物车VO
 * @date 2020/1/9 0003  9:33
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartVO {

    @Id
    private String id;
    private String goods_name;
    private String main_img;
    private String standard;//规格
    private String capacity;//容量
    private Double sale_price;
    private Integer is_discount;//是否打折 0-否 1-是
    private Double dis_price;//折后价
    private Integer count;//操作数量
    private Double total_price;//小计
    private String insert_time;//添加到购物车的时间
    private String update_time;//更新时间
    private String goods_id;
    private String user_id;
}
