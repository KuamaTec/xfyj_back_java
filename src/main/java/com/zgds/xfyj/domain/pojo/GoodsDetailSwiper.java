package com.zgds.xfyj.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ZL
 * @descition 产品详情图
 * @date 2020/1/3
 */
@Table(name = "tbl_goods_detail_imgs")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GoodsDetailSwiper {

    @Id
    private String id;//id主键
    private String img_url;//产品详情轮播图
    private String goods_id;//产品id

}
