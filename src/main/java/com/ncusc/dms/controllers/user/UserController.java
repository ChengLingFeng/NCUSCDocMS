package com.ncusc.dms.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 用户详情映射
 * @author WANGHAO
 * @version 1.0.0
 */
@Controller
@RequestMapping("/user")
public class UserController {
    String rootPath = "user/";
    @RequestMapping("/")
    public String index(ModelAndView mav){
        return "user/index";
    }
}
