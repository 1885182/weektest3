package com.bawei.yk.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bawei.yk.R;
import com.bawei.yk.bean.DataBean;
import com.bawei.yk.fragment.ShouyeFragment;

import java.util.List;

/**
 * @Author: zhang
 * @Date: 2019/4/4 9:11
 * @Description:
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    List<DataBean> list;
    Context context;
    ShouyeFragment fragment;
    int p;
    public MyAdapter(List<DataBean> list, Context context, ShouyeFragment shouyeFragment) {
        this.list = list;
        this.context = context;
        fragment = shouyeFragment;
    }
    setOnClick setOnClick;
    public void setOnItemClick(setOnClick setOnClick){
        this.setOnClick = setOnClick;
    }
    public interface setOnClick{
        public void onClick(int j);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.name.setText(list.get(i).getSellerName());
        viewHolder.checkBox.setChecked(list.get(i).isSelect());
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        viewHolder.recyclerView.setLayoutManager(layoutManager);
        final MyItemAdapter adapter = new MyItemAdapter(list.get(i).getList(),context,fragment);
        viewHolder.recyclerView.setAdapter(adapter);
        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.isCheck(i,-1,viewHolder.checkBox.isChecked());
            }
        });


        adapter.setOnItemClick(new MyItemAdapter.ItemOnClick() {
            @Override
            public void itemClick(int j) {
                setOnClick.onClick(j);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list.size() != 0){
            return list.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox checkBox;
        TextView name;
        RecyclerView recyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recyclerView2_id);
            checkBox = itemView.findViewById(R.id.check2_id);
            name = itemView.findViewById(R.id.name_id);
        }
    }
}
