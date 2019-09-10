package com.foxhis.c_network;

import com.foxhis.c_network.listener.FileDownLoadListener;
import com.foxhis.c_network.listener.ResultListener;
import com.foxhis.c_network.request.CommonRequest;
import com.foxhis.c_network.request.RequestParams;
import com.foxhis.c_network.response.CommonCallBack;
import com.foxhis.c_network.response.CommonFileCallback;

/**
 * Created by SJ on 2019/1/21.
 */
public class HttpUtils {

    public static void sendRequest(String url,String json,ResultListener resultListener){
        CommonOkHttpClient.sendRequest(CommonRequest.creatPostJsonRequest(url,json),new CommonCallBack(resultListener));
    }

    public static void sendRequest(String url, RequestParams requestParams, ResultListener resultListener){
        CommonOkHttpClient.sendRequest(CommonRequest.creatPostRequest(url,requestParams),new CommonCallBack(resultListener));
    }

    public static void downloadFile(String url, RequestParams requestParams, FileDownLoadListener fileDownLoadListener,String path){
        CommonOkHttpClient.downloadFile(CommonRequest.creatPostRequest(url,requestParams),new CommonFileCallback(fileDownLoadListener,path));
    }

    public static void uploadFile(String url, RequestParams requestParams, ResultListener resultListener) {
        CommonOkHttpClient.sendRequest(CommonRequest.createMultiPostRequest(url, requestParams), new CommonCallBack(resultListener));
    }


}
