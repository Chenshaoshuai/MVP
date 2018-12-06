package com.example.zuoyelog.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xlistview.me.maxwin.view.XListView;
import com.example.zuoyelog.Adapter.UserAdapter;
import com.example.zuoyelog.Bean.UserBean;
import com.example.zuoyelog.P.IPresenterImpl;
import com.example.zuoyelog.R;
import com.example.zuoyelog.V.IView;

public class FragmentData extends Fragment implements IView {
    private XListView xListView;
    private IPresenterImpl presenter;
     private UserAdapter adapter;
    private String url = "http://www.xieast.com/api/news/news.php";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragmentdata,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        xListView = view.findViewById(R.id.xListView);
        presenter = new IPresenterImpl(this);
        presenter.startRequest(url,UserBean.class);
        adapter = new UserAdapter(getActivity());
        xListView.setAdapter(adapter);

    }

    @Override
    public void showResponseData(Object data) {
          UserBean userBean = (UserBean) data;
           if(userBean.getCode()==1){
            adapter.setDatas(userBean.getData());
           }
    }
}
