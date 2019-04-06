package com.bawei.yk.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * @Author: zhang
 * @Date: 2019/4/6 13:18
 * @Description:
 */
public class FlowLayout extends ViewGroup {
    int mLeft = 20;
    int mTop = 20;
    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left = mLeft;
        int top = mTop;
        for (int i = 0; i < getChildCount(); i++) {
            int width = getChildAt(i).getMeasuredWidth();
            int height = getChildAt(i).getMeasuredHeight();
            if (width + left + mLeft > getWidth()){
                left = mLeft;
                top += height + mTop;
                getChildAt(i).layout(left,top,left+width,top+height);
            }else {
                getChildAt(i).layout(left,top,left+width,top+height);
            }
            left += mLeft + width;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
