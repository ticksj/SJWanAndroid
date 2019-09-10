package com.foxhis.c_network;

import com.foxhis.c_network.https.HttpsUtils;
import com.foxhis.c_network.response.CommonFileCallback;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by SJ on 2019/1/18.
 * @function 请求发送，请求参数配置，https支持
 */
public class CommonOkHttpClient {

    private static final int TIME_OUT = 30;//超时参数
    private static OkHttpClient mOkHttpClient;

    //为client配置参数
    static {
        //创建client对象构建者
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        //配置超时时间
        okHttpClientBuilder.connectTimeout(TIME_OUT,TimeUnit.SECONDS);
        okHttpClientBuilder.readTimeout(TIME_OUT,TimeUnit.SECONDS);
        okHttpClientBuilder.writeTimeout(TIME_OUT,TimeUnit.SECONDS);

        okHttpClientBuilder.followRedirects(true);//允许重定向
        //https支持
        okHttpClientBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;//允许所有证书
            }
        });
        //创建信任管理器与加密上下文协议设定
        okHttpClientBuilder.sslSocketFactory(HttpsUtils.initSSLSocketFactory(), HttpsUtils.initTrustManager());

        //生成client对象
        mOkHttpClient = okHttpClientBuilder.build();
    }

    /**
     * 发送具体的http、https请求
     * @param request
     * @param commonCallBack
     * @return
     */
    public static Call sendRequest(Request request, Callback commonCallBack){
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(commonCallBack);
        return call;
    }


    /**
     * 文件下载
     * @param request
     * @param commonFileCallback
     * @return
     */
    public static Call downloadFile(Request request, CommonFileCallback commonFileCallback) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(commonFileCallback);
        return call;
    }
}
