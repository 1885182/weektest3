package com.bawei.yk.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.yk.MainActivity;
import com.bawei.yk.R;
import com.bawei.yk.bean.ListBean;
import com.bawei.yk.fragment.ShouyeFragment;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * @Author: zhang
 * @Date: 2019/4/4 9:30
 * @Description:
 */
public class MyItemAdapter extends RecyclerView.Adapter<MyItemAdapter.ViewHolder> {
    List<ListBean> list;
    Context context;
    ShouyeFragment fragment;
    public MyItemAdapter(List<ListBean> list, Context context, ShouyeFragment fragment) {
        this.list = list;
        this.context = context;
        this.fragment = fragment;
    }
    ItemOnClick itemOnClick;
    public void setOnItemClick(ItemOnClick onItemClick){
        this.itemOnClick = onItemClick;
    }
    public interface ItemOnClick{
        void itemClick(int i);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.title.setText(list.get(i).getTitle());
        viewHolder.price.setText(list.get(i).getPrice()+"");
        Glide.with(context).load(list.get(i).getImages()).into(viewHolder.image);
        viewHolder.num.setText(list.get(i).getNum()+"");
        viewHolder.checkBox.setChecked(list.get(i).isSelects());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemOnClick.itemClick(list.get(i).getPid());
            }
        });
        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(i).isSelects()){
                    int n = fragment.getValue(viewHolder.checkBox.isChecked(), list.get(i));
                    fragment.isCheck(n,i,viewHolder.checkBox.isChecked());
                }else {
                    int n = fragment.getValue(viewHolder.checkBox.isChecked(), list.get(i));
                    fragment.isCheck(n,i,viewHolder.checkBox.isChecked());
                }
            }
        });
        viewHolder.jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = viewHolder.num.getText().toString();
                int m = Integer.parseInt(string);
                m++;
                list.get(i).setNum(m);
                fragment.total();
            }
        });
        viewHolder.jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = viewHolder.num.getText().toString();
                int m = Integer.parseInt(string);
                m--;
                if (m > 0){
                    list.get(i).setNum(m);
                    fragment.total();
                }else {
                    Toast.makeText(context,"商品数量最低为1",Toast.LENGTH_LONG).show();
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
        CheckBox checkBox;
        TextView price,title,num;
        Button jia,jian;
        ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.check3_id);
            price = itemView.findViewById(R.id.price_id);
            title = itemView.findViewById(R.id.title_id);
            num = itemView.findViewById(R.id.num);
            jia = itemView.findViewById(R.id.jia);
            jian = itemView.findViewById(R.id.jian);
            image = itemView.findViewById(R.id.image_id);
        }
    }
}
