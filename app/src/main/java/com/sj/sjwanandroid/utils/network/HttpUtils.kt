package com.sj.sjwanandroid.utils.network

import com.foxhis.c_network.CommonOkHttpClient
import com.foxhis.c_network.exception.HttpException
import com.foxhis.c_network.listener.ResultListener
import com.foxhis.c_network.request.CommonRequest
import com.foxhis.c_network.request.RequestParams
import com.foxhis.c_network.response.CommonCallBack
import org.json.JSONObject


object HttpUtils {

    private fun getData(url: String, resultListener: ResultListener) {
        CommonOkHttpClient.sendRequest(
            CommonRequest.createGetRequest(url, RequestParams()),
            CommonCallBack(object :
                ResultListener {
                override fun onSuccess(result: Any?) {
                    var jsonObject = JSONObject(result.toString())
                    when {
                        jsonObject["errorCode"] == 0 -> resultListener.onSuccess(jsonObject["data"])
                        else -> resultListener.onFailure(
                            HttpException(
                                jsonObject["errorCode"].toString(),
                                jsonObject["errorMsg"].toString()
                            )
                        )
                    }
                }

                override fun onFailure(httpException: HttpException?) {
                    resultListener.onFailure(httpException)
                }
            })
        )
    }

    private fun postData(url: String, json: String, resultListener: ResultListener) {
        CommonOkHttpClient.sendRequest(
            CommonRequest.creatPostJsonRequest(url, json),
            CommonCallBack(object : ResultListener {
                override fun onSuccess(result: Any?) {
                    var jsonObject = JSONObject(result.toString())
                    when {
                        jsonObject["errorCode"] == 0 -> resultListener.onSuccess(jsonObject["data"])
                        else -> resultListener.onFailure(
                            HttpException(
                                jsonObject["errorCode"].toString(),
                                jsonObject["errorMsg"].toString()
                            )
                        )
                    }
                }

                override fun onFailure(httpException: HttpException?) {
                    resultListener.onFailure(httpException)
                }
            })
        )
    }


    fun getBanner(resultListener: ResultListener) {
        getData(WanAndroidUrl.getBanner(), resultListener)
    }

    fun getNewArticle(page: Int, resultListener: ResultListener) {
        getData(WanAndroidUrl.getNewArticle(page), resultListener)
    }

    fun getNewProject(page: Int, resultListener: ResultListener) {
        getData(WanAndroidUrl.getNewProject(page), resultListener)
    }

    fun getFriendSites(resultListener: ResultListener) {
        getData(WanAndroidUrl.getFriendSites(), resultListener)
    }

    fun getHotKey(resultListener: ResultListener) {
        getData(WanAndroidUrl.getHotKey(), resultListener)
    }

    fun getTopArticle(resultListener: ResultListener) {
        getData(WanAndroidUrl.getTopArticle(), resultListener)
    }

    fun getAndroidTree(resultListener: ResultListener) {
        getData(WanAndroidUrl.getAndroidTree(), resultListener)
    }

    fun getAndroidTreeArticle(id: Int, resultListener: ResultListener) {
        getData(WanAndroidUrl.getAndroidTreeArticle(id), resultListener)
    }

    fun getNavi(resultListener: ResultListener) {
        getData(WanAndroidUrl.getNavi(), resultListener)
    }

    fun getWeChatList(resultListener: ResultListener) {
        getData(WanAndroidUrl.getWeChatList(), resultListener)
    }

    fun getWeChatArticle(id: Int, page: Int, resultListener: ResultListener) {
        getData(WanAndroidUrl.getWeChatArticle(id, page), resultListener)
    }

    fun getSearchWeChatArticle(id: Int, page: Int, search: String, resultListener: ResultListener) {
        getData(WanAndroidUrl.getSearchWeChatArticle(id, page, search), resultListener)
    }

    fun getProjectTree(resultListener: ResultListener) {
        getData(WanAndroidUrl.getProjectTree(), resultListener)
    }

    fun getProjectList(page: Int, id: Int, resultListener: ResultListener) {
        getData(WanAndroidUrl.getProjectList(page, id), resultListener)
    }

    fun login(name: String, pwd: String, resultListener: ResultListener) {
        var json = JSONObject()
        json.put("username", name)
        json.put("password", pwd)
        postData(WanAndroidUrl.login(), json.toString(), resultListener)
    }

    fun register(name: String, pwd: String, repwd: String, resultListener: ResultListener) {
        var json = JSONObject()
        json.put("username", name)
        json.put("password", pwd)
        json.put("repassword", repwd)
        postData(WanAndroidUrl.register(), json.toString(), resultListener)
    }

    fun getLogout(resultListener: ResultListener) {
        getData(WanAndroidUrl.getLogout(), resultListener)
    }

    fun getCollectList(page: Int, resultListener: ResultListener) {
        getData(WanAndroidUrl.getCollectList(page), resultListener)
    }

    fun collectIn(id: Int, resultListener: ResultListener) {
        postData(WanAndroidUrl.collectIn(id), "", resultListener)
    }

    fun collectOut(title: String, author: String, link: String, resultListener: ResultListener) {
        var json = JSONObject()
        json.put("title", title)
        json.put("author", author)
        json.put("link", link)
        postData(WanAndroidUrl.collectOut(), json.toString(), resultListener)
    }

    fun cancelInArticles(articleId: Int, resultListener: ResultListener) {
        postData(WanAndroidUrl.cancelInArticles(articleId), "", resultListener)
    }

    fun cancelInCollections(articleId: Int, originId: Int, resultListener: ResultListener) {
        var json = JSONObject()
        json.put("originId", originId)
        postData(WanAndroidUrl.cancelInCollections(articleId), json.toString(), resultListener)
    }

    fun getCollectionSites(resultListener: ResultListener) {
        getData(WanAndroidUrl.getCollectionSites(), resultListener)
    }

    fun getCollectionUrl(resultListener: ResultListener) {
        getData(WanAndroidUrl.getCollectionUrl(), resultListener)
    }

    fun editCollectionSite(id: Int, name: String, link: String, resultListener: ResultListener) {
        var json = JSONObject()
        json.put("id", id)
        json.put("name", name)
        json.put("link", link)
        postData(WanAndroidUrl.editCollectionSite(), json.toString(), resultListener)
    }

    fun deleteCollectionSite(id: Int, resultListener: ResultListener) {
        var json = JSONObject()
        json.put("id", id)
        postData(WanAndroidUrl.deleteCollectionSite(), json.toString(), resultListener)
    }

    fun search(page: Int, k: String, resultListener: ResultListener) {
        var json = JSONObject()
        json.put("k", k)
        postData(WanAndroidUrl.search(page), json.toString(), resultListener)
    }
}