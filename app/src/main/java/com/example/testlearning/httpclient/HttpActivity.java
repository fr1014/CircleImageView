package com.example.testlearning.httpclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.testlearning.R;

public class HttpActivity extends AppCompatActivity implements View.OnClickListener{
    private Button bt_get;
    private HttpUtils mHttpUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);
        bt_get = findViewById(R.id.bt_get);
        bt_get.setOnClickListener(this);
        mHttpUtils = new HttpUtils();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_get:
                get();
                break;
        }
    }

    private void get() {
        new Thread(() -> mHttpUtils.useHttpClientGet("http://www.baidu.com")).start();
    }
}
