package com.bawei.zhangchongru.costom;

import com.bawei.zhangchongru.model.MyModel;

import java.util.Map;

/**
 * @Author: zhang
 * @Date: 2019/4/2 8:53
 * @Description:
 */
public interface MyInterface {
    interface ModelInterface{
        void toGetRequest(String url, MyModel.MyCallBack myCallBack);
    }
    interface PresenterInterface{
        void toModel();
        void onDestroy();
    }
    interface ViewInterface{
        void reFreDisplay(Object object);
    }
}
