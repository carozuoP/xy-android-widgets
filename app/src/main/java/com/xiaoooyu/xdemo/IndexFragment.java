package com.xiaoooyu.xdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaoooyu.xdemo.photospiral.PhotoSpiralDemoFragment;
import com.xiaoooyu.xdemo.sidewayslayout.SidewaysLayoutDemoFragment;
import com.xiaoooyu.xdemo.view.CustomViewDemoFragment;
import com.xiaoooyu.xdemo.datetextview.DateTextViewDemoFragment;
import com.xiaoooyu.xdemo.lengthpicker.LengthPickerDemoFragment;
import com.xiaoooyu.xdemo.segmentedpager.SegmentedPagerDemoFragment;

import com.xiaoooyu.xdemo.R;
/**
 *
 */
public class IndexFragment extends Fragment
    implements View.OnClickListener{

    private View mDemoSegmentedBtn;
    private View mDemoDateTextViewBtn;
    private View mDemoLengthPickerBtn;
    private View mDemoCustomViewBtn;
    private View mDemoPhotoSpiralBtn;
    private View mDemoSidewaysBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.index_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDemoSegmentedBtn = view.findViewById(R.id.index_fragment_demo_segmented);
        mDemoSegmentedBtn.setOnClickListener(this);

        mDemoDateTextViewBtn = view.findViewById(R.id.index_fragment_demo_date_textview);
        mDemoDateTextViewBtn.setOnClickListener(this);

        mDemoLengthPickerBtn = view.findViewById(R.id.index_fragment_demo_lengthpicker);
        mDemoLengthPickerBtn.setOnClickListener(this);

        mDemoCustomViewBtn = view.findViewById(R.id.index_fragment_demo_view);
        mDemoCustomViewBtn.setOnClickListener(this);

        mDemoPhotoSpiralBtn = view.findViewById(R.id.index_fragment_demo_photo_spiral);
        mDemoPhotoSpiralBtn.setOnClickListener(this);

        mDemoSidewaysBtn = view.findViewById(R.id.index_fragment_demo_sideways_layout);
        mDemoSidewaysBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.index_fragment_demo_segmented:
                openSegmentedDemo();
                break;
            case R.id.index_fragment_demo_date_textview:
                openDateTextViewDemo();
                break;
            case R.id.index_fragment_demo_lengthpicker:
                openLengthPickerDemo();
                break;
            case R.id.index_fragment_demo_view:
                openCustomViewDemo();
                break;
            case R.id.index_fragment_demo_photo_spiral:
                openPhotoSpiralDemo();
                break;
            case R.id.index_fragment_demo_sideways_layout:
                openSidewaysLayoutDemo();
                break;
            default:
                break;
        }
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void openSegmentedDemo() {
        openFragment(new SegmentedPagerDemoFragment());
    }

    private void openDateTextViewDemo() {
        openFragment(new DateTextViewDemoFragment());
    }

    private void openLengthPickerDemo() {
        openFragment(new LengthPickerDemoFragment());
    }

    private void openCustomViewDemo() {
        openFragment(new CustomViewDemoFragment());
    }

    private void openPhotoSpiralDemo() {
        openFragment(new PhotoSpiralDemoFragment());
    }

    private void openSidewaysLayoutDemo() {
        openFragment(new SidewaysLayoutDemoFragment());
    }
}
