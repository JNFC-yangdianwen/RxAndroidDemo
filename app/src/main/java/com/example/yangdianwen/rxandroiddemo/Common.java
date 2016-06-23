package com.example.yangdianwen.rxandroiddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

public class Common extends AppCompatActivity implements CascSever.OnResultListener{

    private CascSever cascSever;
    Subscriber<String> subscriber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        cascSever = new CascSever();
    }
    /**
     *基本案例实现
     */

    public void clik(View view) {
        //创建一个Observable被观察者
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                int result=10/5;
              //onNext可以调用多次
               subscriber.onNext(result+"");
               subscriber.onNext("cast");
                //事件结束标志
                subscriber.onCompleted();
                //事件错误的标记
//                subscriber.onError();
            }
        }).subscribe(new Observer<String>() {
            //指定观察者
            //被观察者必须指定观察者，事件流程才会开启
            @Override
            public void onNext(String s) {
                System.out.println("onNext"+s);

            }
            @Override
            public void onCompleted() {

                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {


            }

        });




    }
    //被观察者变形1
    public void clik1(View view) {
        //Observer<Integer>中Integer和jsut方法的参数类型相同
        //just方法的参数可变
       Observable.just(1,2).subscribe(new Observer<Integer>() {

           @Override
           public void onNext(Integer integer) {
               System.out.println("onNext"+integer);
           }    @Override
           public void onCompleted() {
               System.out.println("onCompleted");
           }

           @Override
           public void onError(Throwable e) {


           }

       });


    }
    //被观察者变形2,from方法的参数 可以是集合，数组
    public void clik2(View view) {
      subscriber = new Subscriber<String>() {//订阅者

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
    System.out.println("onNext"+s);
            }
        };
        Observable.from(new  String[]{"url1","url2"})
          .subscribe(subscriber);

    }
    public void clik3(View view) {
        String[]arr={"url1","url2","url3"};

        Observable.from(arr)
                .subscribe(new Action1<String>() {//onNext
                    @Override
                    public void call(String s) {
                        System.out.println("call:" + s);
                    }
                }, new Action1<Throwable>() {//onError
                    @Override
                    public void call(Throwable throwable) {

                    }
                }, new Action0() {//onCompleted
                    @Override
                    public void call() {
                        System.out.println("onCompleted:");
                    }
                });

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (subscriber!=null&&!subscriber.isUnsubscribed()){
            subscriber.unsubscribe();
        }
    }

    public void cliklistener(View view) {
//        getResult();

        getResultAsync();
    }
// 异步请求
    private void getResultAsync() {

       cascSever.calcAsync(10, 5,this);
    }
    @Override
    public void onSucess(int result){
        System.out.println("onSucess"+result);
    }
    @Override
    public void onFail(){
        System.out.println("onFail..不会");
    }
//    同步请求
    private void getResult() {
        //10套房子，5个人平分
        int result = cascSever.calc(10, 5);
        System.out.println("result"+result);
    }


}
