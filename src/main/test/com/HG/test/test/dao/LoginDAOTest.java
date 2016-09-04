package com.HG.test.test.dao;

import com.HG.test.dao.login.LoginDAO;
import com.HG.test.pojo.LoginDO;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by gaohanqing on 16/9/1.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:dao.xml")
public class LoginDAOTest {
    @Resource
    private LoginDAO loginDAO;

    @Test
    public void IOCTest()
    {
        Assert.assertNotNull(loginDAO);
    }

    @Test
    public void insertTest(){
        LoginDO user = new LoginDO();
        user.setUsername("Huajie");
        user.setPassword("123456");
        loginDAO.insert_user(user);
    }

    @Test
    public void selectTest(){

        Assert.assertEquals("123456",loginDAO.select_user("Huajie").getPassword());
    }
}
