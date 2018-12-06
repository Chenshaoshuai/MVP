package com.example.zuoyelog.P;

import com.example.zuoyelog.M.IModelImpl;
import com.example.zuoyelog.MyCallBack;
import com.example.zuoyelog.V.IView;

public class IPresenterImpl implements IPresenter{
    private IModelImpl model;
    private IView iView;

    public IPresenterImpl(IView iView) {
        this.iView = iView;
        model = new IModelImpl();
    }

    @Override
    public void startRequest(String urlStr, Class clazz) {
        model.requestData(urlStr, clazz, new MyCallBack() {
            @Override
            public void setData(Object data) {
                iView.showResponseData(data);
            }
        });
    }
    public void onDetach(){
        if(model!=null){
            model = null;
        }
        if(iView!= null){
            iView=null;
        }
    }
}
