package com.bawei.yk.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.yk.R;
import com.bawei.yk.contro.MyInterface;
import com.bawei.yk.presenter.MyPresenter;

public class RegisterActivity extends AppCompatActivity implements MyInterface.ViewInterface.RegisterInterface, View.OnClickListener {
    EditText edit_phone,edit_pwd;
    Button register;
    TextView login,back;
    MyInterface.PresenterInterface presenterInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edit_phone = findViewById(R.id.reg_phone_id);
        edit_pwd = findViewById(R.id.reg_pwd_id);
        login = findViewById(R.id.login1_id);
        register = findViewById(R.id.register);
        back = findViewById(R.id.reg_back);
        presenterInterface = new MyPresenter<>(this);
        login.setOnClickListener(this);
        back.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void showRegister(String string) {
        Toast.makeText(this,string,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                String pwd = edit_pwd.getText().toString();
                String phone = edit_phone.getText().toString();
                presenterInterface.toRegister(phone,pwd);
                break;
            case R.id.reg_back:
                finish();
                break;
            case R.id.login1_id:
                Intent intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
