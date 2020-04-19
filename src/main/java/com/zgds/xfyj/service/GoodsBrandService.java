package com.zgds.xfyj.service;

import com.zgds.xfyj.dao.GoodsBrandMapper;
import com.zgds.xfyj.domain.pojo.GoodsBrand;
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

@Service
@Slf4j
public class GoodsBrandService {
    private final Logger logger = LoggerFactory.getLogger(GoodsBrandService.class);

    @Autowired
    private GoodsBrandMapper mapper;
    @Autowired
    private AliOSSUtils aliOSSUtils;

    public ServerResponse getAll() {
        log.info("用户查询所有酒类品牌：GoodsBrandMapper.getAll（）");
        return ServerResponse.createBySuccess("查询成功！", mapper.getAll());
    }

    public ServerResponse getBrand(String brand_id) {
        log.info("用户根据id查询酒类品牌：GoodsBrandMapper.getBrand（）", brand_id);
        return ServerResponse.createBySuccess("查询成功！", mapper.getBrand(brand_id));
    }

    public ServerResponse getGoodsBrand(String classify_id) {
        log.info("用户根据分类id查询品牌列表：GoodsBrandMapper.getGoodsBrand（）", classify_id);
        return ServerResponse.createBySuccess("查询成功！", mapper.getGoodsBrand(classify_id));
    }

    /**
     * 获取需要在首页展示的所有系列
     *
     * @return
     */
    public ServerResponse getShowHome() {
        log.info("用户根据show_home（首页是否显示）查询首页显示产品列表：GoodsBrandMapper.getShowHome（）");
        return ServerResponse.createBySuccess("查询成功！", mapper.getShowHome());
    }


    public ServerResponse insert(GoodsBrand goodsBrand, MultipartFile file) {
        goodsBrand.setId(UUIDUtils.generateId());
        if (!file.isEmpty()) {
            try {
                goodsBrand.setImg(aliOSSUtils.uploadImg(file, "GoodsBrand"));
                log.info("用户添加品牌上传文件成功！");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                log.info("用户添加品牌上传文件错误", e.getLocalizedMessage());
            }
        }
        int i = mapper.insert(goodsBrand);
        if (i > 0) {
            log.info("用户添加品牌成功：GoodsBrandMapper.insert（）", goodsBrand);
            return ServerResponse.createBySuccessMessages("添加成功！");
        } else {
            log.info("用户添加品牌失败：GoodsBrandMapper.insert（）", goodsBrand);
            return ServerResponse.createBySuccessMessages("添加失败！");
        }
    }

    public ServerResponse updateGoodsBrand(GoodsBrand goodsBrand, MultipartFile file) {
        int i = mapper.updateGoodsBrand(goodsBrand);
        if (file != null) {
            if (!file.isEmpty()) {
                try {
                    goodsBrand.setImg(aliOSSUtils.uploadImg(file, "GoodsBrand"));
                    log.info("用户修改品牌上传文件成功");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    log.info("用户修改品牌上传文件错误", e.getLocalizedMessage());
                }
            }
        }
        if (i > 0) {
            log.info("用户修改品牌成功：GoodsBrandMapper.updateGoodsBrand（）", goodsBrand);
            return ServerResponse.createBySuccessMessages("修改成功！");
        } else {
            log.info("用户修改品牌失败：GoodsBrandMapper.updateGoodsBrand（）", goodsBrand);
            return ServerResponse.createBySuccessMessages("修改失败！");
        }
    }

    public ServerResponse deleteGoodsBrand(String id) {
        int i = mapper.deleteGoodsBrand(id);
        if (i > 0) {
            log.info("用户删除品牌成功：GoodsBrandMapper.deleteGoodsBrand（）", id);
            return ServerResponse.createBySuccessMessages("删除成功！");
        } else {
            log.info("用户删除品牌失败：GoodsBrandMapper.deleteGoodsBrand（）", id);
            return ServerResponse.createBySuccessMessages("删除失败！");
        }
    }
}
