package com.example.zuoyelog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zuoyelog.Bean.LogBean;
import com.example.zuoyelog.P.IPresenterImpl;
import com.example.zuoyelog.V.IView;

public class MainActivity extends AppCompatActivity implements IView {
    private EditText edit_name,edit_pass;
    private Button btn_log;
    private IPresenterImpl presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit_name = findViewById(R.id.edit_name);
        edit_pass = findViewById(R.id.edit_pass);
        btn_log = findViewById(R.id.btn_log);
        presenter = new IPresenterImpl(this);
        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edit_name.getText().toString();
                String pass = edit_pass.getText().toString();
                presenter.startRequest("http://www.xieast.com/api/user/login.php?username="+name+"&password="+pass,LogBean.class);

            }

        });
    }

    @Override
    public void showResponseData(Object data) {
         LogBean logBean = (LogBean) data;
         if(logBean.getCode()==100){
             Toast.makeText(MainActivity.this,logBean.getMsg()+"",Toast.LENGTH_SHORT).show();
             Intent intent = new Intent(MainActivity.this,LogActivity.class);
             intent.putExtra("name",logBean.getData().getName());
             startActivity(intent);
         }else if (logBean.getCode()==101){
             Toast.makeText(MainActivity.this,logBean.getMsg()+"",Toast.LENGTH_SHORT).show();
         }else if(logBean.getCode()==102){
             Toast.makeText(MainActivity.this,logBean.getMsg()+"",Toast.LENGTH_SHORT).show();
         }else if(logBean.getCode()==103){
             Toast.makeText(MainActivity.this,logBean.getMsg()+"",Toast.LENGTH_SHORT).show();
         }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }
}
