package com.xiaoooyu.xwidget.demo.segmentedpager;

import android.support.v4.app.Fragment;

/**
 *
 */
public class SegmentedPagerItem {
    private String mTitle;
    private Fragment mFragment;

    public SegmentedPagerItem(String title, Fragment fragment) {
        mTitle = title;
        mFragment = fragment;
    }

    public String getTitle() {
        return mTitle;
    }

    public Fragment getFragment() {
        return mFragment;
    }
}
