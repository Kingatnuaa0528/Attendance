package com.HG.test.pojo;

import java.util.Date;

/**
 * Created by gaohanqing on 16/8/30.
 */
public class AttendDO {

    private String username;

    private Date attendTime;

    //1: come  2:leave
    private int type;

    private int unused;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getAttendTime() {
        return attendTime;
    }

    public void setAttendTime(Date attendTime) {
        this.attendTime = attendTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
