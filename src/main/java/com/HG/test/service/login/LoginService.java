package com.HG.test.service.login;

/**
 * Created by gaohanqing on 16/8/31.
 */
public interface LoginService {

    /*
     * @func : 检查登陆操作的合法性（IP，用户名，密码） 要求IP必须为实验室内部ip（可以限定在一个范围内即可）
     */
    public boolean CheckLogin(String username, String password, String IP);

    public boolean ResetPassword(String username, String old_password, String new_password);

    public boolean InsertNewUser(String username, String password);
}
