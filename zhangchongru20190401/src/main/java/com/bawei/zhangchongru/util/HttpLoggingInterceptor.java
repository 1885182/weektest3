package com.bawei.zhangchongru.util;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @Author: zhang
 * @Date: 2019/4/1 11:28
 * @Description:
 */
public class HttpLoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        long start = System.nanoTime();
        Log.i("tag",String.format("发送请求 %s on %s%n%s",
                request.url(),
                chain.connection(),
                request.headers()));
        Response response = chain.proceed(request);
        long end = System.nanoTime();
        ResponseBody responseBody = response.peekBody(1024*1024);
        Log.i("tag",String.format("接收响应: [%s] %n返回json:【%s】 %.1fms%n%s",
                response.request().url(),
                responseBody.string(),
                (end - start) / 1e6d,
                response.headers()));
        return response;
    }
}
