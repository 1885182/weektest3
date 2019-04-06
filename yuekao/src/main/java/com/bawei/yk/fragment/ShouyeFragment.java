package com.bawei.yk.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.yk.R;
import com.bawei.yk.adapter.MyAdapter;
import com.bawei.yk.bean.DataBean;
import com.bawei.yk.bean.ListBean;
import com.bawei.yk.bean.MyBean;
import com.bawei.yk.contro.MyInterface;
import com.bawei.yk.presenter.MyPresenter;
import com.bawei.yk.view.DetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhang
 * @Date: 2019/4/4 8:42
 * @Description:
 */
public class ShouyeFragment extends Fragment implements MyInterface.ViewInterface {
    MyInterface.PresenterInterface presenterInterface;
    RecyclerView recyclerView;
    List<DataBean> list = new ArrayList<>();
    MyAdapter adapter;
    CheckBox check;
    TextView total;
    TextView shu;
    String url = "http://172.17.8.100/ks/product/getCarts?uid=5";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shouye, container, false);
        recyclerView = view.findViewById(R.id.recyclerView1_id);
        check = view.findViewById(R.id.check1_id);
        total = view.findViewById(R.id.total_id);
        shu = view.findViewById(R.id.shuliang);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenterInterface = new MyPresenter<>(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyAdapter(list,getActivity(),this);
        recyclerView.setAdapter(adapter);
        presenterInterface.toModel();
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
        adapter.setOnItemClick(new MyAdapter.setOnClick() {
            @Override
            public void onClick(int j) {
                Intent intent = new Intent(getActivity(),DetailActivity.class);
                intent.putExtra("j",j);
                Log.i("tag",j+"");
                startActivity(intent);
            }
        });
    }

    private void setCheckAll(boolean b) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setSelect(b);
            for (int j = 0; j < list.get(i).getList().size(); j++) {
                list.get(i).getList().get(j).setSelects(b);
            }
        }
    }
    //总价
    public void total(){
        int sum = 0;
        int shuliang = 0;
        for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < list.get(i).getList().size(); j++) {
                if (list.get(i).getList().get(j).isSelects()){
                    int price = list.get(i).getList().get(j).getPrice();
                    int num = list.get(i).getList().get(j).getNum();
                    sum += price*num;
                    shuliang++;
                }
            }
        }
        shu.setText(shuliang+"");
        total.setText(sum+"");
        adapter.notifyDataSetChanged();
    }

    public void isCheck(int i,int tag,boolean b){
        if (tag == -1){
            list.get(i).setSelect(b);
            for (int j = 0; j < list.get(i).getList().size(); j++) {
                list.get(i).getList().get(j).setSelects(b);
            }
        }else {
            boolean flag = true;
            for (int j = 0; j < list.get(i).getList().size(); j++) {
                if (!list.get(i).getList().get(j).isSelects()){
                    flag = false;
                }
            }
            list.get(i).setSelect(flag);
        }
        checkType();
        total();
    }

    private void checkType() {
        boolean flag = true;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).getList().size(); j++) {
                if (!list.get(i).getList().get(j).isSelects()){
                    flag = false;
                }
            }
        }
        check.setChecked(flag);
    }

    public int getValue(boolean b,Object o){
        int n = 0;
        ListBean bean = (ListBean) o;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).getList().size(); j++) {
                if (bean.equals(list.get(i).getList().get(j))){
                    n = i;
                    bean.setSelects(b);
                }
            }
        }
        return n;
    }
    @Override
    public void reFreDisplay(Object object) {
        MyBean bean = (MyBean) object;
        list.addAll(bean.getData());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenterInterface.onDestroy();
        presenterInterface = null;
    }
}
