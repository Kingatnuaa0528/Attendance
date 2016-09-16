package com.HG.test.service.login;

import com.HG.test.dao.login.LoginDAO;
import com.HG.test.pojo.LoginDO;

import javax.annotation.Resource;

/**
 * Created by HuaJieJie on 2016/9/5.
 */
public class LoginServiceImpl implements LoginService{

    @Resource
    private LoginDAO loginDAO;

    private static final int MIN_HOST_NO = 0;
    private static final int MAX_HOST_NO = 254;
    private static final String NETWORK_NO = "127.0.0";

    public boolean CheckLogin(String username, String password, String IP) {

        int host_NO = Integer.parseInt(IP.substring(IP.lastIndexOf(".")+1));

        String network_NO = IP.substring(0, IP.lastIndexOf("."));
        if(network_NO.equals(NETWORK_NO) && (host_NO>=MIN_HOST_NO && host_NO <= MAX_HOST_NO))
        {
            if(loginDAO == null)
                System.out.println("aaaaaaaaaaaa");
            LoginDO loginDO = loginDAO.select_user(username);
            if(loginDO != null)
                return (loginDO.getPassword().equals(password));
            else return false;
        }
        else return false;
    }

    public boolean ResetPassword(String username, String old_password, String new_password){
        return (loginDAO.update_user(username, old_password,new_password));
    }

    public boolean InsertNewUser(String username, String password){
        LoginDO loginDo = new LoginDO();
        loginDo.setUsername(username);
        loginDo.setPassword(password);
        return(loginDAO.insert_user(loginDo));
    }
}
