package com.example.zuoyelog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.example.zuoyelog.Fragment.FragmentData;
import com.example.zuoyelog.Fragment.FragmentName;

import java.util.ArrayList;
import java.util.List;

public class LogActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private List<Fragment> list;
    private RadioGroup group;
    private String name;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        Intent intent =getIntent();
        name =intent.getStringExtra("name");
        viewPager = findViewById(R.id.viewPager);
        group = findViewById(R.id.group);

        list = new ArrayList<>();
        list.add(new FragmentData());
        list.add(new FragmentName());

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.data:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.name:
                        viewPager.setCurrentItem(1);
                        break;
                }
            }
        });
    }
    public String QR(){
        return name;
    }

}
