package com.bawei.yk.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.bawei.yk.R;
import com.bawei.yk.bean.DetailBean;
import com.bawei.yk.contro.MyInterface;
import com.bawei.yk.presenter.MyPresenter;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity implements MyInterface.ViewInterface.DetailInterface {
    MyInterface.PresenterInterface presenterInterface;
    List<DetailBean.DataBean> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang);
        presenterInterface = new MyPresenter<>(this);
        int j = getIntent().getIntExtra("j",1);
        Log.i("tag",j+"qq");
        String url = "http://172.17.8.100/ks/product/getProductDetail?pid="+j;
        presenterInterface.toDetailModel(url);

    }

    @Override
    public void reFreDisplay(Object object) {
        DetailBean bean = (DetailBean) object;
        list.add(bean.getData());
    }
}
