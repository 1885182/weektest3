package com.bawei.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class TitleActivity extends AppCompatActivity implements View.OnClickListener {
    TextView text_back,text_bia,text_ti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        text_back = findViewById(R.id.back_id);
        text_bia = findViewById(R.id.biaoti_id);
        text_ti = findViewById(R.id.tijiao_id);
        text_ti.setOnClickListener(this);
        text_bia.setOnClickListener(this);
        text_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_id:
                Toast.makeText(this,"返回监听",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tijiao_id:
                Toast.makeText(this,"我是提交按钮",Toast.LENGTH_SHORT).show();
                break;
            case R.id.biaoti_id:
                break;
        }
    }
}
