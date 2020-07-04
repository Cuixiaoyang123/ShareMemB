package com.dreamlost.sharememb;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.Log;


import com.dreamlost.sqservice.constant.DebugTag;

import java.util.List;

public class SqServiceApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(DebugTag.TAG, "SqServiceApplication onCreate pid = " +
                android.os.Process.myPid() + ", getProcessName = " + getProcessName(this,
                android.os.Process.myPid()));

//        if ("com.example.cxy.sharemem".equals(getProcessName(this,
//                android.os.Process.myPid()))) {
//            SqServiceProxy.init(this, getMainLooper(), null);
//        }

        //getProcessName = com.zk.sqnet
        //getProcessName = com.sq.service
    }


    public static String getProcessName(Context cxt, int pid) {
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }
}
