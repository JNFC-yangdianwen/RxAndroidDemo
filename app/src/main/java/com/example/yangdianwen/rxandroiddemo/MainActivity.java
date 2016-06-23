package com.example.yangdianwen.rxandroiddemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }
    @OnClick({R.id.bt_common,R.id.bt_operatorts,R.id.bt_schedulers})
    public void onAction(View v){
        switch (v.getId()) {
            case R.id.bt_common://基本实现界面
                startActivity(new Intent(this,Common.class));
                break;
            case R.id.bt_operatorts://基本实现界面
                startActivity(new Intent(this,Operators.class));
                break;
            case R.id.bt_schedulers://基本实现界面
                startActivity(new Intent(this,Scheduler.class));

                break;
        }
    }
}
