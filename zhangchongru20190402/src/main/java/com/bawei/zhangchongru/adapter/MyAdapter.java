package com.bawei.zhangchongru.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bawei.zhangchongru.R;
import com.bawei.zhangchongru.bean.DataBean;
import com.bawei.zhangchongru.view.ViewActivity;

import java.util.List;

/**
 * @Author: zhang
 * @Date: 2019/4/2 9:09
 * @Description:
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    List<DataBean> list;
    Context context;
    ViewActivity activity;
    public MyAdapter(List<DataBean> list, Context context) {
        this.list = list;
        this.context = context;
        activity = (ViewActivity) context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.name.setText(list.get(i).getSellerName());
        viewHolder.checkBox1.setChecked(list.get(i).isSelect());
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        viewHolder.recyclerView.setLayoutManager(layoutManager);
        final MyItemAdapter adapter = new MyItemAdapter(list.get(i).getList(),context);
        viewHolder.recyclerView.setAdapter(adapter);
        //第二层CheckBox改变状态监听,
        viewHolder.checkBox1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用activity方法使子CheckBox改变
                activity.setCheckAll(i,-1,viewHolder.checkBox1.isChecked());
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
        TextView name;
        public CheckBox checkBox1;
        RecyclerView recyclerView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_id);
            checkBox1 = itemView.findViewById(R.id.checkbox1_id);
            recyclerView = itemView.findViewById(R.id.recycler_id);
        }
    }
}
