package com.zgds.xfyj.service;

import com.zgds.xfyj.dao.CollectionMapper;
import com.zgds.xfyj.dao.CreditsUpdateRecordsMapper;
import com.zgds.xfyj.dao.GoodsMapper;
import com.zgds.xfyj.dao.UserMapper;
import com.zgds.xfyj.domain.pojo.Collection;
import com.zgds.xfyj.domain.pojo.Goods;
import com.zgds.xfyj.domain.pojo.User;
import com.zgds.xfyj.util.ServerResponse;
import com.zgds.xfyj.util.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper mapper;
    @Autowired
    private CreditsUpdateRecordsMapper creditsUpdateRecordsMapper;
    /**
     * 查询所有用户信息
     * @return
     */
    public ServerResponse getAll(){
        List<User> userList = mapper.getAll();
        List<Object> list = new ArrayList();
        for (User li:userList) {
            Map map = new HashMap();
            li.setNick(URLDecoder.decode(li.getNick()));
            map.put("id",li.getId());
            map.put("nick",li.getNick());
            Integer number=creditsUpdateRecordsMapper.getCount(li.getId());
            map.put("credits",number);
            list.add(map);
        }
        return ServerResponse.createBySuccess(list);
    }

    /**
     * 查询所有用户信息
     * @return
     */
    public ServerResponse selectByPage(Integer page,Integer limit){
        List<User> userList = mapper.selectByPage(page,limit);
        List list = new ArrayList();
        for (User li:userList) {
            Map map = new HashMap();
            li.setNick(URLDecoder.decode(li.getNick()));
            map.put("id",li.getId());
            map.put("nick",li.getNick());
            Integer number=creditsUpdateRecordsMapper.getCount(li.getId());
            map.put("credits",number);
            list.add(map);
        }
        Map map = new HashMap();
        map.put("list",list);
        map.put("number",mapper.allNumber());
        return ServerResponse.createBySuccess(map);
    }

    /**
     * 微信授权注册用户
     *
     * @param user
     * @return
     */
    public ServerResponse addUser(User user) {
        ServerResponse serverResponse = null;

        int i = mapper.insert(user);
        if (i != 1) {
            serverResponse = ServerResponse.createByErrorMessage("注册失败");
        }
        serverResponse = ServerResponse.createBySuccess("注册成功", user);
        return serverResponse;
    }

    /**
     * 微信授权判断用户是否存在
     *
     * @param user
     * @return
     */
    public User isExist(User user) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("open_id", user.getOpen_id());
        List<User> list = mapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 分页获取用户收藏
     *
     * @param user
     * @return
     */
    @Autowired
    private CollectionMapper collectionMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    public ServerResponse getCollectionsByUserId(String userId, Integer currPage, Integer pageSize) {
        ServerResponse serverResponse = null;
        List<Collection> list = null;

        try {
            //总页数
            int totalPage = 0;
            int totalCount = collectionMapper.allCollectionCounts(userId);
            if (totalCount == 0) {
                serverResponse = ServerResponse.createBySuccess("暂无收藏", null);
                return serverResponse;
            }
            if ((totalCount % pageSize) == 0) {
                totalPage = totalCount / pageSize;
            } else {
                totalPage = (totalCount / pageSize) + 1;
            }
            // 由于小程序scroll-view下拉请求每次都会将currPage加1，所以当当前页大于总页数时，返回null
            if (currPage > totalPage) {
                serverResponse = ServerResponse.createBySuccessMessages("页码错误");
                return serverResponse;
            }
            int startIndex = (currPage - 1) * pageSize;

            list = collectionMapper.getCollections(userId, startIndex, pageSize);
            //根据goods_id获取商品
            List<Goods> goodsList = new ArrayList<>();
            for (Collection c :
                    list) {
                Goods goods = goodsMapper.selectByPrimaryKey(c.getGoods_id());
                goodsList.add(goods);
            }


            serverResponse = ServerResponse.createBySuccess("分页获取用户收藏列表成功", goodsList);
        } catch (Exception e) {
            e.printStackTrace();
            serverResponse = ServerResponse.createByErrorMessage("分页获取用户收藏列表失败");
        } finally {
            return serverResponse;
        }
    }

    /**
     * 用户添加收藏
     *
     * @param goods_name
     * @param goodsId
     * @param userId
     * @return
     */
    public ServerResponse addCollection(String goods_name, String goodsId, String userId) {
        ServerResponse serverResponse = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Collection collection = Collection.builder()
                    .id(UUIDUtils.generateId())
                    .goods_name(goods_name)
                    .goods_id(goodsId)
                    .user_id(userId)
                    .collect_time(sdf.format(new Date()))
                    .build();
            int i = collectionMapper.insert(collection);
            if (i == 1) {
                serverResponse = ServerResponse.createBySuccess("添加收藏成功", collection);
                log.info("用户：{}添加收藏成功", userId);
            } else {
                serverResponse = ServerResponse.createBySuccess("添加收藏失败");
                log.info("用户：{}添加收藏失败", userId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("用户：{}添加收藏异常，错误信息：", userId, e.getLocalizedMessage());
            serverResponse = ServerResponse.createByErrorMessage("添加收藏异常");
        } finally {
            return serverResponse;
        }
    }

    public ServerResponse delCollection(String id, String userId) {
        ServerResponse serverResponse = null;

        try {
            int i = collectionMapper.deleteCollection(id, userId);
            if (i > 0) {
                serverResponse = ServerResponse.createBySuccess("删除收藏成功");
                log.info("用户：{}删除收藏成功", userId);
            } else {
                serverResponse = ServerResponse.createBySuccess("删除收藏失败");
                log.info("用户：{}删除收藏失败", userId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("用户：{}删除收藏异常，错误信息：", userId, e.getLocalizedMessage());
            serverResponse = ServerResponse.createByErrorMessage("删除收藏异常");
        } finally {
            return serverResponse;
        }
    }

    public ServerResponse isCollected(String goodId, String userId) {
        ServerResponse serverResponse = null;

        try {
            int i = collectionMapper.isCollected(goodId, userId);
            if (i > 0) {
                serverResponse = ServerResponse.createBySuccess("1");
                log.info("用户：{}查询商品{}是否已收藏成功", userId, goodId);
            } else {
                serverResponse = ServerResponse.createBySuccess("0");
                log.info("用户：{}查询商品{}是否已收藏成功", userId, goodId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("用户：{}查询商品{}是否已收藏异常，错误信息：{}", userId, goodId, e.getLocalizedMessage());
            serverResponse = ServerResponse.createByErrorMessage("查询异常");
        } finally {
            return serverResponse;
        }
    }

    public User selectById(String id){

        return mapper.selectById(id);
    }
}
