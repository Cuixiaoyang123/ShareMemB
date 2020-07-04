package com.dreamlost.sharememb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.dreamlost.sqservice.constant.Data;
import com.dreamlost.sqservice.service.SqServiceNative;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    private String message = "";
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.text);
        Intent intent =  new Intent(this, SqServiceNative.class);
        startService(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Data.data.length != 0) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
            Date now = new Date(System.currentTimeMillis());
            message = Arrays.toString(Data.data);
            text.setText(simpleDateFormat.format(now)+"\n接收到匿名共享内存的数据为\n"+message);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent =  new Intent(this, SqServiceNative.class);
        stopService(intent);
    }
}
