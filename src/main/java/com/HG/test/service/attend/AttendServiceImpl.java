package com.HG.test.service.attend;

import com.HG.test.dao.attend.AttendDAO;
import com.HG.test.pojo.AttendDO;
import com.HG.test.service.attend.AttendService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by HuaJieJie on 2016/9/5.
 */
public class AttendServiceImpl implements AttendService {

    @Resource
    private AttendDAO attendDAO;
    @Override
    public void SubmitAttend(String username, Date dateTime, int LeaveOrCome){

        AttendDO attendDO = new AttendDO();
        attendDO.setUsername(username);
        attendDO.setAttendTime(dateTime);
        attendDO.setType(LeaveOrCome);
        attendDAO.insert_attend(attendDO);

    }

    /*
     * @func : 查询某个同学在一段时间内的出勤时间，以每天为单位，如果一天有多次考勤，则计算总时长，如果一天没有出勤，则时长为0
     */
    @Override
    public Long[] QueryDuration(String username, Date startTime, Date endTime){

        List<Long> res = new ArrayList<Long>();
        List<AttendDO> list = attendDAO.select_byUser(username, startTime, endTime);
        System.out.println(list.size());
        int i = 0;
        Long tmp = new Long(0);
        while(i<list.size())
        {
            int day_record = list.get(0).getAttendTime().getDay();

            while(i<list.size() && list.get(i).getType() != 0) i++;

            if(day_record <  list.get(i).getAttendTime().getDay() );
            {
                day_record = list.get(i).getAttendTime().getDay();
                res.add(tmp);
            }

            Date st = list.get(i).getAttendTime();

            while(i<list.size() && list.get(i).getType() != 1) i++;
            Date et = list.get(i).getAttendTime();

            if(et.getDay() > day_record)
            {
                tmp = tmp + new Date(et.getYear()-1990, et.getMonth(), et.getDay(), 0 ,0 ,0).getTime()-st.getTime();
                res.add(tmp);
                tmp = et.getTime() - new Date(et.getYear()-1990, et.getMonth(), et.getDay(), 0 ,0 ,0).getTime();
                i++;
            }

            else
            {
                tmp = tmp + et.getTime() - st.getTime();
            }
        }

        if(res.get(0)==0) res.remove(0);
        return res.toArray(new Long[1]);






    }

    /*
     * @func : 查询某个同学在一段时间内到达的时间，以每天为单位，如果一天有多次记录，则取最早的一次
     */
    public Date[] QueryComeTime(String username, Date startTime, Date endTime){
        List<Date> res = new ArrayList<Date>();
        List<AttendDO> list = attendDAO.select_byUser(username, startTime, endTime);
        if(list == null) return null;
        int i = 0;
        int day_record = -1;

        while(i<list.size())
        {
            int day = list.get(i).getAttendTime().getDay();
            if(day > day_record)
            {
                if(day_record != -1)
                {
                }
                day_record = list.get(i).getAttendTime().getDay();
                res.add(list.get(i).getAttendTime());
            }
        }
        return res.toArray(new Date[1]);
    }

    /*
     * @func : 查询某个同学在一段时间内离开的时间，以每天为单位，如果一天有多次记录，则取最晚的一次
     */
    public Date[] QueryLeaveTime(String username, Date startTime, Date endTime){
        return null;
    }


    /*
     * @func : 查询所有同学在一段时间内出勤时间，以每天为单位，如果一天有多次记录，则计算总时长，如果一天没有出勤，则时长为0
     * @return : <username, duration>
     */
    public Map<String, Long> QueryAllDuration(Date startTime, Date endTime){
        return null;
    }


    /*
     * @func : 查询所有同学在某一天到达的时间，如果一天有多次记录，则取最早的一次，如果一天没有出勤，则时间为null
     * @return : <username, ArriveTime>
     * @param : time的格式：YYYY-MM-DD HH-MM-SS，只取到日期即可
     */
    public Map<String, Date> QueryAllComeTime(Date time){
        return null;
    }

    /*
     * @func : 查询所有同学在某一天离开的时间，如果一天有多次记录，则取最晚的一次，如果一天没有出勤，则时间为null
     * @return : <username, LeaveTime>
     * @param : time的格式：YYYY-MM-DD HH-MM-SS，只取到日期即可
     */
    public Map<String, Date> QueryAllLeaveTime(Date time){
        return null;
    }
}
