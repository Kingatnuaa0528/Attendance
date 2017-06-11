package com.HG.test.tool;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by hanqing.ghq on 2017/6/9.
 */
public class ResponseJSONUtil {

    public static final String JSON_CONTENT_TYPE = "application/json";
    public static final String DEFAULT_CHARSET = "utf-8";

    public static void responseJson(HttpServletResponse response, JSONObject jsonObject) {
        response.addHeader("Cache-Control", "no-cache");
        response.setContentType(JSON_CONTENT_TYPE);
        PrintWriter out;
        try {
            out = response.getWriter();
            out.write(jsonObject.toJSONString());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
