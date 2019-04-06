package com.bawei.zhangchongru.util;

import android.os.Handler;
import android.os.Message;
import android.util.Log;


import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author: zhang
 * @Date: 2019/4/2 9:09
 * @Description:
 */
public class OkHttpUtil {
    static OkHttpUtil okHttpUtil;
    OkHttpClient okHttpClient;
    private OkHttpUtil(){
        okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Log.i("tag",request.url().toString());
                Response proceed = chain.proceed(request);
                return proceed;
            }
        }).build();
    }
    public static synchronized OkHttpUtil InStance(){
        if (okHttpUtil == null){
            okHttpUtil = new OkHttpUtil();
        }
        return okHttpUtil;
    }
    public void doGet(String url, final Handler handler){
        Request request = new Request.Builder().get().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
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
}
