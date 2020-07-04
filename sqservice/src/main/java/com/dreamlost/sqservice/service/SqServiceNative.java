package com.dreamlost.sqservice.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.dreamlost.sqservice.constant.DebugTag;


public class SqServiceNative extends Service {

    private SqServiceStub mSqServiceStub;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(DebugTag.TAG, "SqServiceNative ： onCreate pid = " + android.os.Process.myPid());

        mSqServiceStub = new SqServiceStub();
    }

    @Override
    public void onDestroy() {
        Log.i(DebugTag.TAG, "SqServiceNative ： onDestroy");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(DebugTag.TAG, "SqServiceNative ： onBind");
        return mSqServiceStub;
    }
}
