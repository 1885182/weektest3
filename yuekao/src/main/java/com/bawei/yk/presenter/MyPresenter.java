package com.bawei.yk.presenter;

import com.bawei.yk.contro.MyInterface;
import com.bawei.yk.model.MyModel;

/**
 * @Author: zhang
 * @Date: 2019/4/4 8:51
 * @Description:
 */
public class MyPresenter<T> implements MyInterface.PresenterInterface {
    MyInterface.ModelInterface modelInterface;
    MyInterface.ViewInterface viewInterface;
    MyInterface.ViewInterface.LoginInterface loginInterface;
    MyInterface.ViewInterface.RegisterInterface registerInterface;
    MyInterface.ViewInterface.DetailInterface detailInterface;
    T tt;

    public MyPresenter(T tt) {
        modelInterface = new MyModel();
        this.tt = tt;
    }

    @Override
    public void toLogin(String phone,String pwd) {
        loginInterface = (MyInterface.ViewInterface.LoginInterface) tt;
        modelInterface.toPostRequest("http://172.17.8.100/small/user/v1/login", phone, pwd, new MyModel.MyCallBack() {
            @Override
            public void success(Object object) {
                loginInterface.showLogin((String) object);
            }
        });
    }

    @Override
    public void toRegister(String phone, String pwd) {
        registerInterface = (MyInterface.ViewInterface.RegisterInterface) tt;
        modelInterface.toPostRequest("http://172.17.8.100/small/user/v1/register", phone, pwd, new MyModel.MyCallBack() {
            @Override
            public void success(Object object) {
                registerInterface.showRegister((String) object);
            }
        });
    }

    @Override
    public void toModel() {
        viewInterface = (MyInterface.ViewInterface) tt;
        modelInterface.toGetRequest("http://172.17.8.100/ks/product/getCarts?uid=5", new MyModel.MyCallBack() {
            @Override
            public void success(Object object) {
                viewInterface.reFreDisplay(object);
            }
        },1);
    }

    @Override
    public void toDetailModel(String url) {
        detailInterface = (MyInterface.ViewInterface.DetailInterface) tt;
        modelInterface.toGetRequest(url, new MyModel.MyCallBack() {
            @Override
            public void success(Object object) {
                detailInterface.reFreDisplay(object);
            }
        },2);
    }

    @Override
    public void onDestroy() {
        if (viewInterface != null){
            viewInterface = null;
        }
    }
}
