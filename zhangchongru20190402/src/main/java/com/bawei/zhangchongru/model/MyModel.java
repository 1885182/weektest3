package com.bawei.zhangchongru.model;

import android.os.Handler;
import android.os.Message;

import com.bawei.zhangchongru.bean.MyBean;
import com.bawei.zhangchongru.costom.MyInterface;
import com.bawei.zhangchongru.util.OkHttpUtil;
import com.google.gson.Gson;

import java.util.Map;

/**
 * @Author: zhang
 * @Date: 2019/4/2 8:57
 * @Description:
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
    public void toGetRequest(String url,MyCallBack myCallBack) {
        this.myCallBack = myCallBack;
        OkHttpUtil.InStance().doGet(url,handler);
    }

    public interface MyCallBack{
        void success(Object object);
    }
}
