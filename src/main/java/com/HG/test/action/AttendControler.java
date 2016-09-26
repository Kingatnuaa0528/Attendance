package com.HG.test.action;

import com.HG.test.pojo.ResultType;
import com.HG.test.service.attend.AttendService;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by HuaJieJie on 2016/9/10.
 */

@Controller
public class AttendControler {
    @Resource
    private AttendService attendService;

    @RequestMapping(value = "/submitattend", method = RequestMethod.POST)
    public String SubmitAttend(HttpServletRequest request, ModelMap map) {
        String username = request.getParameter("username");
        Date dateTime = new Date(System.currentTimeMillis());
        int type = Integer.parseInt(request.getParameter("type"));
        if(attendService.SubmitAttend(username, dateTime, type)){
            if(type == 1) {
                map.put("message","Sign in success!!! Welcome to work!!");
            }
            else{
                map.put("message","Sign in success!!! See you next time!!");
            }
        }
        else{
            map.put("message","Sign in failed!!!");
        }
        return "information";
    }

    @RequestMapping(value = "/QueryDuration", method = RequestMethod.POST)
    public String QueryDuration(HttpServletRequest request, ModelMap map) throws ParseException{
        String username = request.getParameter("username");
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date startTime = sim.parse(request.getParameter("startTime"));
        Date endTime = sim.parse(request.getParameter("endTime"));
        Map<Date,Long> result = attendService.QueryDuration(username, startTime,endTime);
        map.put("type", ResultType.DURATION);
        map.put("username", username);
        map.put("resultset",result);
        return "/result/single_user_result";
    }

    @RequestMapping(value = "/QueryComeTime", method = RequestMethod.POST)
    public String QueryComeTime(HttpServletRequest request, ModelMap map) throws ParseException{
        String username = request.getParameter("username");
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date startTime = sim.parse(request.getParameter("startTime"));
        Date endTime = sim.parse(request.getParameter("endTime"));
        Map<Date,Date> result = attendService.QueryComeTime(username, startTime,endTime);
        map.put("type", ResultType.COMETIME);
        map.put("username", username);
        map.put("resultset", result);
        return "/result/single_user_result";
    }

    @RequestMapping(value = "/QueryLeaveTime", method = RequestMethod.POST)
    public String QueryLeaveTime(HttpServletRequest request, ModelMap map) throws ParseException{
        String username = request.getParameter("username");
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date startTime = sim.parse(request.getParameter("startTime"));
        Date endTime = sim.parse(request.getParameter("endTime"));
        Map<Date,Date> result = attendService.QueryLeaveTime(username, startTime, endTime);
        map.put("type", ResultType.LEAVETIME);
        map.put("username", username);
        map.put("result_set", result);
        return "/result/single_user_result";
    }

    @RequestMapping(value = "/QueryAllDuration", method = RequestMethod.POST)
    public String QueryAllDuration(HttpServletRequest request, ModelMap map) throws ParseException{
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
        Date startTime = sim.parse(request.getParameter("startTime"));
        Date endTime = sim.parse(request.getParameter("endTime"));

        Map<Date, Map<String, Long>> result = attendService.QueryAllDuration(startTime,endTime);
        List<Map.Entry<Date, Map<String, Long>>> list = new ArrayList<Map.Entry<Date, Map<String, Long>>>(result.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Date, Map<String, Long>>>() {
            //降序排序
            @Override
            public int compare(Map.Entry<Date, Map<String, Long>> o1, Map.Entry<Date, Map<String, Long>> o2) {
                //return o1.getValue().compareTo(o2.getValue());
                return o1.getKey().compareTo(o2.getKey());
            }
        });

        //System.out.println(JSONObject.toJSONString(list));
        map.put("type", ResultType.ALLDURATION);
        map.put("result_set",JSONObject.toJSONString(list));
        return "/result/multi_user_result_long";
    }

    @RequestMapping(value = "/QueryAllComeTime", method = RequestMethod.POST)
    public String QueryAllComeTime(HttpServletRequest request, ModelMap map) throws ParseException{
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
        Date startTime = sim.parse(request.getParameter("startTime"));
        Date endTime = sim.parse(request.getParameter("endTime"));
        //System.out.println(startTime.getTime() + "   aaaa  " + endTime.getTime());

        Map<Date, Map<String, Date>> result = attendService.QueryAllComeTime(startTime, endTime);
        List<Map.Entry<Date, Map<String, Date>>> list = new ArrayList<Map.Entry<Date, Map<String, Date>>>(result.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Date, Map<String, Date>>>() {
            //降序排序
            @Override
            public int compare(Map.Entry<Date, Map<String, Date>> o1, Map.Entry<Date, Map<String, Date>> o2) {
                //return o1.getValue().compareTo(o2.getValue());
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        System.out.println(JSONObject.toJSONString(list));
        map.put("type", ResultType.ALLCOMTIME);
        map.put("result_set",JSONObject.toJSONString(list, SerializerFeature.WriteMapNullValue));
        return "/result/multi_user_result_date";
    }

    @RequestMapping(value = "/QueryAllLeaveTime", method = RequestMethod.POST)
    public String QueryAllLeaveTime(HttpServletRequest request, ModelMap map) throws ParseException{
        SimpleDateFormat sim=new SimpleDateFormat("YYYY-MM-dd");
        Date startTime = sim.parse(request.getParameter("startTime"));
        Date endTime = sim.parse(request.getParameter("endTime"));

        Map<Date, Map<String, Date>> result = attendService.QueryAllLeaveTime(startTime,endTime);
        List<Map.Entry<Date, Map<String, Date>>> list = new ArrayList<Map.Entry<Date, Map<String, Date>>>(result.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Date, Map<String, Date>>>() {
            //降序排序
            @Override
            public int compare(Map.Entry<Date, Map<String, Date>> o1, Map.Entry<Date, Map<String, Date>> o2) {
                //return o1.getValue().compareTo(o2.getValue());
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        map.put("type", ResultType.ALLLEAVETIME);
        map.put("result_set",JSONObject.toJSONString(list, SerializerFeature.WriteMapNullValue));
        return "/result/multi_user_result_date";
    }

}
