package com.HG.test.dao.attend.impl;

import com.HG.test.dao.attend.AttendDAO;
import com.HG.test.pojo.AttendDO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by gaohanqing on 16/8/30.
 */
public class AttendDAOImpl implements AttendDAO {

    public void insert_attend(AttendDO attendDO)
    {

    }

    public List<AttendDO> select_byTime(Date startTime, Date endTime)
    {
        List<AttendDO> result = new ArrayList<AttendDO>();

        return result;
    }

    public List<AttendDO> select_byUser(String username)
    {
        List<AttendDO> result = new ArrayList<AttendDO>();

        return result;
    }
}
