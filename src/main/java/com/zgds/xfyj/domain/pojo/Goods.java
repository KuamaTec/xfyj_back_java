package com.zgds.xfyj.domain.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author ZL
 * @descition 商品详情
 * @date 2020/1/3
 */
@Table(name = "tbl_goods")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Goods {

    @Id
    private String id;//id主键
    private String goods_name;//产品名称
    private String sale_price;//售价
    private String standard;//规格
    private String grade;//酒精度
    private String capacity;//容量
    private String is_discount;//是否打折：0-不打折 1-打折
    private String discount;//折扣
    private String produce_addr;//产地
    private String main_img;//产品图片
    private String recommend;//是否推荐
    private String discount_price;//折后价
    private String classify_id;//分类id
    private String brand_id;//系列id
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;//上架时间
    private String goods_status;//商品状态
    private Integer goods_repertory;//库存
}
