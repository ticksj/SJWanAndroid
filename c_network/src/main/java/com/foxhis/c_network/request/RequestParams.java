package com.foxhis.c_network.request;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by SJ on 2019/1/18.
 * 封装所有请求参数到HashMap中
 */
public class RequestParams {
    //线程安全的HashMap
    public ConcurrentHashMap<String, String> urlParams = new ConcurrentHashMap();
    public ConcurrentHashMap<String, Object> fileParams = new ConcurrentHashMap();

    public RequestParams() {
        this(null);
    }

    public RequestParams(final String key, final String value) {
        this(new HashMap<String, String>() {
            {
                put(key, value);
            }
        });
    }

    public RequestParams(Map<String, String> source) {
        if (source != null) {
            for (Map.Entry<String, String> entry : source.entrySet()) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    public void put(String key, String value) {
        if (key != null&&value!=null) {
            urlParams.put(key,value);
        }
    }
    public void put(String key, Object object) throws FileNotFoundException {
        if (key != null) {
            fileParams.put(key,object);
        }
    }
    public boolean hasParams(){
        if (urlParams.size()>0||fileParams.size()>0) {
            return true;
        }
        return false;
    }

}
