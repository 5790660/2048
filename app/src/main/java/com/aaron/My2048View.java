package com.aaron;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 创宇 on 2016/7/19.
 */
public class My2048View extends ViewGroup{

    //每行显示的方块数量
    public static final int NUM_PER_LINE = 4;

    //分割线
    public int divider = 5;

    public My2048View(Context context) {
        super(context);
    }

    public My2048View(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public My2048View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(widthMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int length = getMeasuredWidth() / NUM_PER_LINE;
        Rect rect = new Rect();

        for (int i = 0; i < childCount; i ++) {
            View child = getChildAt(i);
            rect.left = (i % NUM_PER_LINE) * length + divider;
            rect.right = rect.left + length - divider * 2;
            rect.top = i / NUM_PER_LINE * length + divider;
            rect.bottom = rect.top + length - divider * 2;
            child.layout(rect.left, rect.top, rect.right, rect.bottom);
        }
    }
}
