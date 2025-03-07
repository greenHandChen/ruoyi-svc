package com.pegasus.security.utils;

import com.pegasus.security.constant.Constant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by enHui.Chen on 2019/9/5.
 */
public class SecurityUtil {
    /**
     * @Author: enHui.Chen
     * @Description: 设置跨域请求的头
     * @Data 2019/11/26
     */
    public static void setCorsHeader(HttpServletRequest request, HttpServletResponse response) {
        // 设置允许CORS的源
        String origin = request.getHeader("Origin");
        if (SecurityUtil.isAllowOrigin(origin)) {
            response.setHeader("Access-Control-Allow-Origin", origin);
        }
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT,DELETE,PATCH");
        // 设置CORS时请求允许携带的头字段
        response.setHeader("Access-Control-Allow-Headers", "authorization,pragma,cache-control,content-type");
        // 设置允许CORS时携带COOKIE
        response.setHeader("Access-Control-Allow-Credentials", "true");
        // 设置该请求两次预检的最大间隔时间
        response.setHeader("Access-Control-Max-Age", "3600");
    }


    /**
     * @Author: enHui.Chen
     * @Description: 是否允许跨域Host
     * @Data 2019/11/26
     */
    public static boolean isAllowOrigin(String origin) {
        return Constant.ALLOW_ORIGIN.contains(origin);
    }
}
