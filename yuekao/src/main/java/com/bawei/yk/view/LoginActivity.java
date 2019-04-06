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

public class LoginActivity extends AppCompatActivity implements MyInterface.ViewInterface.LoginInterface, View.OnClickListener {
    EditText edit_phone,edit_pwd;
    Button login;
    TextView register,back;
    MyInterface.PresenterInterface presenterInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edit_phone = findViewById(R.id.phone_id);
        edit_pwd = findViewById(R.id.pwd_id);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register_id);
        back = findViewById(R.id.login_back);
        presenterInterface = new MyPresenter<>(this);
        login.setOnClickListener(this);
        back.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void showLogin(String string) {
        Toast.makeText(LoginActivity.this,string,Toast.LENGTH_LONG).show();
        if (string.equals("登录成功")){
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                String pwd = edit_pwd.getText().toString();
                String phone = edit_phone.getText().toString();
                presenterInterface.toLogin(phone,pwd);
                break;
            case R.id.login_back:
                finish();
                break;
            case R.id.register_id:
                Intent intent = new Intent(this,RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
