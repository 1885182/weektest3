package com.bawei.zhangchongru.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.zhangchongru.R;
import com.bawei.zhangchongru.bean.ListBean;
import com.bawei.zhangchongru.view.ViewActivity;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * @Author: zhang
 * @Date: 2019/4/2 18:27
 * @Description:
 */
public class MyItemAdapter extends RecyclerView.Adapter<MyItemAdapter.ViewHolder> {
    List<ListBean> list;
    Context context;
    ViewActivity activity;
    public MyItemAdapter(List<ListBean> list, Context context) {
        this.list = list;
        this.context = context;
        activity = (ViewActivity) context;
    }
    //加载布局
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item1, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.title.setText(list.get(i).getTitle());
        viewHolder.price.setText(list.get(i).getPrice()+"");
        viewHolder.num.setText(list.get(i).getNum()+"");
        viewHolder.checkBox2.setChecked(list.get(i).isSelects());
        Glide.with(context).load(list.get(i).getImages()).into(viewHolder.image);
        //最里层CheckBox状态改变监听
        viewHolder.checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewHolder.checkBox2.isChecked() == false){
                    int n = activity.getValue(viewHolder.checkBox2.isChecked(),list.get(i));
                    activity.setCheckAll(n,i,viewHolder.checkBox2.isChecked());
                    activity.check.setChecked(false);
                }else {
                    int n = activity.getValue(viewHolder.checkBox2.isChecked() ,list.get(i) );
                    activity.setCheckAll(n , i ,viewHolder.checkBox2.isChecked());
                }
            }
        });
        //数量加
        viewHolder.jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = viewHolder.num.getText().toString();
                int j = Integer.parseInt(n);
                j++;
                list.get(i).setNum(j);
                activity.total();
            }
        });
        //数量减
        viewHolder.jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int j = Integer.parseInt(viewHolder.num.getText().toString());
                j--;
                if (j > 0){
                    list.get(i).setNum(j);
                    activity.total();
                }else {

                }
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
        TextView title,num,price;
        ImageView image;
        CheckBox checkBox2;
        Button jia,jian;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_id);
            num = itemView.findViewById(R.id.num_id);
            image = itemView.findViewById(R.id.image_id);
            price = itemView.findViewById(R.id.price_id);
            checkBox2 = itemView.findViewById(R.id.checkbox2_id);
            jia = itemView.findViewById(R.id.jia);
            jian = itemView.findViewById(R.id.jian);
        }
    }
}
