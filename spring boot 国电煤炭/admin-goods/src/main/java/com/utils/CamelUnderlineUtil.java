package com.utils;

import com.github.pagehelper.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 驼峰命名与下划线转换
 * @Author Harlan
 * @Date 2020/11/8
 */
public class CamelUnderlineUtil {

    private static final char UNDERLINE ='_';

    public static String underLineToCamel(String camelParam){
        if (StringUtil.isEmpty(camelParam)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int len = camelParam.length();
        for (int i = 0; i < len; i++) {
            char c = camelParam.charAt(i);
            if (c==UNDERLINE) {
                if(++i<len){
                    sb.append(Character.toUpperCase(camelParam.charAt(i)));
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }


    public static Map<String, Object> underLineToCamelForMap(Map<String, Object> underLineMap){
        Map<String, Object> camelMap = new HashMap<>(underLineMap.size());
        for (String s : underLineMap.keySet()) {
            camelMap.put(underLineToCamel(s), underLineMap.get(s));
        }
        return camelMap;
    }
}
