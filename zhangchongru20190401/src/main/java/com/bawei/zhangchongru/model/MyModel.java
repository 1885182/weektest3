package com.bawei.zhangchongru.model;

import android.os.Handler;
import android.os.Message;

import com.bawei.zhangchongru.bean.MyBean;
import com.bawei.zhangchongru.costom.MyInterface;
import com.bawei.zhangchongru.util.OkHttpUtil;
import com.google.gson.Gson;

/**
 * @Author: zhang
 * @Date: 2019/4/1 9:15
 * @Description:获取数据处理数据
 */
public class MyModel implements MyInterface.ModelInterface {
    MyCallBack myCallBack;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String json = (String) msg.obj;
            Gson gson = new Gson();
            MyBean bean = gson.fromJson(json,MyBean.class);
            myCallBack.success(bean);
        }
    };
    @Override
    public void toRequest(String url,MyCallBack myCallBack) {
        this.myCallBack = myCallBack;
        OkHttpUtil.getInstance().doGet(url,handler);
    }
    public interface MyCallBack{
        public void success(Object obj);
    }
}
