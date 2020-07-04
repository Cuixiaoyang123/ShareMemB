package com.dreamlost.sqservice.service;

import android.app.Application;
import android.os.MemoryFile;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;


import com.cxy.sqservice.ISqService;
import com.dreamlost.sqservice.constant.Data;
import com.dreamlost.sqservice.constant.DebugTag;
import com.dreamlost.sqservice.util.MemoryFileHelper;

import java.lang.reflect.Field;
import java.util.Arrays;

public class SqServiceStub extends ISqService.Stub {

    private static final String TAG = DebugTag.TAG;

    private byte[] mPackageContent;

    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

    }

    @Override
    public void dataFlow(ParcelFileDescriptor data, int length) throws RemoteException {
        Log.i(DebugTag.TAG, "SqServiceStub dataFlow ： onCreate pid = " + android.os.Process.myPid());
        try {
            long time = System.currentTimeMillis();
            createBufferIfNeed(length);
            MemoryFile memoryFile = MemoryFileHelper
                    .openMemoryFile(data, mPackageContent.length,
                            MemoryFileHelper.OPEN_READWRITE);
            int readNum = memoryFile.readBytes(mPackageContent, 0, 0, mPackageContent.length);
            if ((mPackageContent[0]&0x01) != 0x01) {
                Log.d(TAG, "传输数据发生错误");
            }else {
                memoryFile.writeBytes(new byte[]{0x00}, 0, 0, 1);
                memoryFile.close();
                Data.data = mPackageContent;
                Log.d(TAG, "匿名共享文件描述符: " + data.getFileDescriptor().toString());
                Log.d(TAG, "服务端读取Byte数组 : " + Arrays.toString(mPackageContent) + "数组长度：" + mPackageContent.length+"nums="+readNum
                        + "，耗费时间 : " + (System.currentTimeMillis() - time)+"ms");
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "dataFlow err = " + e.toString());
        }
    }

    private void createBufferIfNeed(int length) {
        if (length <= 0) {
            Log.d(TAG, "createBufferIfNeed length <= 0");
        }
        if (mPackageContent != null && mPackageContent.length == length) {
            return;
        }
        mPackageContent = new byte[length];
    }

//    private void inflateActivityXml() throws Exception {
//        Class mainClazz = Class.forName("com.dreamlost.sharememb.MainActivity");
//        Field message = mainClazz.getDeclaredField("message");
//        message.setAccessible(true);
//        message.set();
//
//    }
}
