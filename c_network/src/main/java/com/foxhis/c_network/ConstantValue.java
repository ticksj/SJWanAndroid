package com.foxhis.c_network;

/**
 * Created by SJ on 2019/1/21.
 */
public interface ConstantValue {


    String FAIL_NETWORK = "1";//请求失败
    String FAIL_RESULT = "2";//业务失败
    String FAIL_JSON = "3";//解析失败
    String FAIL_TOKEN = "4";//超时失败
    String FAIL_IO = "5";//IO失败
    String FAIL_OTHER = "6";//其他失败--文件路径不存在

    String RESPONSE_CODE = "code";//业务解析 key
    String RESPONSE_SUCCESS = "0";//业务成功 value
    String RESPONSE_TOKEN_INVAILD = "token invaild";// 超时 value
    String RESPONSE_MSG = "msg";// 业务 附加信息value
}
