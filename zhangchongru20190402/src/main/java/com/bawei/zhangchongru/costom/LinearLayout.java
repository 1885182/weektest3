package com.bawei.zhangchongru.costom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bawei.zhangchongru.R;
import com.bawei.zhangchongru.bean.DataBean;
import com.bawei.zhangchongru.view.ViewActivity;

import java.util.List;

/**
 * @Author: zhang
 * @Date: 2019/4/2 19:13
 * @Description:
 */
public class LinearLayout extends android.widget.LinearLayout {

    TextView num;
    Button jia,jian;
    ViewActivity activity;
    public LinearLayout(Context context) {
        super(context);
        activity = (ViewActivity) context;
    }

    public LinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.linearlayout, this);
        num = view.findViewById(R.id.num_id);
        jia = view.findViewById(R.id.jia);
        jian = view.findViewById(R.id.jian);

    }

    public LinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    List<DataBean> list = null;

    public void setList(List<DataBean> list) {
        this.list = list;
    }
}
