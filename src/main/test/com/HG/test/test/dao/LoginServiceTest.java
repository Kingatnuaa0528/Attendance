package com.HG.test.test.dao;

import com.HG.test.service.login.LoginService;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by HuaJieJie on 2016/9/5.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:dao.xml")
public class LoginServiceTest {

    @Resource
    LoginService loginService;

    @Test
    public void CheckLoginTest(){

        boolean flag = loginService.CheckLogin("Huajie", "123456", "192.168.0.2");
        Assert.assertEquals(true,flag);
        flag = loginService.CheckLogin("Huajie", "123456", "192.138.0.2");
        Assert.assertEquals(false,flag);
        flag = loginService.CheckLogin("Huajie", "123456", "192.168.0.435");
        Assert.assertEquals(false,flag);

    }


}
