package com.HG.test.dao.login.impl;

import com.HG.test.dao.login.LoginDAO;
import com.HG.test.pojo.LoginDO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by gaohanqing on 16/9/1.
 */
@Component
public class LoginDAOImpl implements LoginDAO {

    @Resource
    private DataSource datasource;

    private Connection conn;

    private  void init() {
        try {
            conn = datasource.getConnection();
        }catch(SQLException e)
        {
            System.out.println("init ERROR!!!!!");
            e.printStackTrace();
        }
    }

    @Override
    public boolean insert_user(LoginDO loginDO) {
        PreparedStatement ps = null;

        try{
            System.out.println("aaaaaaaaaaa");
            init();
            ps = conn.prepareStatement("INSERT INTO userinf VALUES(?,?);");
            ps.setString(1, loginDO.getUsername());
            ps.setString(2, loginDO.getPassword());
            ps.executeUpdate();
            return  true;
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            try{
                if(ps != null) ps.close();
                if(conn != null) conn.close();
            }catch (SQLException e)
            {
                e.printStackTrace();
            }

        }
        return false;

    }

    @Override
    public LoginDO select_user(String username) {
        PreparedStatement ps = null;
        LoginDO logindo = null;

        try{
            init();
            ps = conn.prepareStatement("SELECT * FROM userinf WHERE username = ?;");
            ps.setString(1, username);
            ResultSet res = ps.executeQuery();
            res.next();
            //System.out.println(res.);
            logindo = new LoginDO();
            logindo.setUsername(res.getString(1));
            logindo.setPassword(res.getString(2));


        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            try{
                if(ps != null) ps.close();
                if(conn != null) conn.close();
            }catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return logindo;
    }

    @Override
    public boolean delete_user(String username) {
        PreparedStatement ps = null;

        try{
            init();
            ps = conn.prepareStatement("DELETE FROM userinf WHERE username = ?;");
            ps.setString(1, username);
            ps.executeUpdate();
            return true;
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            try{
                if(ps != null) ps.close();
                if(conn != null) conn.close();
            }catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean update_user(String username, String old_password, String new_password) {
        PreparedStatement re = null;
        PreparedStatement wr = null;

        try{
            init();
            re = conn.prepareStatement("SELECT password FROM userinf WHERE  username = ?");
            re.setString(1,username);
            ResultSet res = re.executeQuery();
            res.next();
            if(res.getString(1).equals(old_password)) {
                wr = conn.prepareStatement("UPDATE userinf SET password=? WHERE username = ?;");
                wr.setString(1, new_password);
                wr.setString(2,username);
                wr.executeUpdate();
                return true;
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            try{
                if(re != null) re.close();
                if(wr != null) wr.close();
                if(conn != null) conn.close();
            }catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return false;
    }
}
