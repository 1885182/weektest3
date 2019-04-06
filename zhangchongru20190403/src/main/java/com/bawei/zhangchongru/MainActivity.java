package com.bawei.zhangchongru;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView clea,jian,qing;
    Button tui;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clea = findViewById(R.id.clear_id);
        jian = findViewById(R.id.jiance_id);
        tui = findViewById(R.id.tui_id);
        qing = findViewById(R.id.qing_id);
        qing.setOnClickListener(this);
        tui.setOnClickListener(this);
        jian.setOnClickListener(this);
        clea.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tui_id:
                String t = tui.getText().toString();
                if (t.equals("开")){
                    tui.setText("关");
                }else {
                    tui.setText("开");
                }
                break;
            case R.id.jiance_id:
                Toast.makeText(this,"当前已是最新版本",Toast.LENGTH_SHORT).show();
                break;
            case R.id.clear_id:
                Toast.makeText(this,"缓存已清理",Toast.LENGTH_SHORT).show();
                break;
            case R.id.qing_id:
                String q = qing.getText().toString();
                if (q.equals("高清")){
                    qing.setText("超清");
                }else if (q.equals("超清")){
                    qing.setText("标准");
                }else {
                    qing.setText("高清");
                }
                break;
        }
    }
}
