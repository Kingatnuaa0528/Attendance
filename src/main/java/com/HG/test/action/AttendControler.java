package com.HG.test.action;

import com.HG.test.service.attend.AttendService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by HuaJieJie on 2016/9/10.
 */

@Controller
public class AttendControler {
    @Resource
    private AttendService attendService;

    @RequestMapping(value = "/submitattend", method = RequestMethod.POST)
    public ModelAndView SubmitAttend(HttpServletRequest request,HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        String username = (String)request.getAttribute("username");
        Date dateTime = new Date(System.currentTimeMillis());
        int type = (Integer)request.getAttribute("type");
        if(attendService.SubmitAttend(username, dateTime, type)){
            if(type == 1) {
                mv.addObject("message","Singn in success!!! Welcome to work!!");
            }
            else{
                mv.addObject("message","Singn in success!!! See you next time!!");
            }
        }
        else{
            mv.addObject("message","Singn in failed!!!");
        }
        mv.setViewName("information");
        return mv;
    }


}
