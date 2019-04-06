package com.bawei.zhangchongru.costom;

import com.bawei.zhangchongru.bean.MyBean;
import com.bawei.zhangchongru.model.MyModel;

import java.util.List;

/**
 * @Author: zhang
 * @Date: 2019/4/1 9:16
 * @Description:
 */
public interface MyInterface {
    //m层接口
    public interface ModelInterface{
        //获取请求到的数据
        public void toRequest(String url, MyModel.MyCallBack myCallBack);
    }
    //p层的接口
    public interface PresenterInterface{
        //调用m层的方法
        public void toModel();
        //释放内存
        public void onDestroy();
    }
    //v层接口
    public interface ViewInterface{
        public void reFreDisplay(Object obj);
    }
}
