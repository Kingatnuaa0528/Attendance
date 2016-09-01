package com.HG.test.service.attend;

import java.util.Date;

/**
 * Created by gaohanqing on 16/8/31.
 */
public interface AttendService {

    /*
     * @func : 用来提交一次考勤记录，包括到达考勤和离开考勤
     * @param : dateTime表示发起操作的时间
     * @param : LeaveOrCome表示当前提交为到达考勤还是离开考勤 1:到达考情  0:离开考勤
     */
    public void SubmitAttend(String username, Date dateTime, int LeaveOrCome);

    /*
     * @func : 查询某个同学在一段时间内的出勤时间，以每天为单位，如果一天有多次考勤，则计算总时长，如果一天没有出勤，则时长为0
     */
    public Long[] QueryDuration(String username, Date startTime, Date endTime);

    /*
     * @func : 查询某个同学在一段时间内到达的时间，以每天为单位，如果一天有多次记录，则取最早的一次
     */
    public Date[] QueryComeTime(String username, Date startTime, Date endTime);

    /*
     * @func : 查询某个同学在一段时间内离开的时间，以每天为单位，如果一天有多次记录，则取最晚的一次
     */
    public Date[] QueryLeaveTime(String username, Date startTime, Date endTime);
}