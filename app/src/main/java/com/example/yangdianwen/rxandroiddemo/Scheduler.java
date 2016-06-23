
package com.example.yangdianwen.rxandroiddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Scheduler extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler);
    }

    public void clik(View view) {

        //创建一个Observable被观察者
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                System.out.println("call。。。。。。。"+Thread.currentThread().getName());
              //在子线程处理
                int result=10/5;
                //onNext可以调用多次
                subscriber.onNext(result+"");
                subscriber.onNext("cast");
                //事件结束标志
                subscriber.onCompleted();
                //事件错误的标记
//                subscriber.onError();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
            //指定观察者
            //被观察者必须指定观察者，事件流程才会开启
            @Override
            public void onNext(String s) {
                //回到主线程
                System.out.println("onNext。。。。。。。"+Thread.currentThread().getName());
                System.out.println("onNext。。。。。。。"+s);

            }
            @Override
            public void onCompleted() {

                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {


            }

        });





//        //Integer转换前的数据类型，也就是just方法中的原始数据类型，String是转换后的数据类型
//        Observable.just(6).map(new Func1<Integer, String>() {
//            @Override
//            public String call(Integer integer) {
//                return integer+"";
//            }
//        }).map(new Func1<String, Long>() {
//            @Override
//            public Long call(String s) {
//                return Long.parseLong(s);
//            }
//        }).subscribe(new Action1<Long>() {
//            @Override
//            public void call(Long l) {
//                System.out.println("call"+l);
//            }
//        });



    }
}
