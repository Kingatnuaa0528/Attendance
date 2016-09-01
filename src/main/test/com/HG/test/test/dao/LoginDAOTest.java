package com.HG.test.test.dao;

import com.HG.test.dao.login.LoginDAO;
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
}
