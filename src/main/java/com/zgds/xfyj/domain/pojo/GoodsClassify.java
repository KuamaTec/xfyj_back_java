package com.zgds.xfyj.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ZL
 * @descition 酒品分类
 * @date 2020/1/3
 */
@Table(name = "tbl_goods_classify")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GoodsClassify {

    @Id
    private String id;//id主键
    private String classify_name;//分类名称

}
