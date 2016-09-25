package com.HG.test.action;

import com.HG.test.service.login.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by HuaJieJie on 2016/9/8.
 */

@Controller
public class LoginControler {

    @Resource
    private LoginService loginService;
    //登陆操作
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String Login(HttpServletRequest request, ModelMap map){

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String IP = request.getRemoteAddr();

        if(loginService.CheckLogin(username, password, IP)){
            map.put("username",username);
            return "functionselection";
        }
        else{
            map.put("message","Login Error!!!");
            return "information";
        }
    }
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(HttpServletRequest request, ModelMap map){

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(loginService.InsertNewUser(username, password)){
            map.put("username",username);
            return "functionselection";
        }
        else{
            map.put("message", "Register Error!!!");
           return "information";
        }
    }

    @RequestMapping(value = "/resetPassword",method = RequestMethod.POST)
    public String resetPassword(HttpServletRequest request, ModelMap map){

        String username = request.getParameter("username");
        String old_password = request.getParameter("old_password");
        String new_password = request.getParameter("new_password");
        if(loginService.ResetPassword(username, old_password, new_password)){
            map.put("message","Reset Success!!");
        }
        else{
            map.put("message","Register Error!!!");
        }
        return "information";
    }

}
