/**
 * Copyright (C), 2015-2019, lianfankeji
 * FileName: RequestUtil
 * Author: 25414
 * Date: 2019/5/2 13:57
 * Description: RequestUtil
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.hg.gama.gamautil;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hg.gama.gamautil.numasutil.exception.GamaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RequestUtil {
    private static ObjectMapper mapper = new ObjectMapper(); // can reuse, share
    private static final Logger logger = LoggerFactory.getLogger(RequestUtil.class);

    /**
     * 从request提可用字段转换成对象.(若有deleted，默认=0)
     */
    public static <T> T  getObjectFromRequest (Class<T> clazz, HttpServletRequest request ){
        try{
            Map<String, Object> ParmMap=getParameterMapFromRequest(request);
            T obj = clazz.newInstance();
            obj= mapToObject(ParmMap,clazz);
            return obj;
        }catch (Exception e){
            e.printStackTrace();
            throw new GamaException("从request解析对象出错");
        }


    }
    /**
     * <获取参数map>
     */
    protected static Map<String, Object> getParameterMapFromRequest(HttpServletRequest request) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, String[]> tempMap = request.getParameterMap();

        Set<String> keys = tempMap.keySet();
        for (String key : keys) {
            if(tempMap.get(key)!=null&&tempMap.get(key).length>1)throw new Exception("request存在两个相同参数");

            byte source [] = request.getParameter(key).getBytes("iso8859-1");
            String modelname = new String (source,"UTF-8");
            resultMap.put(key,modelname);
        }
        return resultMap;
    }
    /**
     * 将Map可用字段转换成对象.(若有deleted，默认=0)
     */
    public static    <T> T mapToObject(Map<String, Object> map, Class<T> clazz)throws Exception {
        if (map == null) { return null; }
        T obj = clazz.newInstance();
            Field[] fields = obj.getClass().getDeclaredFields();

            for (Field field : fields) {
                int mod = field.getModifiers();
                field.setAccessible(true);
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                String fieldName = field.getName();
                if("deleted".equals(fieldName)){
                    field.set(obj, 0);
                    continue;
                }
                if(!Character.isLowerCase(fieldName.charAt(0)))
                    fieldName = (new StringBuilder()).append(Character.toLowerCase(fieldName.charAt(0))).append(fieldName.substring(1)).toString();
                field.set(obj, map.get(fieldName));
            }
        return obj;
    }

    /**
     * 将Json转换成对象.
     */
    public static <T> T toObject(String json, Class<T> clazz) {
        if (json == null || json.isEmpty()) {
            return null;
        }
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            logger.warn("message:" + e.getMessage() + " json:" + json);
            throw new RuntimeException(e);
        }
    }
}
