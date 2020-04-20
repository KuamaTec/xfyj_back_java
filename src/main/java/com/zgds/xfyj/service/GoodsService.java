package com.zgds.xfyj.service;

import com.zgds.xfyj.dao.GoodsBrandMapper;
import com.zgds.xfyj.dao.GoodsClassifyMapper;
import com.zgds.xfyj.dao.GoodsMapper;
import com.zgds.xfyj.domain.pojo.Goods;
import com.zgds.xfyj.domain.pojo.GoodsBrand;
import com.zgds.xfyj.domain.pojo.GoodsClassify;
import com.zgds.xfyj.util.AliOSSUtils;
import com.zgds.xfyj.util.ServerResponse;
import com.zgds.xfyj.util.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class GoodsService {
    private final Logger logger = LoggerFactory.getLogger(GoodsService.class);

    @Autowired
    private GoodsMapper mapper;
    @Autowired
    private GoodsClassifyMapper classifyMapper;
    @Autowired
    private GoodsBrandMapper brandMapper;
    @Autowired
    private GoodsDetailImgsService imgsService;
    @Autowired
    private GoodsDetailSwiperService swiperService;
    @Autowired
    private AliOSSUtils aliOSSUtils;

    private String Mapper = "GoodsMapper";

    public ServerResponse getAllGoods(){
        log.info("用户开始查询商品详情："+Mapper+".getAllBrand()");
        List<Goods> list = mapper.getAllGoods();
        List<GoodsClassify> goodsClassifyList = classifyMapper.getAll();
        List<GoodsBrand> goodsBrandList = brandMapper.getAll();
        log.info("用户开始查询商品详情,转换商品分类id：classifyMapper.getClassifyName");
        log.info("用户开始查询商品详情,转换商品系列id：brandMapper.getBrand");
        for (Goods li:list) {
            /*li.setClassify_id(classifyMapper.getClassifyName(li.getClassify_id()).getClassify_name());
            li.setBrand_id(brandMapper.getBrand(li.getBrand_id()).getBrand_name());*/
            for (GoodsClassify gList:goodsClassifyList) {
                if (gList.getId().equals(li.getClassify_id())){
                    li.setClassify_id(gList.getClassify_name());
                    break;
                }
            }
            for (GoodsBrand gList:goodsBrandList) {
                if (gList.getId().equals(li.getBrand_id())){
                    li.setBrand_id(gList.getBrand_name());
                    break;
                }
            }
        }
        return ServerResponse.createBySuccess("商品详情查询成功！",list);
    }

    public ServerResponse getShowGoods(String brand_id) {
        //1、查询商品信息
        log.info("用户跟据系列id查询产品信息：GoodsMapper.getAllBrand()", brand_id);
        List<Goods> goodsList = mapper.getAllBrand(brand_id);
        //2、查询酒系列信息
        log.info("用户跟据系列id查询系列信息：GoodsBrandMapper.getBrand()", brand_id);
        GoodsBrand goodsBrand = brandMapper.getBrand(brand_id);
        List list = new ArrayList();
        list.add(goodsList);
        list.add(goodsBrand.getImg());
        log.info("首页推荐系列酒类查询成功！");
        return ServerResponse.createBySuccess("查询成功！", list);
    }

    public ServerResponse getGoods(String brand_id) {
        log.info("用户跟据系列id查询产品信息：GoodsMapper.getGoods()", brand_id);
        return ServerResponse.createBySuccess("查询成功！", mapper.getAllBrand(brand_id));
    }

    public ServerResponse getGoodsId(String id) {
        log.info("用户跟据id查询产品信息：GoodsMapper.getGoodsId()", id);
        return ServerResponse.createBySuccess("查询成功！", mapper.getGoodsId(id));
    }

    public ServerResponse getRecommend(String classify_id, Integer currPage, Integer pageSize) {
        int totalPage = 0;
        int totalCount = mapper.getCountRecommend();
        if ((totalCount % pageSize) == 0) {
            totalPage = totalCount / pageSize;
        } else {
            totalPage = (totalCount / pageSize) + 1;
        }
        if (currPage > totalPage) {
            return ServerResponse.createBySuccessMessages("页码错误");
        }
        int startIndex = (currPage - 1) * pageSize;
        return ServerResponse.createBySuccess("查询成功！", mapper.getRecommend(classify_id, startIndex, pageSize));
    }

    public ServerResponse getIsDiscount(String classify_id, Integer currPage, Integer pageSize) {
        int totalPage = 0;
        int totalCount = mapper.getCountIsDiscount();
        if ((totalCount % pageSize) == 0) {
            totalPage = totalCount / pageSize;
        } else {
            totalPage = (totalCount / pageSize) + 1;
        }
        if (currPage > totalPage) {
            return ServerResponse.createBySuccessMessages("页码错误");
        }
        int startIndex = (currPage - 1) * pageSize;
        return ServerResponse.createBySuccess("查询成功！", mapper.getIsDiscount(classify_id, startIndex, pageSize));
    }


    /**
     * 添加商品信息
     * @param goods
     * @param file
     * @return
     */
    public ServerResponse insertAll(Goods goods, MultipartFile file,MultipartFile file1,MultipartFile file2){
        System.out.println(goods.toString()+"-----"+file.isEmpty()+"---------"+file1.isEmpty()+"------------"+file2.isEmpty());
        /*String id=UUIDUtils.generateId();
        goods.setId(id);
        goods.setTime(new Date());
        if (!file.isEmpty()){
            try {
                log.info("添加产品信息，上传产品图片成功！");
                goods.setMain_img(aliOSSUtils.uploadImg(file,"Goods"));
            } catch (FileNotFoundException e) {
                log.info("添加产品信息，上传产品图片失败:",e.getLocalizedMessage());
                e.printStackTrace();
            }
        }
        Integer i=mapper.insert(goods);
        if (i>0){
            log.info("添加产品详情信息成功:"+Mapper+".insert()");
            GoodsDetailImgs goodsDetailImgs = new GoodsDetailImgs();
            goodsDetailImgs.setId(UUIDUtils.generateId());
            goodsDetailImgs.setGoods_id(id);
            imgsService.insert(goodsDetailImgs,file1);

            GoodsDetailSwiper goodsDetailSwiper = new GoodsDetailSwiper();
            goodsDetailSwiper.setId(UUIDUtils.generateId());
            goodsDetailSwiper.setGoods_id(id);
            swiperService.insert(goodsDetailSwiper,file2);


            log.info("添加产品信息成功:"+Mapper+".insert()");
            return ServerResponse.createBySuccessMessages("添加成功！");
        }else {
            log.info("添加产品信息失败:"+Mapper+".insert()");
            return ServerResponse.createByErrorMessage("添加失败！");
        }*/
        return null;
    }

    /**
     * 根据id删除商品信息
     *
     * @param id
     * @return
     */
    public ServerResponse deleteGoods(String id) {
        Integer i = mapper.deleteGoods(id);
        if (i > 0) {
            log.info("删除产品信息成功:" + Mapper + ".deleteGoods()");
            return ServerResponse.createBySuccessMessages("删除成功！");
        } else {
            log.info("删除产品信息失败:" + Mapper + ".deleteGoods()");
            return ServerResponse.createByErrorMessage("删除失败！");
        }

    }

    /**
     * 修改商品信息
     *
     * @param goods
     * @return
     */
    public ServerResponse updateGoods(Goods goods, MultipartFile file) {
        goods.setTime(new Date());
        if (file != null) {
            if (!file.isEmpty()) {
                try {
                    log.info("修改产品信息，上传文件成功!");
                    goods.setMain_img(aliOSSUtils.uploadImg(file, "Goods"));
                } catch (FileNotFoundException e) {
                    log.info("修改产品信息，上传文件错误:", e.getLocalizedMessage());
                    e.printStackTrace();
                }
            }
        } else {
            goods.setMain_img(null);
        }
        Integer i = mapper.updateGoods(goods);
        if (i > 0) {
            log.info("修改产品信息成功:" + Mapper + ".updateGoods()");
            return ServerResponse.createBySuccessMessages("修改成功！");
        } else {
            log.info("修改产品信息失败:" + Mapper + ".updateGoods()");
            return ServerResponse.createByErrorMessage("修改失败！");
        }

    }
}