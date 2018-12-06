package com.example.zuoyelog.M;

import com.example.zuoyelog.MyCallBack;

public interface IModel {
    void requestData(String urlStr, Class clazz, MyCallBack callBack);
}
