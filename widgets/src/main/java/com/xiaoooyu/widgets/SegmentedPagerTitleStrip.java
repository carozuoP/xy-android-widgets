package com.xiaoooyu.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 *
 */
public class SegmentedPagerTitleStrip extends LinearLayout {
    private static final int DEFAULT_BOTTOM_BORDER_THICKNESS_DIPS = 2;
    private static final byte DEFAULT_BOTTOM_BORDER_COLOR_ALPHA = 0x26;
    private static final int SELECTED_INDICATOR_THICKNESS_DIPS = 8;
    private static final int DEFAULT_SELECTED_INDICATOR_COLOR = 0xFFD6BC84;
    private static final int DEFAULT_COLOR = 0xFFD6BC84;
    private static final int SELECTED_TEXT_COLOR = 0xFFFFFFFF;


    private static final int DEFAULT_DIVIDER_THICKNESS_DIPS = 1;
    private static final byte DEFAULT_DIVIDER_COLOR_ALPHA = 0x20;
    private static final float DEFAULT_DIVIDER_HEIGHT = 0.5f;
    private final static int DEFAULT_BORDER_THICKNESS_DIPS = 1;

    private final int mBottomBorderThickness;
    private final Paint mBottomBorderPaint;

    private final int mSelectedIndicatorThickness;
    private final Paint mSelectedIndicatorPaint;

    private final int mDefaultBottomBorderColor;

    private final Paint mDividerPaint;
    private final float mDividerHeight;
    private final int mRadius;
    private final Paint mBorderPaint;


    private int mSelectedPosition;
    private float mSelectionOffset;

    private static final int DEFAULT_RADIUS = 6;

    public SegmentedPagerTitleStrip(Context context) {
        this(context, null);
    }

    public SegmentedPagerTitleStrip(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);

        final float density = getResources().getDisplayMetrics().density;

        TypedValue outValue = new TypedValue();
        context.getTheme().resolveAttribute(android.R.attr.colorForeground, outValue, true);
        final int themeForegroundColor =  outValue.data;

        mDefaultBottomBorderColor = setColorAlpha(themeForegroundColor,
                DEFAULT_BOTTOM_BORDER_COLOR_ALPHA);

//        mDefaultTabColorizer = new SimpleTabColorizer();
//        mDefaultTabColorizer.setIndicatorColors(DEFAULT_SELECTED_INDICATOR_COLOR);
//        mDefaultTabColorizer.setDividerColors(setColorAlpha(themeForegroundColor,
//                DEFAULT_DIVIDER_COLOR_ALPHA));

        mBottomBorderThickness = (int) (DEFAULT_BOTTOM_BORDER_THICKNESS_DIPS * density);
        mBottomBorderPaint = new Paint();
        mBottomBorderPaint.setColor(mDefaultBottomBorderColor);

        mSelectedIndicatorThickness = (int) (SELECTED_INDICATOR_THICKNESS_DIPS * density);
        mSelectedIndicatorPaint = new Paint();

        mDividerHeight = DEFAULT_DIVIDER_HEIGHT;
        mDividerPaint = new Paint();
        mDividerPaint.setStrokeWidth((int) (DEFAULT_DIVIDER_THICKNESS_DIPS * density));

        mBorderPaint = new Paint();
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setStrokeWidth((int) (DEFAULT_BORDER_THICKNESS_DIPS) * density);
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setStrokeCap(Paint.Cap.ROUND);
        mBorderPaint.setStrokeJoin(Paint.Join.ROUND);
        mBorderPaint.setColor(DEFAULT_COLOR);

        mRadius = (int) (DEFAULT_RADIUS * density);
        mBorderPaint.setPathEffect(new CornerPathEffect(mRadius));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        final int height = getHeight();
        final int childCount = getChildCount();
        final int dividerHeightPx = (int) (Math.min(Math.max(0f, mDividerHeight), 1f) * height);
//        final SlidingTabLayout.TabColorizer tabColorizer = mCustomTabColorizer != null
//                ? mCustomTabColorizer
//                : mDefaultTabColorizer;

