package com.zgds.xfyj.wechat;

import com.alibaba.fastjson.JSONObject;
import com.zgds.xfyj.config.WxPayConfig;
import com.zgds.xfyj.dao.UserMapper;
import com.zgds.xfyj.domain.pojo.User;
import com.zgds.xfyj.service.UserService;
import com.zgds.xfyj.util.AesCbcUtil;
import com.zgds.xfyj.util.HttpRequest;
import com.zgds.xfyj.util.ServerResponse;
import com.zgds.xfyj.util.UUIDUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @descition
 * @date 2019/11/18 0018  18:13
 */
@Controller
@RequestMapping("/wx/user")
@Api(description = "微信接口")
@Slf4j
public class WechatUserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper mapper;
    @Autowired
    private WxPayConfig wxPayConfig;

    @RequestMapping("/userinfo/{id}")
    @ResponseBody
    @ApiOperation(value = "", httpMethod = "GET")
    public ServerResponse getUserInfo(@PathVariable String id) {
        ServerResponse serverResponse = null;
        try {
            User user = mapper.selectByPrimaryKey(id);
            user.setNick(URLDecoder.decode(user.getNick()));
            log.info("{}获取用户信息成功", id);
            serverResponse = ServerResponse.createBySuccess(user);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("{}获取用户信息异常", id);
            serverResponse = ServerResponse.createByErrorMessage("获取用户信息异常");
        } finally {
            return serverResponse;
        }
    }


    /**
     * 微信授权注册
     *
     * @param encryptedData
     * @param iv
     * @param code
     * @param headphoto
     * @return
     */
    @ResponseBody
    @RequestMapping("/wxregister")
    @ApiOperation(value = "", httpMethod = "GET")
    public ServerResponse wx_register(@RequestParam String encryptedData,
                                      @RequestParam String iv,
                                      @RequestParam String code,
                                      @RequestParam String headphoto) {
        ServerResponse serverResponse = null;
        User user = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
        String regist_time = sdf.format(new Date());
        try {
            Map map = decodeUserInfo(encryptedData, iv, code);
            Map userInfo = (Map) map.get("userInfo");
            String openId = (String) userInfo.get("openId");
            String uninId = (String) userInfo.get("uninId");
            String nick = (String) userInfo.get("nickName");
            Integer gender = (Integer) userInfo.get("gender");
            user = User.builder()
                    .id(UUIDUtils.generateId())
                    .nick(URLEncoder.encode(nick, "utf-8"))
                    .head_photo(headphoto)
                    .open_id(openId)
                    .rigist_time(regist_time)
                    .build();

            //根据openid判断用户是不是已注册，防止重复注册
            User existUser = userService.isExist(user);
            if (existUser != null) {
                log.info("{}授权成功！", URLEncoder.encode(user.getNick(), "utf-8"));
                serverResponse = ServerResponse.createBySuccess("授权成功", existUser);
                return serverResponse;
            }

            //插入数据库
            serverResponse = userService.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                log.info("{}注册失败！" + e.getLocalizedMessage(), URLEncoder.encode(user.getNick(), "utf-8"));
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
                return ServerResponse.createByErrorMessage("授权异常");
            }
        } finally {
            try {
                log.info("{}注册成功！", URLEncoder.encode(user.getNick(), "utf-8"));
                return serverResponse;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return ServerResponse.createByErrorMessage("授权异常");
            }
        }
    }


    @ResponseBody
    @RequestMapping(value = "/decodeUserInfo", method = RequestMethod.POST)

    @ApiOperation(value = "", httpMethod = "POST")
    public Map decodeUserInfo(String encryptedData,
                              String iv,
                              String code) {
        Map map = new HashMap();
        //登录凭证不能为空
        if (code == null || code.length() == 0) {
            map.put("status", 0);
            map.put("msg", "code 不能为空");
            return map;
        }
        //小程序唯一标识   (在微信小程序管理后台获取)
        String wxspAppid = wxPayConfig.getAppid();
        //小程序的 app secret (在微信小程序管理后台获取)
        String wxspSecret = wxPayConfig.getAppsercret();
        //授权（必填）
        String grant_type = "authorization_code";


        //////////////// 1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid ////////////////
        //请求参数
        String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + code + "&grant_type=" + grant_type;
        //发送请求
        String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
        //解析相应内容（转换成json对象）
        JSONObject json = JSONObject.parseObject(sr);
        //获取会话密钥（session_key）
        String session_key = json.get("session_key").toString();
        //用户的唯一标识（openid）
        String openid = (String) json.get("openid");

        //////////////// 2、对encryptedData加密数据进行AES解密 ////////////////
        try {
            String result = AesCbcUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
            if (null != result && result.length() > 0) {
                map.put("status", 1);
                map.put("msg", "解密成功");

                JSONObject userInfoJSON = JSONObject.parseObject(result);
                Map userInfo = new HashMap();
                userInfo.put("openId", userInfoJSON.get("openId"));
                userInfo.put("nickName", userInfoJSON.get("nickName"));
                userInfo.put("gender", userInfoJSON.get("gender"));
                userInfo.put("city", userInfoJSON.get("city"));
                userInfo.put("province", userInfoJSON.get("province"));
                userInfo.put("country", userInfoJSON.get("country"));
                userInfo.put("avatarUrl", userInfoJSON.get("avatarUrl"));
                userInfo.put("unionId", userInfoJSON.get("unionId"));
                System.out.println(userInfo.toString());
                map.put("userInfo", userInfo);
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", 0);
            map.put("msg", "解密失败");
            return map;
        }
        map.put("status", 0);
        map.put("msg", "解密失败");
        return map;
    }

}