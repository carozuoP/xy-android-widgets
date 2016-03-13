package com.xiaoooyu.xdemo.lengthpicker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.xiaoooyu.xwidget.LengthPicker;
import com.xiaoooyu.xdemo.R;


/**
 *
 */
public class LengthPickerDemoFragment extends Fragment implements View.OnClickListener {

    private LengthPicker mWidthPicker = null;
    private LengthPicker mHeightPicker = null;
    private TextView mAreaTextView;
    private Button mComputeButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.length_picker_demo_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mWidthPicker = (LengthPicker) view.findViewById(R.id.width);
        mHeightPicker = (LengthPicker) view.findViewById(R.id.height);
        mAreaTextView = (TextView) view.findViewById(R.id.area);

        mComputeButton = (Button) view.findViewById(R.id.compute);
        mComputeButton.setOnClickListener(this);
    }

    public void updateArea(View v) {
        if (mWidthPicker == null || mHeightPicker == null || mAreaTextView == null)
            return;

        int area = mWidthPicker.getNumInches() * mHeightPicker.getNumInches();
        mAreaTextView.setText(getString(R.string.area_format, area));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.compute) {
            updateArea(v);
        }
    }
}
