package com.example.testlearning.costom;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.animation.AnimationUtils;

import com.example.testlearning.R;

public class MotionViewActivity extends AppCompatActivity {

    private CustomView mCustomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion_view);
        mCustomView = findViewById(R.id.customview);
       anim();
    }

    private void anim() {
//        mCustomView.setAnimation(AnimationUtils.loadAnimation(this,R.anim.translate));

//        ObjectAnimator.ofFloat(mCustomView,"translationX",0,300)
//                .setDuration(1000)
//                .start();

        mCustomView.smoothScrollTo(-400,-800);
}

}
