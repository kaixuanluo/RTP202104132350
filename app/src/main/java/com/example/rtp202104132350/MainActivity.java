package com.example.rtp202104132350;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

//    https://blog.csdn.net/bingqingsuimeng/article/details/50738240
public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate:"+ Arrays.toString(new Nalu().getF()));
        Log.d(TAG, "onCreate:"+ Arrays.toString(new Nalu().getNRI()));
    }
}
