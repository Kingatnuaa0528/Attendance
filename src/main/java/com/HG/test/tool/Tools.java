package com.HG.test.tool;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * Created by gaohanqing on 16/10/30.
 */
public class Tools {

    public static String getJsonString(HttpServletRequest request) throws IOException
    {
        byte[] bytes = new byte[1024 * 1024];
        InputStream is = request.getInputStream();

        int nRead = 1;
        int nTotalRead = 0;
        while (nRead > 0) {
            nRead = is.read(bytes, nTotalRead, bytes.length - nTotalRead);
            if (nRead > 0)
                nTotalRead = nTotalRead + nRead;
        }
        return new String(bytes, 0, nTotalRead, "utf-8");
    }

    public static Date DateFormat(Date date)
    {
        date.setHours(0);
        date.setMinutes(0);
        date.setSeconds(0);
        return date;
    }
}
