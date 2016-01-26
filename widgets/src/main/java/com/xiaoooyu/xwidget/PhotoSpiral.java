package com.xiaoooyu.xwidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 *
 */
public class PhotoSpiral extends ViewGroup {

    public PhotoSpiral(Context context) {
        super(context);
    }

    public PhotoSpiral(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PhotoSpiral(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        View first = getChildAt(0);
        if (first != null) {
            int size = first.getMeasuredWidth() + first.getMeasuredHeight();
            int width = ViewGroup.resolveSize(size, widthMeasureSpec);
            int height = ViewGroup.resolveSize(size, heightMeasureSpec);
            setMeasuredDimension(width, height);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        // Assume all children are of the same dimensions
        // Order: landscape, portrait, landscape, portrait

        if (changed) {
            View first = getChildAt(0);

            if (first != null) {
                final int childWidth = first.getMeasuredWidth();
                final int childHeight = first.getMeasuredHeight();

                int x = childHeight;
                int y = 0;

                for (int i = 0; i < getChildCount(); i++) {
                    View child = getChildAt(i);

                    x = adjustX(i, x, childWidth, childHeight);
                    y = adjustY(i, y, childWidth, childHeight);
                    child.layout(x, y,
                            x + child.getMeasuredWidth(), y + child.getMeasuredHeight());
                }
            }
        }

//        super.onLayout();
    }

    private int adjustX(int pos, int x, int childWidth, int childHeight) {
        int delta = childWidth - childHeight;
        switch (pos) {
            case 1:
                x += delta;
                break;
            case 2:
                x -= childWidth;
                break;
        }
        return x;
    }

    private int adjustY(int pos, int y, int childWidth, int childHeight) {

        switch (pos) {
            case 1:
                y += childHeight;
                break;
            case 2:
                y += (childWidth - childHeight);
                break;
            case 3:
                y -= childWidth;
                break;
        }

        return y;
    }
}
