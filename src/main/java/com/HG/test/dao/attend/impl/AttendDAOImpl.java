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
    public boolean insert_attend(AttendDO attendDO)
    {
        PreparedStatement ps = null;

        try{
            init();
            ps = conn.prepareStatement("INSERT INTO Attend VALUES(?,?,?);");
            ps.setString(1, attendDO.getUsername());
            ps.setTimestamp(2, new java.sql.Timestamp(attendDO.getAttendTime().getTime()));
            ps.setInt(3,attendDO.getType());
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
        //System.out.println(username + "    " + startTime);

        try{
            init();
            ps = conn.prepareStatement("SELECT * FROM Attend WHERE username=? AND attendTime BETWEEN ? AND ? ORDER BY attendTime;");
            ps.setString(1,username);
            ps.setTimestamp(2,new java.sql.Timestamp(startTime.getTime()));
            ps.setTimestamp(3,new java.sql.Timestamp(endTime.getTime()));
            ResultSet res = ps.executeQuery();
            //System.out.println(username);
            //res.next();
            while(res.next())
            {
                AttendDO attendDO = new AttendDO();
                attendDO.setUsername(res.getString(1));
                attendDO.setAttendTime(new Date(res.getTimestamp(2).getTime()));
                attendDO.setType(res.getInt(3));
                result.add(attendDO);
                //System.out.println(attendDO.getUsername() + "    " + attendDO.getAttendTime() + "   " + attendDO.getType());
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
    public List<List<AttendDO>> select_ALLUser(Date startTime, Date endTime) {
        List<List<AttendDO>> result = new ArrayList<List<AttendDO>>();
        List<AttendDO> ans = new ArrayList<AttendDO>();
        PreparedStatement ps = null;

        try{
            init();
            ps = conn.prepareStatement("SELECT * FROM  attend  WHERE attendTime BETWEEN ? AND ?  ORDER BY username,attendTime;");
            ps.setTimestamp(1,new java.sql.Timestamp(startTime.getTime()));
            ps.setTimestamp(2,new java.sql.Timestamp(endTime.getTime()));
            ResultSet res = ps.executeQuery();
           // System.out.println(res.);
            res.next();
            String name_record = res.getString(1);
            //System.out.println(name_record);
            //int i = 0;
            do
            {
                //System.out.println("0000" + new Date(res.getTimestamp(2).getTime()));
                if(res.getString(1).equals(name_record)) {
                        AttendDO attendDO = new AttendDO();
                        attendDO.setUsername(res.getString(1));
                        attendDO.setAttendTime(new Date(res.getTimestamp(2).getTime()));
                        attendDO.setType(res.getInt(3));
                    //System.out.println("1111:"+res.getString(1) +"  "+ new Date(res.getTimestamp(2).getTime()) +"  "+ res.getInt(3));
                        ans.add(attendDO);
                }else{
                        result.add(ans);
                        ans = new ArrayList<AttendDO>();
                        AttendDO attendDO = new AttendDO();
                        attendDO.setUsername(res.getString(1));
                        attendDO.setAttendTime(new Date(res.getTimestamp(2).getTime()));
                        attendDO.setType(res.getInt(3));
                        ans.add(attendDO);
                        name_record = res.getString(1);
                    }

            } while(res.next());

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
        result.add(ans);
        return result;
    }

}
