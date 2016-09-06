package com.HG.test.test.dao;

import com.HG.test.service.attend.AttendService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

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
        Long[] time = attendService.QueryDuration("Huajie", new Date(116, 8, 4, 0, 0, 0), new Date(116, 8, 5, 0, 0, 0));
        System.out.println(time.length);
        for(int i=0;i<time.length;i++)
            System.out.println(time[i]);
    }
}
