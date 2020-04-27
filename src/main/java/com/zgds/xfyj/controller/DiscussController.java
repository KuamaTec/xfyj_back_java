package com.zgds.xfyj.controller;

import com.zgds.xfyj.dao.DiscussMapper;
import com.zgds.xfyj.dao.GoodsMapper;
import com.zgds.xfyj.dao.UserMapper;
import com.zgds.xfyj.domain.pojo.Discuss;
import com.zgds.xfyj.domain.pojo.Goods;
import com.zgds.xfyj.domain.pojo.User;
import com.zgds.xfyj.domain.vo.DiscussVO;
import com.zgds.xfyj.util.ServerResponse;
import com.zgds.xfyj.util.UUIDUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.ListUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author lxp
 * @descition
 * @date 2020/1/5
 */
@Api(description = "商品评价API")
@Controller
@RequestMapping("/discuss")
@Slf4j
public class DiscussController {

    @Autowired
    private DiscussMapper discussMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    /**
     * 添加评论
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/discuss/add")
    @ApiOperation(value = "添加评论", notes = "添加评论", httpMethod = "POST")
    public ServerResponse add(@RequestParam(value = "imgs") String imgs,
                              @RequestParam(value = "content") String content,
                              @RequestParam(value = "goods_id") String goods_id,
                              @RequestParam(value = "user_id") String user_id) {
        ServerResponse serverResponse = null;

        try {
            //1、创建discuss对象
            Discuss discuss = Discuss.builder()
                    .id(UUIDUtils.generateId())
                    .imgs(imgs)
                    .content(content)
                    .discuss_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()))
                    .goods_id(goods_id)
                    .user_id(user_id)
                    .build();
            int i = discussMapper.insert(discuss);
            if (i == 1) {
                serverResponse = ServerResponse.createBySuccess("评论发布成功", discuss);
                log.info("用户{}发布评论成功，商品{}", user_id, goods_id);
            } else {
                serverResponse = ServerResponse.createBySuccess("评论发布失败");
                log.info("用户{}发布评论失败，商品{}", user_id, goods_id);
            }

        } catch (Exception e) {
            e.printStackTrace();
            serverResponse = ServerResponse.createByErrorMessage("发布异常");
            log.info("用户{}发布评论异常，商品{}", user_id, goods_id);
        } finally {
            return serverResponse;
        }

    }

    /**
     * 删除评论
     *
     * @param discuss_id
     * @param user_id
     * @return
     */
    @ResponseBody
    @RequestMapping("/discuss/del")
    @ApiOperation(value = "删除评论", notes = "删除评论", httpMethod = "POST")
    public ServerResponse add(@RequestParam(value = "discuss_id") String discuss_id,
                              @RequestParam(value = "user_id") String user_id) {
        ServerResponse serverResponse = null;

        try {
            int i = discussMapper.deleteByUserIdDiscussId(user_id, discuss_id);
            if (i == 1) {
                serverResponse = ServerResponse.createBySuccess("删除评论成功");
                log.info("用户{}删除评论{}成功", user_id, discuss_id);
            } else {
                serverResponse = ServerResponse.createBySuccess("删除评论失败");
                log.info("用户{}删除评论{}失败", user_id, discuss_id);
            }

        } catch (Exception e) {
            e.printStackTrace();
            serverResponse = ServerResponse.createByErrorMessage("删除评论异常");
            log.info("用户{}删除评论{}异常，错误信息：{}", user_id, discuss_id, e.getLocalizedMessage());
        } finally {
            return serverResponse;
        }

    }

    @ResponseBody
    @RequestMapping("/discuss/list")
    @ApiOperation(value = "获取评论列表", notes = "获取评论列表", httpMethod = "POST")
    public ServerResponse getCollections(@RequestParam(value = "goodsId") String goodsId,
                                         @RequestParam(value = "currPage") Integer currPage,
                                         @RequestParam(value = "pageSize") Integer pageSize) {
        ServerResponse serverResponse = null;
        List<Discuss> list = null;
        List<DiscussVO> list1 = null;
        try {
            Integer totalCount = discussMapper.allCounts(goodsId);
            //总页数
            int totalPage = 0;
            if ((totalCount % pageSize) == 0) {
                totalPage = totalCount / pageSize;
            } else {
                totalPage = (totalCount / pageSize) + 1;
            }
            // 由于小程序scroll-view下拉请求每次都会将currPage加1，所以当当前页大于总页数时，返回null
            if (currPage > totalPage) {
                return ServerResponse.createByErrorMessage("页码错误");
            }
            int startIndex = (currPage - 1) * pageSize;

            list = discussMapper.selectByPage(goodsId, startIndex, pageSize);
            if (!ListUtils.isEmpty(list)) {
                list1 = new ArrayList<>();
            }
            for (Discuss discuss :
                    list) {
                String user_id = discuss.getUser_id();
                User user = userMapper.selectByPrimaryKey(user_id);
                //组装vo
                DiscussVO discussVO = DiscussVO.builder()
                        .discuss(discuss)
                        .user(user)
                        .build();
                list1.add(discussVO);
            }

            serverResponse = ServerResponse.createBySuccess("获取商品评论列表成功", list1);
            log.info("获取商品评论列表成功，商品id：" + goodsId);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("获取商品评论列表失败，商品id：{}，失败原因：{}", goodsId, e.getLocalizedMessage());
            serverResponse = ServerResponse.createByErrorMessage("获取商品评论列表失败，商品id：" + goodsId);
        } finally {
            return serverResponse;
        }

    }

    @ResponseBody
    @RequestMapping("/getUserIdDiscuss")
    @ApiOperation(value = "管路员获取用户评论列表", notes = "管路员获取用户评论列表", httpMethod = "POST")
    public ServerResponse getUserIdDiscuss(@RequestParam(value = "user_id") String user_id) {
        log.info("管理员开始查询用户评论列表","getUserIdDiscuss(),user_id="+user_id);
        List<Discuss> discussList =discussMapper.getUserIdDiscuss(user_id);
        List list = new ArrayList();
        for (Discuss li:discussList) {
            Map map = new HashMap();
            Goods goods = goodsMapper.getGoodsId(li.getGoods_id());
            map.put("id",li.getId());
            map.put("goods_name",goods.getGoods_name());
            map.put("sale_price",goods.getSale_price());
            map.put("main_img",goods.getMain_img());
            map.put("imgs",li.getImgs());
            map.put("content",li.getContent());
            map.put("discuss_time",li.getDiscuss_time());
            list.add(map);
        }
        log.info("管理员查询用户评论列表成功！","getUserIdDiscuss(),user_id="+user_id);

        return ServerResponse.createBySuccess(list);
    }
}
