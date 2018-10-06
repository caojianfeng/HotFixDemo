package com.caojianfeng.hotfixdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvMsg = findViewById(R.id.tv_msg);
        tvMsg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String msg = "1/0=" + (1 / 0);
        tvMsg.setText(msg);
    }
}
