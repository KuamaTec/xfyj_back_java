package com.zgds.xfyj.background.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Xiang-ping li
 * @descition
 * @date 2020/1/3 0003  17:03
 */
@Controller
@RequestMapping("/back")
public class IndexController {

    @RequestMapping("/index")
    public String welcome() {
        return "index";
    }
}
