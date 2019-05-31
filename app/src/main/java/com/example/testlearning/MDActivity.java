package com.example.testlearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.testlearning.utils.ToastUtil;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MDActivity extends AppCompatActivity {
    //    private ConstraintLayout md_activity;
//    private Button bt_snackbar;
    private TextInputLayout tl_username;
    private TextInputLayout tl_password;
    private Button bt_login;
    private static final String EMAIL_PATTEREN =
            "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),;:<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTEREN);
    private Matcher matcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_textinput);
//        md_activity = findViewById(R.id.md_activity);
//        bt_snackbar = findViewById(R.id.snackbar);
//        bt_snackbar.setOnClickListener(view->{
//            showSnackbar();
//        });
        tl_username = findViewById(R.id.tl_username);
        tl_password = findViewById(R.id.tl_password);
        bt_login = findViewById(R.id.bt_login);
        bt_login.setOnClickListener(view-> login());
    }

    private void login() {
        String username = tl_username.getEditText().getText().toString();
        String password = tl_password.getEditText().getText().toString();
        if (!validateUsername(username)){
            tl_username.setErrorEnabled(true);
            tl_username.setError("请输入正确的邮箱地址");
        }else if (!validatePassword(password)){
            tl_password.setErrorEnabled(true);
            tl_password.setError("密码字数过少");
        }else {
            tl_username.setErrorEnabled(false);
            tl_password.setErrorEnabled(false);
            ToastUtil.toastShort(this,"登录成功");
        }
    }

    private boolean validateUsername(String name){
        matcher = pattern.matcher(name);
        return matcher.matches();
    }

    private boolean validatePassword(String password) {
        return password.length() > 6;
    }

//    private void showSnackbar(){
//        Snackbar.make(md_activity,"标题",Snackbar.LENGTH_LONG)
//                .setAction("点击事件", v -> {
//                    ToastUtil.toastShort(this,"我被点了");
//                }).setDuration(2000)
//                .show();
//    }
}
