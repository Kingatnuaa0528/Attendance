package com.HG.test.dao.login;

import com.HG.test.pojo.LoginDO;

/**
 * Created by gaohanqing on 16/8/30.
 */
public interface LoginDAO {

    public boolean insert_user(LoginDO loginDO);

    public LoginDO select_user(String username);

    public boolean delete_user(String username);

    public boolean update_user(String username, String old_password, String new_password);
}
