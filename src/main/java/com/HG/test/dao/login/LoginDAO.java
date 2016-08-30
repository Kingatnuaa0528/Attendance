package com.HG.test.dao.login;

import com.HG.test.pojo.LoginDO;

/**
 * Created by gaohanqing on 16/8/30.
 */
public interface LoginDAO {

    public void insert_user(LoginDO loginDO);

    public LoginDO select_user(String username);

    public void delete_user(String username);
}
