package com.example.yangdianwen.rxandroiddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

public class Operators extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operators);
    }

    public void clikMap(View view) {
        //Integer转换前的数据类型，也就是just方法中的原始数据类型，String是转换后的数据类型
        Observable.just(6).map(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return integer+"";
            }
        }).map(new Func1<String, Long>() {
            @Override
            public Long call(String s) {
                return Long.parseLong(s);
            }
        }).subscribe(new Action1<Long>() {
            @Override
            public void call(Long l) {
                System.out.println("call"+l);
            }
        });






    }
}
