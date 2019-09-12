package com.foxhis.c_network.request;

import java.io.File;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by SJ on 2019/1/18.
 * 接受请求参数，生成Request对象
 */
public class CommonRequest {

    /**
     * 请求类型为Form表单类型
     *
     * @param url
     * @param params
     * @return 返回创建好的Post类型Request对象
     */
    public static Request creatPostRequest(String url, RequestParams params) {
        return creatPostRequest(url, params, null);
    }

    /**
     * 添加请求头
     * @param url
     * @param params
     * @param headers
     * @return
     */
    public static Request creatPostRequest(String url, RequestParams params, RequestParams headers) {
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
        }
        //通过请求构建类的build方法获取到真正的请求体对象
        FormBody formBody = builder.build();

        //添加请求头
        Headers.Builder mHeaderBuild = new Headers.Builder();
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.urlParams.entrySet()) {
                mHeaderBuild.add(entry.getKey(), entry.getValue());
            }
        }
        Headers mHeader = mHeaderBuild.build();

        return new Request.Builder().url(url).post(formBody).headers(mHeader).build();
    }

    /**
     * 请求类型为Json类型
     *
     * @param url
     * @param json
     * @return 返回创建好的Post类型Request对象
     */
    public static Request creatPostJsonRequest(String url, String json) {
        return creatPostJsonRequest(url, json,null);
    }

    /**
     * 添加请求头
     * @param url
     * @param json
     * @param headers
     * @return
     */
    public static Request creatPostJsonRequest(String url, String json, RequestParams headers) {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);

        //添加请求头
        Headers.Builder mHeaderBuild = new Headers.Builder();
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.urlParams.entrySet()) {
                mHeaderBuild.add(entry.getKey(), entry.getValue());
            }
        }
        Headers mHeader = mHeaderBuild.build();

        return new Request.Builder().url(url).post(body).headers(mHeader).build();
    }



    /**
     * @param url
     * @param params
     * @return 返回创建好的Get类型Request对象
     */
    public static Request createGetRequest(String url, RequestParams params) {
        return createGetRequest(url, params,null);
    }

    /**
     * 可以带请求头的Get请求
     * @param url
     * @param params
     * @param headers
     * @return
     */
    public static Request createGetRequest(String url, RequestParams params, RequestParams headers) {
        StringBuilder urlBuilder = new StringBuilder(url).append("?");
        if (params != null) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                urlBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        //添加请求头
        Headers.Builder mHeaderBuild = new Headers.Builder();
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.urlParams.entrySet()) {
                mHeaderBuild.add(entry.getKey(), entry.getValue());
            }
        }
        Headers mHeader = mHeaderBuild.build();
        return new Request.Builder().
                url(urlBuilder.substring(0, urlBuilder.length() - 1))
                .get()
                .headers(mHeader)
                .build();
    }





    /**
     * 文件上传请求
     *
     * @return
     */
    private static final MediaType FILE_TYPE = MediaType.parse("application/octet-stream");

    public static Request createMultiPostRequest(String url, RequestParams params) {

        MultipartBody.Builder requestBody = new MultipartBody.Builder();
        requestBody.setType(MultipartBody.FORM);
        if (params != null) {

            for (Map.Entry<String, Object> entry : params.fileParams.entrySet()) {
                if (entry.getValue() instanceof File) {
                    requestBody.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + entry.getKey() + "\""),
                            RequestBody.create(FILE_TYPE, (File) entry.getValue()));
                } else if (entry.getValue() instanceof String) {

                    requestBody.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + entry.getKey() + "\""),
                            RequestBody.create(null, (String) entry.getValue()));
                }
            }
        }
        return new Request.Builder().url(url).post(requestBody.build()).build();
    }

}
