package com.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class HeaderUtils {

    public static HttpEntity<String> getHeader(HttpServletRequest request){
        HttpHeaders headers=new HttpHeaders();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            headers.add(key, value);
        }
        return new HttpEntity<String>(headers);
    }
}
