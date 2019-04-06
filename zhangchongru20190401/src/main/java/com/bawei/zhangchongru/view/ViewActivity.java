package com.bawei.zhangchongru.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.bawei.zhangchongru.R;
import com.bawei.zhangchongru.adapter.MyAdapter;
import com.bawei.zhangchongru.adapter.MyFragmentAdapter;
import com.bawei.zhangchongru.bean.MyBean;
import com.bawei.zhangchongru.bean.Shop;
import com.bawei.zhangchongru.costom.MyInterface;
import com.bawei.zhangchongru.fragment.MyFragment;
import com.bawei.zhangchongru.fragment.ShowFragment;
import com.bawei.zhangchongru.fragment.TwoFragment;
import com.bawei.zhangchongru.presenter.MyPresenter;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity {
    ViewPager viewPager;
    RadioGroup radioGroup;
    List<Fragment> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        radioGroup = findViewById(R.id.radio);
        viewPager = findViewById(R.id.viewpage);
        list.add(new ShowFragment());
        list.add(new TwoFragment());
        list.add(new MyFragment());
        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(),list);
        viewPager.setAdapter(adapter);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb1:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.rb2:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.rb3:
                        viewPager.setCurrentItem(2);
                        break;
                }
            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                radioGroup.check(radioGroup.getChildAt(i).getId());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }


}
