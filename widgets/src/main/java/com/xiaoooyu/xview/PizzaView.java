package com.xiaoooyu.xview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

import com.xiaoooyu.xwidget.R;

/**
 *
 */
public class PizzaView extends SquareView {

    private static final int DEFAULT_STROKE_WIDTH = 2;
    private static final int DEFAULT_NUM_WEDGES = 5;
    private static final int DEFAULT_COLOR = Color.YELLOW;

    private int mStrokeWidth = DEFAULT_STROKE_WIDTH;
    private int mStrokeColor = DEFAULT_COLOR;
    private int mNumWedges = DEFAULT_NUM_WEDGES;
    private Paint mPaint;

    public PizzaView(Context context) {
        super(context);
        init(null);
    }

    public PizzaView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public PizzaView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.PizzaView_Styleable);

            mStrokeWidth = array.getLayoutDimension(R.styleable.PizzaView_Styleable_stroke_width, mStrokeWidth);
            mStrokeColor = array.getColor(R.styleable.PizzaView_Styleable_stroke_color, mStrokeColor);
            mNumWedges = array.getInteger(R.styleable.PizzaView_Styleable_num_wedges, mNumWedges);
        }

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        mPaint = new Paint();
        mPaint.setColor(mStrokeColor);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final int width = getWidth() - getPaddingLeft() - getPaddingRight() - mStrokeWidth;
        final int height = getHeight() - getPaddingTop() - getPaddingBottom() - mStrokeWidth;
        final int diameter = Math.min(width, height);
        final int cx = width / 2 + getPaddingLeft() + ( mStrokeWidth / 2 );
        final int cy = height / 2 + getPaddingTop() + ( mStrokeWidth / 2 );

        final int radius = diameter / 2;

        canvas.drawCircle(cx, cy, radius, mPaint);

        drawPizzaCuts(canvas, cx, cy, radius);
    }

    private void drawPizzaCuts(Canvas canvas, int cx, int cy, int radius) {
        canvas.save();

        final float degree = 360f / mNumWedges;
        for (int i = 0; i < mNumWedges; i++) {
            canvas.rotate(degree, cx, cy);
            canvas.drawLine(cx, cy, cx, cy - radius, mPaint);
        }

        canvas.restore();
    }
}
