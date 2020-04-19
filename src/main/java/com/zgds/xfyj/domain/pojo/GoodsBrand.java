package com.zgds.xfyj.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ZL
 * @descition 品牌：茅台、汾酒
 * @date 2020/1/3
 */
@Table(name = "tbl_goods_brand")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GoodsBrand {

    @Id
    private String id;//id主键
    private String classify_id;//分类id
    private String brand_name;//系列名称
    private String img;//系列名片图
    private Integer show_home;//首页是否显示
    private Integer rank;//首页显示顺序号
}
