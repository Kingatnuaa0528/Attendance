package com.HG.test.dao.attend;

import com.HG.test.pojo.AttendDO;

import java.util.Date;
import java.util.List;

/**
 * Created by gaohanqing on 16/8/30.
 */
public interface AttendDAO {

    public boolean insert_attend(AttendDO attendDO);

    public List<AttendDO> select_byTime(Date startTime, Date endTime);

    public List<AttendDO> select_byUser(String username, Date startTime, Date endTime);
    public List<List<AttendDO>> select_ALLUser(Date startTime, Date endTime);
}
