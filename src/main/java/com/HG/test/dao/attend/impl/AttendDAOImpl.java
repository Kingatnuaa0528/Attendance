package com.HG.test.dao.attend.impl;

import com.HG.test.dao.attend.AttendDAO;
import com.HG.test.pojo.AttendDO;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by gaohanqing on 16/8/30.
 */
public class AttendDAOImpl implements AttendDAO {

    @Resource
    private DataSource datasource;
    private Connection conn;

    private  void init() {
        try {
            conn = datasource.getConnection();
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void insert_attend(AttendDO attendDO)
    {
        PreparedStatement ps = null;

        try{
            init();
            ps = conn.prepareStatement("INSERT INTO Attend VALUES(?,?,?);");
            ps.setString(1, attendDO.getUsername());
            ps.setTimestamp(2, new java.sql.Timestamp(attendDO.getAttendTime().getTime()));
            ps.setInt(3,attendDO.getType());
            ps.executeUpdate();
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


    }

    public List<AttendDO> select_byTime(Date startTime, Date endTime)
    {
        List<AttendDO> result = new ArrayList<AttendDO>();
        PreparedStatement ps = null;

        try{
            init();
            ps = conn.prepareStatement("SELECT * FROM Attend WHERE attendTime BETWEEN ? AND ?;");
            ps.setTimestamp(1,new java.sql.Timestamp(startTime.getTime()));
            ps.setTimestamp(2,new java.sql.Timestamp(endTime.getTime()));
            ResultSet res = ps.executeQuery();
            //res.next();
            while(res.next())
            {
                AttendDO attendDO = new AttendDO();
                attendDO.setUsername(res.getString(1));
                attendDO.setAttendTime(new Date(res.getTimestamp(2).getTime()));
                attendDO.setType(res.getInt(3));
                result.add(attendDO);
            }

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
        return result;
    }

    @Override
    public List<AttendDO> select_byUser(String username, Date startTime, Date endTime) {
        List<AttendDO> result = new ArrayList<AttendDO>();
        PreparedStatement ps = null;

        try{
            init();
            ps = conn.prepareStatement("SELECT * FROM Attend WHERE username=? AND attendTime BETWEEN ? AND ? ORDER BY attendTime;");
            ps.setString(1,username);
            ps.setTimestamp(2,new java.sql.Timestamp(startTime.getTime()));
            ps.setTimestamp(3,new java.sql.Timestamp(endTime.getTime()));
            ResultSet res = ps.executeQuery();
            //res.next();
            while(res.next())
            {
                AttendDO attendDO = new AttendDO();
                attendDO.setUsername(res.getString(1));
                attendDO.setAttendTime(new Date(res.getTimestamp(2).getTime()));
                attendDO.setType(res.getInt(3));
                result.add(attendDO);
            }

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
        return result;
    }

}
