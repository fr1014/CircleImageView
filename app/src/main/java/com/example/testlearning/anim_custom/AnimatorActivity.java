package com.example.testlearning.anim_custom;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.testlearning.R;

public class AnimatorActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mImageView;
    private Button translationX;
    private Button translationY;
    private Button rotation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);
        mImageView = findViewById(R.id.im_anim);
        translationX = findViewById(R.id.translationX);
        translationY = findViewById(R.id.translationY);
        rotation = findViewById(R.id.rotation);
        translationX.setOnClickListener(this);
        translationY.setOnClickListener(this);
        rotation.setOnClickListener(this);

        anim();
    }

    private void anim() {
        Animator animator = AnimatorInflater.loadAnimator(this,R.animator.rotation);
        animator.setTarget(mImageView);
        animator.start();
    }

//    private void anim() {
//        ObjectAnimator animator1 = ObjectAnimator.ofFloat(mImageView,"translationX",0.0f,200.0f,0f);
//        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mImageView,"scaleX",1.0f,2.0f);
//        ObjectAnimator animator3 = ObjectAnimator.ofFloat(mImageView,"scaleY",1.0f,2.0f);
//        ObjectAnimator animator4 = ObjectAnimator.ofFloat(mImageView,"rotationX",0.0f,360.f,0.0f);
//        AnimatorSet set = new AnimatorSet();
//        set.setDuration(3000);
//        set.play(animator4).with(animator1).after(animator2).after(animator3);
//        set.start();
//    }


//    private void anim() {
//        ValueAnimator mValueAnimator = ValueAnimator.ofFloat(0,100);
//        mValueAnimator.setTarget(mImageView);
//        mValueAnimator.setDuration(3000).start();
//        mValueAnimator.addUpdateListener(animation -> {
//            Float mFloat = (Float) animation.getAnimatedValue();
//            translationX.setText(mFloat+"");
//        });
//        mValueAnimator.start();
//    }

    private void animTranslationX() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mImageView, "rotationX", 180).setDuration(3000);
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                translationX.setText("finish");
            }
        });
        objectAnimator.start();
    }

    private void animTranslationY() {
        ObjectAnimator.ofFloat(mImageView, "rotationY", 180)
                .setDuration(3000)
                .start();
    }

    private void animRotation() {
        ObjectAnimator.ofFloat(mImageView, "rotation", 180)
                .setDuration(3000)
                .start();
    }


    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.translationX:
//                animTranslationX();
//                break;
//            case R.id.translationY:
//                animTranslationY();
//                break;
//            case R.id.rotation:
//                animRotation();
//                break;
//        }
    }


}
