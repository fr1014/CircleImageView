package com.example.testlearning.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.testlearning.R;

public class TransferActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt_transfer;
    private EditText et_from;
    private EditText et_to;
    private EditText et_amout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);
        bt_transfer = findViewById(R.id.transfer);
        et_from = findViewById(R.id.from);
        et_to = findViewById(R.id.to);
        et_amout = findViewById(R.id.amout);
        bt_transfer.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.transfer:
                    int from = Integer.parseInt(et_from.getText().toString());
                    int to = Integer.parseInt(et_to.getText().toString());
                    int amout = Integer.parseInt(et_amout.getText().toString());
                    TransferRunnable runnable = new TransferRunnable(from,to,amout);
                    Thread thread = new Thread(runnable);
                    thread.start();
                    break;
            }
    }
}
