package com.bawei.zhangchongru.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bawei.zhangchongru.R;
import com.bawei.zhangchongru.adapter.MyAdapter;
import com.bawei.zhangchongru.bean.DataBean;
import com.bawei.zhangchongru.bean.ListBean;
import com.bawei.zhangchongru.bean.MyBean;
import com.bawei.zhangchongru.costom.MyInterface;
import com.bawei.zhangchongru.presenter.MyPresenter;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity implements MyInterface.ViewInterface {
    MyInterface.PresenterInterface presenterInterface;
    RecyclerView recyclerView;
    List<DataBean> list = new ArrayList<>();
    MyAdapter adapter;
    public CheckBox check;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        recyclerView = findViewById(R.id.recyclerView_id);
        presenterInterface = new MyPresenter<>(this);
        check = findViewById(R.id.check_id);
        textView = findViewById(R.id.total_id);
        init();
    }

    private void init() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyAdapter(list,this);
        recyclerView.setAdapter(adapter);
        presenterInterface.toModel();
        //全选反选
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check.isChecked()){
                    setCheckAll(true);
                    total();
                }else {
                    setCheckAll(false);
                    total();
                }
            }
        });
    }
    //根据最外层CheckBox的选中状态,遍历改变第二层的选中状态
    private void setCheckAll(boolean b) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setSelect(b);
            //子集合
            for (int j = 0; j < list.get(i).getList().size(); j++) {
                list.get(i).getList().get(j).setSelects(b);
            }
        }
    }
    /**
     * @param i  商家的下标
     * @param tag  商家中孩子的下标，默认值位-1
     * @param b 是否为选中状态
     */
    //
    public void setCheckAll(int i,int tag,boolean b){
        if (tag != -1){
            list.get(i).getList().get(tag).setSelects(b);
            boolean flag = true;
            //遍历当前集合中的数据
            for (int j = 0; j < list.get(i).getList().size(); j++) {
                //如果没有selects值为false就使上一层CheckBox选中状态为true
                if (!list.get(i).getList().get(j).isSelects()){
                    flag = false;
                }
            }
            //商家的选中状态
            list.get(i).setSelect(flag);
        }else {
            list.get(i).setSelect(b);
            for (int j = 0; j < list.get(i).getList().size(); j++) {
                list.get(i).getList().get(j).setSelects(b);
            }
        }
        isCheck();
        total();
    }
    //当对里面的CheckBox进行操作时 判断最外层CheckBox是否选中
    public void isCheck(){
        boolean flag = true;
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).isSelect()){
                flag = false;
            }
        }
        check.setChecked(flag);
    }
    //计算总价
    public void total(){
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).getList().size(); j++) {
                if (list.get(i).getList().get(j).isSelects()){
                    int price = list.get(i).getList().get(j).getPrice();
                    int num = list.get(i).getList().get(j).getNum();
                    sum += price * num;
                }
            }
        }
        textView.setText(sum+"");
        adapter.notifyDataSetChanged();
    }

    /**
     *
     * @param b 最里层CheckBox点击后的选中状态
     * @param o 当前条目的上一层集合
     * @return
     */
    //获得商家的下标
    public int getValue(boolean b,Object o){
        ListBean listBean = (ListBean) o;
        int n = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).getList().size(); j++) {
                ListBean bean = list.get(i).getList().get(j);
                if (bean.equals(listBean)){
                    bean.setSelects(b);
                    n = i;
                }
            }
        }
        return n;
    }
    //获得数据
    @Override
    public void reFreDisplay(Object object) {
        MyBean bean = (MyBean) object;
        list.addAll(bean.getData());
        //list.remove(0);
        adapter.notifyDataSetChanged();
    }
    //内存释放
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenterInterface.onDestroy();
        presenterInterface = null;
    }
}
