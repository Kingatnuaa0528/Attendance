package com.HG.test.test.dao;

import com.HG.test.dao.attend.AttendDAO;
import com.HG.test.pojo.AttendDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.util.Date;
import java.util.List;

/**
 * Created by HuaJieJie on 2016/9/4.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:dao.xml")
public class AttendDAOTest {
    @Resource
    private AttendDAO attendDAO;

    @Test
    public void insertTest()
    {
        AttendDO attendDO = new AttendDO();

        attendDO.setUsername("Huajie");
        System.out.println(new Date(System.currentTimeMillis()));
        attendDO.setAttendTime(new Date());
        attendDO.setType(0);

        attendDAO.insert_attend(attendDO);
    }

    @Test
    public void selectTest()
    {
        List<AttendDO> list = attendDAO.select_byTime(new Date(116, 8, 4, 0, 0, 0), new Date(116, 8, 5, 0, 0, 0));
        System.out.println(new Date(116, 8, 5, 0, 0, 0));
        for(int i = 0; i<list.size();i++)
        {
            System.out.println(list.get(i).getUsername() + "  " +list.get(i).getAttendTime() + "  " +list.get(i).getType());
        }

    }

    @Test
    public void selectbyuserTest()
    {
        List<AttendDO> list = attendDAO.select_byUser("Huajie",new Date(116, 8, 4, 0, 0, 0), new Date(116, 8, 5, 0, 0, 0));
        //System.out.println(new Date(116, 8, 5, 0, 0, 0));
        for(int i = 0; i<list.size();i++)
        {
            System.out.println(list.get(i).getUsername() + "  " +list.get(i).getAttendTime() + "  " +list.get(i).getType());
        }

    }

    @Test
    public void selectALLUserTest()
    {
        List<List<AttendDO>> list = attendDAO.select_ALLUser(new Date(116, 8, 4, 0, 0, 0), new Date(116, 8, 7, 0, 0, 0));
        //System.out.println(list.size());
        for(int i = 0; i<list.size();i++)
        {
            List<AttendDO> ll = list.get(i);
            //System.out.println(ll.size());
            for(int j = 0; j<ll.size();j++)
            {
                System.out.println(ll.get(j).getUsername() + "  " +ll.get(j).getAttendTime() + "  " +ll.get(j).getType());
            }

        }
    }

}
