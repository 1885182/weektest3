package com.bawei.zhangchongru.presenter;

import com.bawei.zhangchongru.costom.MyInterface;
import com.bawei.zhangchongru.model.MyModel;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhang
 * @Date: 2019/4/2 9:01
 * @Description:
 */
public class MyPresenter<T> implements MyInterface.PresenterInterface {
    MyInterface.ModelInterface modelInterface;
    MyInterface.ViewInterface viewInterface;
    T tt;

    public MyPresenter(T tt) {
        modelInterface = new MyModel();
        viewInterface = (MyInterface.ViewInterface) tt;
        this.tt = tt;
    }

    @Override
    public void toModel() {
        String dataUrl = "http://172.17.8.100/ks/product/getCarts?uid=5";
        modelInterface.toGetRequest(dataUrl,new MyModel.MyCallBack() {
            @Override
            public void success(Object object) {
                viewInterface.reFreDisplay(object);
            }
        });
    }

    @Override
    public void onDestroy() {
        if (viewInterface != null){
            viewInterface = null;
        }
    }
}
