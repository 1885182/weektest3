package com.bawei.zhangchongru.presenter;

import com.bawei.zhangchongru.costom.MyInterface;
import com.bawei.zhangchongru.model.MyModel;

/**
 * @Author: zhang
 * @Date: 2019/4/1 9:15
 * @Description:处理逻辑用来model层和View层交互
 */
public class MyPresenter<T> implements MyInterface.PresenterInterface {
    MyInterface.ViewInterface viewInterface;
    MyInterface.ModelInterface modelInterface;
    T tt;

    public MyPresenter(T tt) {
        viewInterface = (MyInterface.ViewInterface) tt;
        modelInterface = new MyModel();
        this.tt = tt;
    }

    @Override
    public void toModel() {
        modelInterface.toRequest("http://172.17.8.100/small/commodity/v1/commodityList", new MyModel.MyCallBack() {
            @Override
            public void success(Object obj) {
                viewInterface.reFreDisplay(obj);
            }
        });
    }
    //释放内存
    @Override
    public void onDestroy() {
        if (viewInterface != null){
            viewInterface = null;
        }
    }
}
