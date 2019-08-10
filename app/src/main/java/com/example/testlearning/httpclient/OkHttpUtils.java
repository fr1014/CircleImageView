package com.example.testlearning.httpclient;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpUtils {
    private static final String TAG = "OkHttpUtils";

    //异步GET请求
    public static void enqueueGet(String url){
        Request request = new Request.Builder()
                .url(url)
                .get() //默认是GET请求，可以不写
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            //回调不在UI线程
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String str = response.body().string();
                Log.d(TAG, str);
            }
        });

    }

    //异步POST请求
    public static void enqueuePost(){
        
    }
}
