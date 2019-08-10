package com.example.testlearning.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testlearning.R;
import com.example.testlearning.utils.ToastUtil;

import java.lang.ref.WeakReference;

import okhttp3.internal.http2.Http2Reader;

public class HandlerActivity extends AppCompatActivity {

    private TextView mTextView;
    private ImageView imageView;
    @SuppressLint("HandlerLeak")
    private MyHandler myHandler = new MyHandler(this);
    private int images[] = {R.drawable.image1, R.drawable.image2, R.drawable.image3};
    private int index;
    private MyRunnable myRunnable = new MyRunnable();


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(new Handler.Callback() {
        //拦截handle消息,当返回值为true时，void handleMessage(Message msg)不执行
        @Override
        public boolean handleMessage(Message msg) {
            return false;
        }
    }){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    private static class MyHandler extends Handler {
        private WeakReference<Activity> reference;

        public MyHandler(Activity activity) {
            reference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (reference.get() != null) {
                switch (msg.what) {
                    case 0:
                        ToastUtil.toastShort(reference.get(),""+0);
                        break;
                }
            }
        }
    }

    private class MyRunnable implements Runnable {

        @Override
        public void run() {
            index++;
            index = index % 3;
            imageView.setImageResource(images[index]);
            myHandler.postDelayed(myRunnable, 1000);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        mTextView = findViewById(R.id.textview);
        imageView = findViewById(R.id.imageview);

//        postOne();
//        postTwo();
        postThree();
    }

    private void postThree() {
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
//                    Message message = new Message();
                    Message message = myHandler.obtainMessage();
                    message.what = 0;
                    myHandler.sendMessage(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void postTwo() {
        myHandler.postDelayed(myRunnable, 1000);
    }

    private void postOne() {
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    myHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mTextView.setText("textview update");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
