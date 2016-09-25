package com.HG.test.test.dao;

import com.HG.test.service.attend.AttendService;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * Created by HuaJieJie on 2016/9/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:dao.xml")
public class AttendServiceTest {
    @Resource
    private AttendService attendService;
    @Test
    public void QueryDurationTest(){
        Map<Date,Long> map = attendService.QueryDuration("Huajie", new Date(116, 8, 13, 0, 0, 0), new Date(116, 8, 16, 0, 0, 0));

        Date[] key = map.keySet().toArray(new Date[1]);
        for(int i = 0;i<key.length;i++)
        {
            System.out.println(key[i].toString() +":   "+ map.get(key[i]));
        }
    }

    @Test
    public void QueryComeTimeTest(){
        Map<Date,Date> come_time = attendService.QueryComeTime("Huajie", new Date(116, 8, 14, 0, 0, 0), new Date(116, 8, 15, 0, 0, 0));
        Date[] key = come_time.keySet().toArray(new Date[1]);
        for(int i=0; i < key.length; i++)
            System.out.println(come_time.get(key[i]));
    }

    @Test
    public void QueryLeaveTimeTest(){
        Map<Date,Date> come_time = attendService.QueryLeaveTime("Huajie", new Date(116, 8, 4, 0, 0, 0), new Date(116, 8, 7, 0, 0, 0));
        /*System.out.println(come_time.length);
        for(int i=0;i<come_time.length;i++)
            System.out.println(come_time[i]);*/
    }


    @Test
     public void QueryAllLeaveTimeTest(){
        Map<Date, Map<String, Date>> map = attendService.QueryAllLeaveTime(new Date(116, 8, 14, 0, 0, 0), new Date(116, 8, 16, 0, 0, 0));
        Date[] key = map.keySet().toArray(new Date[1]);
        for(int i = 0; i < key.length; i++)
        {
            Map<String, Date> value = map.get(key[i]);
            String[] user_keySet = value.keySet().toArray(new String[1]);
            for(int j = 0; j < user_keySet.length; j++)
            {
                System.out.println(key[i] +":   "+ user_keySet[j] + "  " + value.get(user_keySet[j]));
            }
        }
    }

    @Test
    public void QueryAllComeTimeTest(){
        Map<Date, Map<String, Date>> map = attendService.QueryAllComeTime(new Date(116, 8, 13, 0, 0, 0), new Date(116, 8, 17, 0, 0, 0));
        Date[] key = map.keySet().toArray(new Date[1]);
        System.out.println("length :  " + key.length);
        for(int i = 0; i < key.length; i++)
        {
            Map<String, Date> value = map.get(key[i]);
            String[] user_keySet = value.keySet().toArray(new String[1]);
            for(int j = 0; j < user_keySet.length; j++)
            {
                System.out.println(key[i] +":   "+ user_keySet[j] + "  " + value.get(user_keySet[j]));
            }
        }
    }

    @Test
    public void QueryAllDurationTest(){
        Map<Date, Map<String, Long>> map = attendService.QueryAllDuration(new Date(116, 8, 14, 0, 0, 0), new Date(116, 8, 16, 0, 0, 0));
        Date[] key = map.keySet().toArray(new Date[1]);
        for(int i = 0; i < key.length; i++)
        {
            Map<String, Long> value = map.get(key[i]);
            String[] user_keySet = value.keySet().toArray(new String[1]);
            for(int j = 0; j < user_keySet.length; j++)
                System.out.println(key[i] +":   "+ user_keySet[j] + "  " + value.get(user_keySet[j]));
        }
        //System.out.println(JSONObject.toJSONString(map));
    }
}
