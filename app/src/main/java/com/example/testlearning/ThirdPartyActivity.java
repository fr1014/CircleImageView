package com.example.testlearning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.testlearning.utils.ToastUtil;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class ThirdPartyActivity extends AppCompatActivity {
    private static final String TAG = "MultWindow";
    private Button bt_call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        Log.d(TAG,"onCreate");
        bt_call = findViewById(R.id.bt_call);
        bt_call.setOnClickListener(view ->
                ThirdPartyActivityPermissionsDispatcher.callWithPermissionCheck(ThirdPartyActivity.this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    //注意：注解的方法不能是 private 的
    @NeedsPermission(Manifest.permission.CALL_PHONE)
    //在需要获取权限的地方注释
    void call(){
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:"+"13285698922");
        intent.setData(data);
        startActivity(intent);
    }

    @OnShowRationale(Manifest.permission.CALL_PHONE)
    //提示用户为何要开启权限
    void showWhy(final PermissionRequest request){
        new AlertDialog.Builder(this)
                .setMessage("提示用户为何要开启此权限")
                .setPositiveButton("知道了", (dialog, which) -> request.proceed())
                .show();
    }

    @OnPermissionDenied(Manifest.permission.CALL_PHONE)
    //用户拒绝时的提示
    void showDenied(){
        ToastUtil.toastShort(this,"用户拒绝时的提示");
    }

    @OnNeverAskAgain(Manifest.permission.CALL_PHONE)
    //用户选择不在询问后的提示
    void showNotAsk(){
        new AlertDialog.Builder(this)
                .setMessage("该功能需要访问电话的权限，不开启无法正常工作！")
                .setPositiveButton("确定",null)
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ThirdPartyActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
}
