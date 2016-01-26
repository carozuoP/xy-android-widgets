package com.xiaoooyu.xwidget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 */
public class DateTextView extends TextView {

    public DateTextView(Context context) {
        super(context);
        setDate();
    }

    public DateTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setDate();
    }

    public DateTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setDate();
    }

    private void setDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String today = format.format(Calendar.getInstance().getTime());
        setText(today);
    }

}
