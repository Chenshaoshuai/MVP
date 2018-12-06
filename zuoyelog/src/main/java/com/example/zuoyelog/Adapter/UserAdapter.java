package com.example.zuoyelog.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.zuoyelog.Bean.UserBean;
import com.example.zuoyelog.R;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends BaseAdapter {
    private Context mContext;
    private List<UserBean.DataBean> beans;

    public UserAdapter(Context mContext) {
        this.mContext = mContext;
        beans = new ArrayList<>();
    }


    public void setDatas(List<UserBean.DataBean> beans) {
        this.beans = beans;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return beans.size();
    }

    @Override
    public UserBean.DataBean getItem(int position) {
        return beans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.text = convertView.findViewById(R.id.text);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.text.setText(getItem(position).getTitle());
        return convertView;
    }
    class ViewHolder{
        TextView text;
    }
}