        // Thick colored underline below the current selection
        if (childCount > 0) {
            View selectedTitle = getChildAt(mSelectedPosition);
            int left = selectedTitle.getLeft();
            int right = selectedTitle.getRight();
            int color = DEFAULT_SELECTED_INDICATOR_COLOR;

            if (mSelectionOffset > 0f && mSelectedPosition < (getChildCount() - 1)) {
                int nextColor = DEFAULT_SELECTED_INDICATOR_COLOR;
                if (color != nextColor) {
                    color = blendColors(nextColor, color, mSelectionOffset);
                }

                // Draw the selection partway between the tabs
                View nextTitle = getChildAt(mSelectedPosition + 1);
                left = (int) (mSelectionOffset * nextTitle.getLeft() +
                        (1.0f - mSelectionOffset) * left);
                right = (int) (mSelectionOffset * nextTitle.getRight() +
                        (1.0f - mSelectionOffset) * right);
            }

            mSelectedIndicatorPaint.setColor(color);

            RectF rect = new RectF(left, 0, right, height);
            drawRoundRect(canvas, rect, new float[]{
                    mSelectedPosition == 0 ? mRadius : 0,
                    mSelectedPosition == 0 ? mRadius : 0,
                    mSelectedPosition == childCount - 1 ? mRadius : 0,
                    mSelectedPosition == childCount - 1 ? mRadius : 0,
                    mSelectedPosition == childCount - 1 ? mRadius : 0,
                    mSelectedPosition == childCount - 1 ? mRadius : 0,
                    mSelectedPosition == 0 ? mRadius : 0,
                    mSelectedPosition == 0 ? mRadius : 0
            }, mSelectedIndicatorPaint);



//            canvas.drawRoundRect(rect, mRadius, mRadius, mSelectedIndicatorPaint);
//            canvas.drawRect(left, height - mSelectedIndicatorThickness, right,
//                    height, mSelectedIndicatorPaint);
        }

        // Thin underline along the entire bottom edge
//        canvas.drawRect(0, height - mBottomBorderThickness, getWidth(), height, mBottomBorderPaint);

        // Vertical separators between the titles
//        int separatorTop = (height - dividerHeightPx) / 2;
//        for (int i = 0; i < childCount - 1; i++) {
//            View child = getChildAt(i);
//            mDividerPaint.setColor(DEFAULT_SELECTED_INDICATOR_COLOR);
//            canvas.drawLine(child.getRight(), separatorTop, child.getRight(),
//                    separatorTop + dividerHeightPx, mDividerPaint);
//        }

//        drawBorder(canvas, getWidth(), height);
    }

    private void drawBorder(Canvas canvas, int width, int height) {
//        RectF rect = new RectF(0, 0, getWidth(), height);
        int left = 0;
        int top = 0;
        int right = left + width;
        int bottom = top + height;
        int radius = mRadius;

        Path path = new Path();
        path.moveTo(left + radius, top);
        path.lineTo(right, top);
        path.lineTo(right, bottom);
        path.lineTo(left + radius, bottom);
        path.quadTo(left, bottom, left, bottom - radius);
        path.lineTo(left, top + radius);
        path.quadTo(left, top, left + radius, top);
        canvas.drawPath(path, mBorderPaint);
    }

    private void drawRoundRect(Canvas canvas, RectF rect, float[] radii, Paint paint) {
        Path path = new Path();
        path.addRoundRect(rect, radii, Path.Direction.CW);
        canvas.drawPath(path, paint);
    }

    void onViewPagerPageChanged(int position, float positionOffset) {
        mSelectedPosition = position;
        mSelectionOffset = positionOffset;

        if (positionOffset == 0) {
            if(getChildCount() > 0) {
                for (int i = 0; i < getChildCount(); i++) {
                    ((TextView)getChildAt(i)).setTextColor(DEFAULT_COLOR);
                }
            }
            ((TextView)getChildAt(position)).setTextColor(SELECTED_TEXT_COLOR);
        }


        invalidate();
    }

    /**
     * Set the alpha value of the {@code color} to be the given {@code alpha} value.
     */
    private static int setColorAlpha(int color, byte alpha) {
        return Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color));
    }

    /**
     * Blend {@code color1} and {@code color2} using the given ratio.
     *
     * @param ratio of which to blend. 1.0 will return {@code color1}, 0.5 will give an even blend,
     *              0.0 will return {@code color2}.
     */
    private static int blendColors(int color1, int color2, float ratio) {
        final float inverseRation = 1f - ratio;
        float r = (Color.red(color1) * ratio) + (Color.red(color2) * inverseRation);
        float g = (Color.green(color1) * ratio) + (Color.green(color2) * inverseRation);
        float b = (Color.blue(color1) * ratio) + (Color.blue(color2) * inverseRation);
        return Color.rgb((int) r, (int) g, (int) b);
    }
}
