package com.HG.test.action;

import com.HG.test.pojo.FrontData;
import com.HG.test.pojo.LoginDO;
import com.HG.test.pojo.ResultType;
import com.HG.test.service.attend.AttendService;
import com.HG.test.tool.Tools;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by HuaJieJie on 2016/9/10.
 */

@Controller
public class AttendController {
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
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
        Date startTime = sim.parse(request.getParameter("startTime"));
        Date endTime = sim.parse(request.getParameter("endTime"));
        //System.out.println("name :  " + username + " startTime :  " + startTime + " endTime :  " + endTime);
        Map<Date,Long> result = attendService.QueryDuration(username, startTime,endTime);
        map.put("type", ResultType.DURATION);
        map.put("username", username);
        System.out.println(JSONObject.toJSONString(result));
        map.put("result_set", JSONObject.toJSONString(result));
        return "/result/single_user_result_long";
    }

    @RequestMapping(value = "/QueryComeTime", method = RequestMethod.POST)
    public String QueryComeTime(HttpServletRequest request, ModelMap map) throws ParseException{
        String username = request.getParameter("username");
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
        Date startTime = sim.parse(request.getParameter("startTime"));
        Date endTime = sim.parse(request.getParameter("endTime"));
        Map<Date,Date> result = attendService.QueryComeTime(username, startTime,endTime);
        map.put("type", ResultType.COMETIME);
        map.put("username", username);
        map.put("result_set", JSONObject.toJSONString(result));
        return "/result/single_user_result_date";
    }

    @RequestMapping(value = "/QueryLeaveTime", method = RequestMethod.POST)
    public String QueryLeaveTime(HttpServletRequest request, ModelMap map) throws ParseException{
        String username = request.getParameter("username");
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
        Date startTime = sim.parse(request.getParameter("startTime"));
        Date endTime = sim.parse(request.getParameter("endTime"));
        Map<Date,Date> result = attendService.QueryLeaveTime(username, startTime, endTime);
        map.put("type", ResultType.LEAVETIME);
        map.put("username", username);
        map.put("result_set", JSONObject.toJSONString(result));
        return "/result/single_user_result_date";
    }

    @RequestMapping(value = "/QueryAllDuration", method = RequestMethod.POST)
    public void QueryAllDuration(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException{
        String data = Tools.getJsonString(request);
        JSONObject jsonObject = JSONObject.parseObject(data);
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
        Date startTime = sim.parse(jsonObject.getString("startTime"));
        Date endTime = sim.parse(jsonObject.getString("endTime"));

        System.out.println("startTime :  " + startTime + "  String :  " + jsonObject.getString("startTime"));

        Map<Date, Map<String, Long>> result = attendService.QueryAllDuration(startTime,endTime);
        List<Map.Entry<Date, Map<String, Long>>> list = new ArrayList<Map.Entry<Date, Map<String, Long>>>(result.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Date, Map<String, Long>>>() {
            //降序排序
            @Override
            public int compare(Map.Entry<Date, Map<String, Long>> o1, Map.Entry<Date, Map<String, Long>> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });

        System.out.println(JSONObject.toJSONString(list));
        System.out.println(data);
        //map.put("type", ResultType.ALLDURATION);
        //map.put("result_set",JSONObject.toJSONString(list));
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();

        out.print(data);
        out.flush();
        out.close();
    }

    @RequestMapping(value = "/QueryAllComeTime", method = RequestMethod.POST)
    public String QueryAllComeTime(HttpServletRequest request, ModelMap map) throws ParseException, IOException{
        String data = Tools.getJsonString(request);
        JSONObject jsonObject = JSONObject.parseObject(data);
        Date startTime = java.sql.Date.valueOf(jsonObject.getString("startTime"));
        Date endTime = java.sql.Date.valueOf(jsonObject.getString("endTime"));
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
        return "/result/multi_user_result";
    }

    @RequestMapping(value = "/QueryAllLeaveTime", method = RequestMethod.POST)
    public String QueryAllLeaveTime(HttpServletRequest request, ModelMap map) throws ParseException, IOException{
        String data = Tools.getJsonString(request);
        JSONObject jsonObject = JSONObject.parseObject(data);
        Date startTime = java.sql.Date.valueOf(jsonObject.getString("startTime"));
        Date endTime = java.sql.Date.valueOf(jsonObject.getString("endTime"));

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

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public void test(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException
    {
        String data = request.getParameter("mydata");
        JSONObject object = JSON.parseObject(data);
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");

        Date startTime = Tools.DateFormat(sim.parse(object.getString("startTime")));

        Date endTime = Tools.DateFormat(sim.parse(object.getString("endTime")));

        Map<Date, Map<String, Long>> result = attendService.QueryAllDuration(startTime,endTime);
        List<Map.Entry<Date, Map<String, Long>>> list = new ArrayList<Map.Entry<Date, Map<String, Long>>>(result.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Date, Map<String, Long>>>() {
            //降序排序
            @Override
            public int compare(Map.Entry<Date, Map<String, Long>> o1, Map.Entry<Date, Map<String, Long>> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });

        System.out.println(data);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(data);
        writer.flush();
        writer.close();
    }
}
