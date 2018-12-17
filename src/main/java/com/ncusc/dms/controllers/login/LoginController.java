package com.ncusc.dms.controllers.login;

import com.ncusc.dms.pojo.UserAccount;
import com.ncusc.dms.service.user.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登陆
 * @author WANGHAO && WangYiqing
 */
@Controller
public class LoginController {
    @Autowired
    UserAccountService userAccountservice;

    /**
     * 注册
     * @param id
     * @param name
     * @param password
     * @param model
     * @return
     */
    @RequestMapping("/register")
    public String Login(@RequestParam("id") String id,@RequestParam("name") String name,@RequestParam("password") String password,Model model)
    {
        if(userAccountservice.addUser( id, name, password))
            model.addAttribute("info","注册成功");
        else
            model.addAttribute("info","用户已存在,注册失败");
        return "login/register";
    }

    /**
     * 登录
     * @param id
     * @param password
     * @param model
     * @return
     */
    @RequestMapping("/login")
    public String Login(@RequestParam("id") String id,@RequestParam("password") String password,Model model)
    {

        return "login/login";
    }
    @RequestMapping("/loginError")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login/login";
    }

}
