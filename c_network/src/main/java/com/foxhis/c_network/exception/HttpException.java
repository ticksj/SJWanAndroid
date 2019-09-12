package com.foxhis.c_network.exception;

/**
 * Created by SJ on 2019/1/21.
 */
public class HttpException {
    private String errorCode;
    private String errorMsg;

    public HttpException(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
