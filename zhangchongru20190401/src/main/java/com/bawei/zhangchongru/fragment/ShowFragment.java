package com.bawei.zhangchongru.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bawei.zhangchongru.R;
import com.bawei.zhangchongru.adapter.MyAdapter;
import com.bawei.zhangchongru.bean.MyBean;
import com.bawei.zhangchongru.bean.Shop;
import com.bawei.zhangchongru.costom.MyInterface;
import com.bawei.zhangchongru.presenter.MyPresenter;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhang
 * @Date: 2019/4/1 10:19
 * @Description:view层展示数据 banner轮播
 */
public class ShowFragment extends Fragment implements MyInterface.ViewInterface {
    MyInterface.PresenterInterface presenterInterface;
    Banner banner;
    RecyclerView recyclerView;
    List<String> blist = new ArrayList<>();
    List<MyBean.ResultBean.ShopBean> list = new ArrayList<>();
    MyAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show, container, false);
        banner = view.findViewById(R.id.banner_id);
        recyclerView = view.findViewById(R.id.recyclerView_id);
        presenterInterface = new MyPresenter<>(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        init();
        setBanner();
    }
    private void init() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyAdapter(list,getActivity());
        recyclerView.setAdapter(adapter);
        presenterInterface.toModel();
    }

    private void setBanner() {
        blist.add("http://172.17.8.100/images/small/banner/cj.png");
        blist.add("http://172.17.8.100/images/small/banner/hzp.png");
        blist.add("http://172.17.8.100/images/small/banner/lyq.png");
        blist.add("http://172.17.8.100/images/small/banner/px.png");
        banner.isAutoPlay(true).setDelayTime(2000).setImages(blist).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        }).start();
    }

    @Override
    public void reFreDisplay(Object obj) {
        MyBean bean = (MyBean) obj;
        list.add(bean.getResult().getMlss());
        list.add(bean.getResult().getPzsh());
        list.add(bean.getResult().getRxxp());
        adapter.notifyDataSetChanged();
    }
    //释放内存
    @Override
    public void onDestroy() {
        super.onDestroy();
        presenterInterface.onDestroy();
        presenterInterface = null;
    }
}
