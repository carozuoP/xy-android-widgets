package com.xiaoooyu.xwidget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 *
 */
public class SegmentedPagerLayout extends FrameLayout {

    private static final int DEFAULT_VIEW_TEXT_SIZE = 16;
    private static final int DETAULT_VIEW_PADDING = 8;

    private final SegmentedPagerTitleStrip mTitleStrip;
    private ViewPager mViewPager;
    private ViewPager.OnPageChangeListener mViewPagerPageChangeListener;
    private int mTextSize;
    private int mSegmentPadding;

    public SegmentedPagerLayout(Context context) {
        this(context, null);
    }

    public SegmentedPagerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        getFromAttributes(context, attrs);

        mTitleStrip = new SegmentedPagerTitleStrip(context);

        addView(mTitleStrip,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    private void getFromAttributes(Context context, AttributeSet attrs) {
        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.SegmentedPagerLayoutStyle);

        mTextSize = t.getDimensionPixelSize(R.styleable.SegmentedPagerLayoutStyle_segmentTextSize,
                DEFAULT_VIEW_TEXT_SIZE);
        mSegmentPadding = t.getDimensionPixelSize(R.styleable.SegmentedPagerLayoutStyle_segmentPadding,
                DETAULT_VIEW_PADDING);

        t.recycle();
    }

    /**
     * Sets the associated view pager. Note that the assumption here is that the pager content
     * (number of tabs and tab titles) does not change after this call has been made.
     */
    public void setViewPager(ViewPager viewPager) {
        mTitleStrip.removeAllViews();

        mViewPager = viewPager;
        if (viewPager != null) {
            viewPager.setOnPageChangeListener(new InternalViewPagerListener());
            populateTitleStrip();
        }
    }

    private void populateTitleStrip() {
        final PagerAdapter adapter = mViewPager.getAdapter();
        final OnClickListener tabClickListener = new TabClickListener();

        for (int i = 0; i < adapter.getCount(); i++) {
            View tabView = null;
            TextView tabTitleView = null;

//            if (mTabViewLayoutId != 0) {
//                // If there is a custom tab view layout id set, try and inflate it
//                tabView = LayoutInflater.from(getContext()).inflate(mTabViewLayoutId, mTabStrip,
//                        false);
//                tabTitleView = (TextView) tabView.findViewById(mTabViewTextViewId);
//            }

            if (tabView == null) {
                tabView = createDefaultTabView(getContext());
            }

            if (tabTitleView == null && TextView.class.isInstance(tabView)) {
                tabTitleView = (TextView) tabView;
            }

            tabTitleView.setText(adapter.getPageTitle(i));
            tabView.setOnClickListener(tabClickListener);

            mTitleStrip.addView(tabView);
        }
    }

    /**
     * Create a default view to be used for tabs.
     */
    protected TextView createDefaultTabView(Context context) {
        TextView textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(mTextSize);
//        textView.setTypeface(Typeface.DEFAULT_BOLD);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // If we're running on Honeycomb or newer, then we can use the Theme's
            // selectableItemBackground to ensure that the View has a pressed state
            TypedValue outValue = new TypedValue();
            getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground,
                    outValue, true);
            textView.setBackgroundResource(outValue.resourceId);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            // If we're running on ICS or newer, enable all-caps to match the Action Bar tab style
            textView.setAllCaps(true);
        }

        int padding = mSegmentPadding;
        textView.setPadding(padding, padding, padding, padding);
        LinearLayout.LayoutParams layoutParams =
                new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                        LayoutParams.MATCH_PARENT, 1.0f);
        textView.setLayoutParams(layoutParams);

        return textView;
    }

    private class InternalViewPagerListener implements ViewPager.OnPageChangeListener {
        private int mScrollState;

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            int tabStripChildCount = mTitleStrip.getChildCount();
            if ((tabStripChildCount == 0) || (position < 0) || (position >= tabStripChildCount)) {
                return;
            }

            mTitleStrip.onViewPagerPageChanged(position, positionOffset);

            View selectedTitle = mTitleStrip.getChildAt(position);
            int extraOffset = (selectedTitle != null)
                    ? (int) (positionOffset * selectedTitle.getWidth())
                    : 0;

            if (mViewPagerPageChangeListener != null) {
                mViewPagerPageChangeListener.onPageScrolled(position, positionOffset,
                        positionOffsetPixels);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            mScrollState = state;

            if (mViewPagerPageChangeListener != null) {
                mViewPagerPageChangeListener.onPageScrollStateChanged(state);
            }
        }

        @Override
        public void onPageSelected(int position) {
            if (mScrollState == ViewPager.SCROLL_STATE_IDLE) {
                mTitleStrip.onViewPagerPageChanged(position, 0f);
            }

            if (mViewPagerPageChangeListener != null) {
                mViewPagerPageChangeListener.onPageSelected(position);
            }
        }
    }

    private class TabClickListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            for (int i = 0; i < mTitleStrip.getChildCount(); i++) {
                if (v == mTitleStrip.getChildAt(i)) {
                    mViewPager.setCurrentItem(i);
                    return;
                }
            }
        }
    }
}
