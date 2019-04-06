package com.bawei.zhangchongru.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @Author: zhang
 * @Date: 2019/4/1 10:30
 * @Description:RecyclerView适配器 切换页面
 */
public class MyFragmentAdapter extends FragmentPagerAdapter {
    List<Fragment> list;

    public MyFragmentAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
