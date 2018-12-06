package com.example.zuoyelog.M;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.example.zuoyelog.MyCallBack;
import com.example.zuoyelog.Utils.HttpUtil;
import com.google.gson.Gson;

public class IModelImpl implements IModel {
    private MyCallBack callBack;
    public <T> T requestData(String urlStr,Class clazz){
        return (T) new Gson().fromJson(HttpUtil.getRequest(urlStr),clazz);
    }
    @SuppressLint("StaticFieldLeak")
    @Override
    public void requestData(final String urlStr, final Class clazz, final MyCallBack callBack) {
        new AsyncTask<String,Void,Object>(){

            @Override
            protected Object doInBackground(String... strings) {

                return requestData(strings[0],clazz);
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                callBack.setData(o);
            }
        }.execute(urlStr);
    }
}
