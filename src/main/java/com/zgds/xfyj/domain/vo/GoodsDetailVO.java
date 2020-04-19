package com.zgds.xfyj.domain.vo;

import com.zgds.xfyj.domain.pojo.Goods;
import com.zgds.xfyj.domain.pojo.GoodsDetailImgs;
import com.zgds.xfyj.domain.pojo.GoodsDetailSwiper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Xiang-ping li
 * @descition
 * @date 2020/1/5 0005  19:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoodsDetailVO {
    private Goods goods;
    private List<GoodsDetailImgs> goodsDetailImgs;
    private List<GoodsDetailSwiper> goodsDetailSwiper;
}
