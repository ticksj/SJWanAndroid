package com.sj.sjwanandroid.utils.network
import com.foxhis.c_network.CommonOkHttpClient
import com.foxhis.c_network.exception.HttpException
import com.foxhis.c_network.listener.ResultListener
import com.foxhis.c_network.request.CommonRequest
import com.foxhis.c_network.request.RequestParams
import com.foxhis.c_network.response.CommonCallBack
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sj.sjwanandroid.bean.ArticleData
import com.sj.sjwanandroid.bean.BaseBean

object HttpUtils {

    fun<T> getData(url:String,resultListener: ResultListener<T>){
        CommonOkHttpClient.sendRequest(CommonRequest.createGetRequest(url, RequestParams()), CommonCallBack(object :
            ResultListener<String> {
            override fun onSuccess(result: String) {
                val type = object : TypeToken<BaseBean<ArticleData>>() {}.type
                var baseBean = Gson().fromJson<BaseBean<ArticleData>>(result.toString(), type)
                if (0==baseBean.errorCode) {
                    resultListener.onSuccess(baseBean.data as T)
                }else{
                    resultListener.onFailure(HttpException(baseBean.errorCode.toString(),baseBean.errorMsg))
                }
            }
            override fun onFailure(httpException: HttpException?) {
                resultListener.onFailure(httpException)
            }
        }))

    }



}