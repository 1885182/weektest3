package com.bawei.yk.util;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author: zhang
 * @Date: 2019/4/4 8:52
 * @Description:
 */
public class OkHttpUtil {
    static OkHttpUtil okHttpUtil;
    OkHttpClient okHttpClient;

    private OkHttpUtil() {
        okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response proceed = chain.proceed(request);
                Log.i("tag",request.url().toString());
                return proceed;
            }
        }).build();
    }
    public static synchronized OkHttpUtil getInstance(){
        if (okHttpUtil == null){
            okHttpUtil = new OkHttpUtil();
        }
        return okHttpUtil;
    }
    public void doGet(String url, final Handler handler, final int i){
        Request request = new Request.Builder().url(url).get().build();
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
                message.arg1 = i;
                handler.sendMessage(message);
            }
        });
    }
    public void doPost(String url, String phone, String pwd, Callback myCallBack){

        FormBody.Builder builder = new FormBody.Builder();
        builder.add("phone",phone);
        builder.add("pwd",pwd);
        FormBody body = builder.build();
        Request request = new Request.Builder().post(body).url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(myCallBack);
    }
}
