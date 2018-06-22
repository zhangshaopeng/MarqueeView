package com.shaopeng;

import android.app.Application;

import com.shao.utilslibrary.UtilManager;

/**
 * Describe：
 * auther：  zhangshaopeng
 * Emile：   1377785991@qq.com
 * Date：    2018/6/22
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        UtilManager.init(this);
    }
}
