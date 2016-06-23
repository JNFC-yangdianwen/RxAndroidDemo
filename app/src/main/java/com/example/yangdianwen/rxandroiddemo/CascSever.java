package com.example.yangdianwen.rxandroiddemo;

import android.os.SystemClock;

/**
 * Created by yangdianwen on 16-6-22.
 * 计算数据
 */
public class CascSever {
    //计算每个人分几套房子
    //total 房子总数
    //count 人数
    int result;
    public int calc(final int total, final int count){

        /**  进行耗时操作
         * 进行网络请求操作
         * 数据库查询的操作
         * 文件读写操作
         */
        //开启异步请求获取数据
        new Thread(){
            @Override
            public void run() {
                super.run();

                SystemClock.sleep(2000);
                result = total / count;

            }
        }.start();
        return result;
    }
/**
 * 通过监听回调接触耦合
 */

    public void calcAsync(final int total, final int count, final Common common) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    SystemClock.sleep(2000);
                    result = total / count;
                    common.onSucess(result);
                }
                catch (Exception e)
                {
               e.printStackTrace();
                    common.onFail();
                }
            }
        }.start();

    }
    //面向接口
    public interface OnResultListener{
        void onSucess(int Result);
        void onFail();
    }
}

