package com.bawei.zhangchongru.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawei.zhangchongru.R;
import com.bawei.zhangchongru.bean.MyBean;

import java.util.List;

/**
 * @Author: zhang
 * @Date: 2019/4/1 9:22
 * @Description:RecyclerView适配器多条目展示数据
 */
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<MyBean.ResultBean.ShopBean> list;
    Context context;


    public MyAdapter(List<MyBean.ResultBean.ShopBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return position%3;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (list.get(i).getName().equals("热销新品")){
            ((ViewHolder)viewHolder).name.setText(list.get(i).getName());
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            ((ViewHolder) viewHolder).recyclerView.setLayoutManager(layoutManager);
            MyItemAdapter adapter = new MyItemAdapter(list.get(i).getCommodityList(),context);
            ((ViewHolder)viewHolder).recyclerView.setAdapter(adapter);
        }else if (list.get(i).getName().equals("魔力时尚")){
            ((ViewHolder)viewHolder).name.setText(list.get(i).getName());
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            ((ViewHolder)viewHolder).recyclerView.setLayoutManager(layoutManager);
            MyItemAdapter1 adapter = new MyItemAdapter1(list.get(i).getCommodityList(),context);
            ((ViewHolder)viewHolder).recyclerView.setAdapter(adapter);
        }else {
            ((ViewHolder)viewHolder).name.setText(list.get(i).getName());
            GridLayoutManager layoutManager = new GridLayoutManager(context,2);
            ((ViewHolder) viewHolder).recyclerView.setLayoutManager(layoutManager);
            MyItemAdapter adapter = new MyItemAdapter(list.get(i).getCommodityList(),context);
            ((ViewHolder)viewHolder).recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        RecyclerView recyclerView;
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recycler_id);
            name = itemView.findViewById(R.id.name_id);
        }
    }
}
