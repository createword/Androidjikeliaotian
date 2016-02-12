package com.jikexueyuan.jike_chat;

import android.app.Application;

import io.rong.imkit.RongIM;

/**
 * Created by 泽群 on 2015/7/24.
 */
public class App extends Application {

    public static String token = "";
    public static String username = "";
    public static boolean isLogin = false;

    @Override
    public void onCreate() {
        super.onCreate();

        /*init rongcloud*/
        RongIM.init(this);
    }
}
