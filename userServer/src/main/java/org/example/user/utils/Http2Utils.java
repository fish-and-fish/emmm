package org.example.user.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.hutool.json.JSONUtil;

public class Http2Utils {

    public static String getRequestByJson(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();
        String line;
        String xmlData = "";
        while ((line = reader.readLine()) != null) {
            xmlData += line;
        }
        return xmlData;
    }

    public static Map<String, String> getRequestHeader(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String, String> map = new HashMap<>();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            map.put(headerName, headerValue);
        }
        return map;
    }

    public static String getRequestBody(HttpServletRequest request) throws IOException {
        String contentType = request.getContentType();
        if (contentType.contains("application/json")) {
            return getRequestByJson(request);
        } else if (contentType.contains("application/x-www-form-urlencoded;")) {
            return JSONUtil.toJsonStr(getRequestBodyByForm(request));
        } else {
            return "";
        }
    }

    public static Map<String, String> getRequestBodyByForm(HttpServletRequest request) {
        // 处理 Form 表单类型的请求
        Enumeration<String> parameterNames = request.getParameterNames();
        Map<String, String> params = new HashMap<>();
        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            params.put(key, request.getParameter(key));
        }
        return params;
    }


}
