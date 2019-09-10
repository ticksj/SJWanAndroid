package com.foxhis.c_network.response;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.foxhis.c_network.exception.HttpException;
import com.foxhis.c_network.listener.ResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.foxhis.c_network.ConstantValue.FAIL_JSON;
import static com.foxhis.c_network.ConstantValue.FAIL_NETWORK;

/**
 * Created by SJ on 2019/1/21.
 */
public class CommonCallBack implements Callback {
    private static final String TAG = "CommonCallBack";

    public Handler handler = new Handler(Looper.getMainLooper());
    private ResultListener listener;
    public CommonCallBack(ResultListener listener) {
        this.listener = listener;
    }

    @Override
    public void onFailure(Call call, IOException e) {
        Log.e(TAG, "onFailure: " + Thread.currentThread().getName());
        handler.post(new Runnable() {
            @Override
            public void run() {
                listener.onFailure(new HttpException(FAIL_NETWORK,""));
            }
        });
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final String result = response.body().string();
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    listener.onSuccess(result);//测试

//                    if (RESPONSE_SUCCESS.equals(jsonObject.opt(RESPONSE_CODE))) {
//                        // TODO: 2019/1/21 此处确定要进行数据解析程度
//                        listener.onSuccess(result);
//                    } else if (RESPONSE_TOKEN_INVAILD.equals(jsonObject.optString(RESPONSE_CODE))) {
//                        listener.onFailure(new HttpException(FAIL_TOKEN, "Token 失效" + jsonObject.opt(RESPONSE_MSG)));
//                    } else {
//                        listener.onFailure(new HttpException(FAIL_RESULT, jsonObject.optString(RESPONSE_MSG)));
//                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    listener.onFailure(new HttpException(FAIL_JSON, e.toString()));
                }
            }
        });

    }
}
