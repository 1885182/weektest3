package com.bawei.yk.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.bawei.yk.bean.DetailBean;
import com.bawei.yk.bean.MyBean;
import com.bawei.yk.contro.MyInterface;
import com.bawei.yk.util.OkHttpUtil;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @Author: zhang
 * @Date: 2019/4/4 8:51
 * @Description:
 */
public class MyModel implements MyInterface.ModelInterface {
    MyCallBack myCallBack;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String json = (String) msg.obj;
            if (msg.arg1 == 2){
                Gson gson = new Gson();
                DetailBean bean = gson.fromJson(json,DetailBean.class);
                myCallBack.success(bean);
            }else if (msg.arg1 == 1){
                Gson gson = new Gson();
                MyBean bean = gson.fromJson(json,MyBean.class);
                myCallBack.success(bean);
            }else {
                try {
                    JSONObject object = new JSONObject(json);
                    String string = object.getString("message");
                    myCallBack.success(string);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                myCallBack.success(json);
            }
        }
    };
    @Override
    public void toGetRequest(String url, MyCallBack myCallBack,int i) {
        this.myCallBack = myCallBack;
        OkHttpUtil.getInstance().doGet(url,handler,i);
    }

    @Override
    public void toDetailRequest(String url, MyCallBack myCallBack,int i) {
        this.myCallBack = myCallBack;
        OkHttpUtil.getInstance().doGet(url, handler,i);
    }


    @Override
    public void toPostRequest(String url, String phone, String pwd, MyCallBack myCallBack) {
        this.myCallBack = myCallBack;
        OkHttpUtil.getInstance().doPost(url, phone, pwd, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Message message = new Message();
                message.obj = json;
                handler.sendMessage(message);
            }
        });
    }

    public interface MyCallBack{
        void success(Object object);
    }
}
