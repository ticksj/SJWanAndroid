package com.foxhis.c_network.listener;

import com.foxhis.c_network.exception.HttpException;

/**
 * Created by SJ on 2019/1/21.
 */
public interface ResultListener {
    void onSuccess(Object result);
    void onFailure(HttpException httpException);
}
