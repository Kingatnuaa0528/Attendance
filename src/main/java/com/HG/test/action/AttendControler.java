package com.HG.test.action;

import com.HG.test.pojo.ResultType;
import com.HG.test.service.attend.AttendService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * Created by HuaJieJie on 2016/9/10.
 */

@Controller
public class AttendControler {
    @Resource
    private AttendService attendService;

    @RequestMapping(value = "/submitattend", method = RequestMethod.POST)
    public String SubmitAttend(HttpServletRequest request,HttpServletResponse response) {
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
        return "information";
    }

    @RequestMapping(value = "/QueryDuration", method = RequestMethod.POST)
    public String QueryDuration(HttpServletRequest request,HttpServletResponse response){
        ModelAndView mv = new ModelAndView();
        String username = (String)request.getAttribute("username");
        Date startTime = (Date)request.getAttribute("startTime");
        Date endTime = (Date)request.getAttribute("endTime");
        Map<Date,Long> result = attendService.QueryDuration(username, startTime,endTime);
        mv.addObject("type", ResultType.DURATION);
        mv.addObject("username", username);
        mv.addObject("resultset",result);
        return "result";
    }

    @RequestMapping(value = "/QueryComeTime", method = RequestMethod.POST)
    public String QueryComeTime(HttpServletRequest request,HttpServletResponse response){
        ModelAndView mv = new ModelAndView();
        String username = (String)request.getAttribute("username");
        Date startTime = (Date)request.getAttribute("startTime");
        Date endTime = (Date)request.getAttribute("endTime");
        Map<Date,Date> result = attendService.QueryComeTime(username, startTime,endTime);
        mv.addObject("type", ResultType.COMETIME);
        mv.addObject("username", username);
        mv.addObject("resultset",result);
        return "result";
    }

    @RequestMapping(value = "/QueryLeaveTime", method = RequestMethod.POST)
    public String QueryLeaveTime(HttpServletRequest request,HttpServletResponse response){
        ModelAndView mv = new ModelAndView();
        String username = (String)request.getAttribute("username");
        Date startTime = (Date)request.getAttribute("startTime");
        Date endTime = (Date)request.getAttribute("endTime");
        Map<Date,Date> result = attendService.QueryLeaveTime(username, startTime, endTime);
        mv.addObject("type", ResultType.LEAVETIME);
        mv.addObject("username", username);
        mv.addObject("resultset",result);
        return "result";
    }

    @RequestMapping(value = "/QueryAllDuration", method = RequestMethod.POST)
    public String QueryAllDuration(HttpServletRequest request,HttpServletResponse response){
        ModelAndView mv = new ModelAndView();
        Date startTime = (Date)request.getAttribute("startTime");
        Date endTime = (Date)request.getAttribute("endTime");
        Map<String,Map<Date, Long>> result = attendService.QueryAllDuration(startTime,endTime);
        mv.addObject("type", ResultType.ALLDURATION);
        mv.addObject("resultset",result);
        return "multi_result";
    }

    @RequestMapping(value = "/QueryAllComeTime", method = RequestMethod.POST)
    public String QueryAllComeTime(HttpServletRequest request,HttpServletResponse response){
        ModelAndView mv = new ModelAndView();
        Date startTime = (Date)request.getAttribute("startTime");
        Date endTime = (Date)request.getAttribute("endTime");
        Map<String,Map<Date,Date>> result = attendService.QueryAllComeTime(startTime, endTime);
        mv.addObject("resultset",result);
        return "multi_result";
    }

    @RequestMapping(value = "/QueryAllLeaveTime", method = RequestMethod.POST)
    public String QueryAllLeaveTime(HttpServletRequest request,HttpServletResponse response){
        ModelAndView mv = new ModelAndView();
        Date startTime = (Date)request.getAttribute("startTime");
        Date endTime = (Date)request.getAttribute("endTime");

        Map<String,Map<Date,Date>> result = attendService.QueryAllLeaveTime(startTime,endTime);
        mv.addObject("resultset",result);
        return "multi_result";
    }

}
