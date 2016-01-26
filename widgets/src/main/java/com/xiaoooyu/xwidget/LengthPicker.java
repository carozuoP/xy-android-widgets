package com.xiaoooyu.xwidget;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 *
 */
public class LengthPicker extends LinearLayout implements View.OnClickListener {

    private TextView mTextView;
    private View mPlusButton;
    private View mMinusButton;
    private int mNumInches;

    public LengthPicker(Context context) {
        super(context);
        init();
    }

    public LengthPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.length_picker, this);

        mPlusButton = findViewById(R.id.plus_button);
        mMinusButton = findViewById(R.id.minus_button);
        mTextView = (TextView) findViewById(R.id.text);

        updateControls();

        mPlusButton.setOnClickListener(this);
        mMinusButton.setOnClickListener(this);
    }

    private void updateControls() {
        int feet = mNumInches / 12;
        int inches = mNumInches % 12;

        String text = String.format("%d' %d\"", feet, inches);
        if (feet == 0) {
            text = String.format("%d\"", inches);
        } else {
            if (inches == 0) {
                text = String.format("%d'", feet);
            }
        }
        mTextView.setText(text);
        mMinusButton.setEnabled(mNumInches > 0);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.plus_button) {
            mNumInches ++;
            updateControls();
        } else if (id == R.id.minus_button) {
            if (mNumInches > 0) {
                mNumInches --;
                updateControls();
            }
        } else {

        }
    }

    public int getNumInches() {
        return mNumInches;
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("superState", super.onSaveInstanceState());
        bundle.putInt("numInches", mNumInches);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            mNumInches = bundle.getInt("numInches");
            super.onRestoreInstanceState(bundle.getParcelable("superState"));
        } else {
            super.onRestoreInstanceState(state);
        }

        updateControls();
    }
}
