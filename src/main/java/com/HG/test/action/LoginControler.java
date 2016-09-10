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
    public ModelAndView Login(HttpServletRequest request, HttpServletResponse response){

        ModelAndView mv = new ModelAndView();
        String username = (String)request.getAttribute("username");
        String password = (String)request.getAttribute("password");
        String IP = request.getRemoteAddr();
        if(loginService.CheckLogin(username, password, IP)){
            mv.addObject("message",username);
            mv.setViewName("functionseletion");
        }
        else{
            mv.addObject("message","Login Error!!!");
            mv.setViewName("information");
        }
        return mv;
    }
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ModelAndView register(HttpServletRequest request, HttpServletResponse response){

        ModelAndView mv = new ModelAndView();
        String username = (String)request.getAttribute("username");
        String password = (String)request.getAttribute("password");
        if(loginService.InsertNewUser(username, password)){
            mv.addObject("message","Hello"+username);
            mv.setViewName("functionseletion");
        }
        else{
            mv.addObject("message","Register Error!!!");
            mv.setViewName("information");
        }
        return mv;
    }

    @RequestMapping(value = "/resetPassword",method = RequestMethod.POST)
    public ModelAndView resetPassword(HttpServletRequest request, HttpServletResponse response){

        ModelAndView mv = new ModelAndView();
        String username = (String)request.getAttribute("username");
        String old_password = (String)request.getAttribute("old_password");
        String new_password = (String)request.getAttribute("new_password");
        if(loginService.ResetPassword(username, old_password, new_password)){
            mv.addObject("message","Reset Success!!");
            mv.setViewName("information");
        }
        else{
            mv.addObject("message","Register Error!!!");
            mv.setViewName("information");
        }
        return mv;
    }

}
