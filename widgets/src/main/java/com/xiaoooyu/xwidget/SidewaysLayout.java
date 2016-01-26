package com.xiaoooyu.xwidget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 *
 */
public class SidewaysLayout extends LinearLayout {
    public SidewaysLayout(Context context) {
        super(context);
    }

    public SidewaysLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(0, getHeight());
        canvas.rotate(-90);
        super.dispatchDraw(canvas);
        canvas.restore();
    }
}
