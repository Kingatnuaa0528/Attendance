package com.HG.test.action;

import com.HG.test.service.login.LoginService;
import org.springframework.stereotype.Controller;
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
    public String Login(HttpServletRequest request, HttpServletResponse response){

        ModelAndView mv = new ModelAndView();
        String username = (String)request.getAttribute("username");
        String password = (String)request.getAttribute("password");
        String IP = request.getRemoteAddr();
        if(loginService.CheckLogin(username, password, IP)){
            mv.addObject("message",username);
            return "functionseletion";
        }
        else{
            mv.addObject("message","Login Error!!!");
            return "information";
        }
    }
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(HttpServletRequest request, HttpServletResponse response){

        ModelAndView mv = new ModelAndView();
        String username = (String)request.getAttribute("username");
        String password = (String)request.getAttribute("password");
        if(loginService.InsertNewUser(username, password)){
            mv.addObject("message","Hello"+username);
            return "functionseletion";
        }
        else{
            mv.addObject("message","Register Error!!!");
           return "information";
        }
    }

    @RequestMapping(value = "/resetPassword",method = RequestMethod.POST)
    public String resetPassword(HttpServletRequest request, HttpServletResponse response){

        ModelAndView mv = new ModelAndView();
        String username = (String)request.getAttribute("username");
        String old_password = (String)request.getAttribute("old_password");
        String new_password = (String)request.getAttribute("new_password");
        if(loginService.ResetPassword(username, old_password, new_password)){
            mv.addObject("message","Reset Success!!");
        }
        else{
            mv.addObject("message","Register Error!!!");
        }
        return "information";
    }

}
