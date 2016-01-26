package com.xiaoooyu.xdemo.segmentedpager;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaoooyu.xwidget.SegmentedPagerLayout;
import com.xiaoooyu.xdemo.R;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class SegmentedPagerDemoFragment extends Fragment {

    private ViewPager mViewPager;
    private SegmentedPagerLayout mViewPagerTitle;

    private ArrayList<SegmentedPagerItem> mPagerItems;

    public SegmentedPagerDemoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.segmented_pager_demo_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setAdapter(new MyPagerAdapter(getChildFragmentManager()));

        mViewPagerTitle = (SegmentedPagerLayout) view.findViewById(R.id.viewpager_title);
        mViewPagerTitle.setViewPager(mViewPager);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPagerItems = new ArrayList<>();
        mPagerItems.add(new SegmentedPagerItem("搭理搭理", new SegmentedPagerChildFragment()));
        mPagerItems.add(new SegmentedPagerItem("搭理搭理", new SegmentedPagerChildFragment()));
        mPagerItems.add(new SegmentedPagerItem("搭理搭理", new SegmentedPagerChildFragment()));
//        mPagerItems.add(new SegmentedPagerItem("搭理搭理搭理搭理", new SegmentedPagerChildFragment()));
//        mPagerItems.add(new SegmentedPagerItem("搭理搭理", new SegmentedPagerChildFragment()));
//        mPagerItems.add(new SegmentedPagerItem("搭理搭理", new SegmentedPagerChildFragment()));
//        mPagerItems.add(new SegmentedPagerItem("搭理搭理", new SegmentedPagerChildFragment()));
//        mPagerItems.add(new SegmentedPagerItem("搭理搭理", new SegmentedPagerChildFragment()));
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            if (mPagerItems == null)
                return 0;

            return mPagerItems.size();
        }

        @Override
        public Fragment getItem(int position) {
            return mPagerItems.get(position).getFragment();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mPagerItems.get(position).getTitle();
        }
    }
}
