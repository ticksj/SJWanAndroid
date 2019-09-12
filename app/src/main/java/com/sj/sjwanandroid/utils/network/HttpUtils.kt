package com.sj.sjwanandroid.utils.network
import com.foxhis.c_network.CommonOkHttpClient
import com.foxhis.c_network.exception.HttpException
import com.foxhis.c_network.listener.ResultListener
import com.foxhis.c_network.request.CommonRequest
import com.foxhis.c_network.request.RequestParams
import com.foxhis.c_network.response.CommonCallBack
import org.json.JSONObject


object HttpUtils {

    fun getData(url: String, resultListener: ResultListener) {
        CommonOkHttpClient.sendRequest(
            CommonRequest.createGetRequest(url, RequestParams()),
            CommonCallBack(object :
                ResultListener {
                override fun onSuccess(result: Any?) {
                    var jsonObject = JSONObject(result.toString())
                    when{
                        jsonObject["errorCode"]==0 -> resultListener.onSuccess(jsonObject["data"])
                        else -> resultListener.onFailure(HttpException(jsonObject["errorCode"].toString(),jsonObject["errorMsg"].toString()))
                    }
                }
                override fun onFailure(httpException: HttpException?) {
                    resultListener.onFailure(httpException)
                }
            })
        )
    }


}