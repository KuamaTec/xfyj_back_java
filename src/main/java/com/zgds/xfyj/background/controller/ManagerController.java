package com.zgds.xfyj.background.controller;

import com.zgds.xfyj.background.dao.ManagerMapper;
import com.zgds.xfyj.util.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Xiang-ping li
 * @descition 后台用户（商家用户）controller
 * @date 2020/1/3 0003  17:03
 */
@Controller
@RequestMapping("/back/manager")
@Slf4j
public class ManagerController {

    @Autowired
    private ManagerMapper managerMapper;

    @RequestMapping("/login/{username}/{password}")
    public ServerResponse login(@PathVariable(name = "username") String username,
                                @PathVariable(name = "password") String password) {
        ServerResponse serverResponse = null;

//        try {
//            if(ListUtils.isEmpty(list)){
//                serverResponse = ServerResponse.createBySuccessMessages("用户不存在！");
//                log.info("用户不存在");
//            }
//            List<Manager> list = managerMapper.selectByMobilePwd(username, password);
//            else if(ListUtils.isEmpty(list)){
//                serverResponse = ServerResponse.createBySuccess("登陆成功",list.get(0));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            return serverResponse;
//        }
        return null;
    }

}
