package com.example.pitchviewdemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

public class PitchLineScrollLayout extends ViewGroup {

    private final String TAG = "PitchLineScrollLayout";

    private Scroller mScroller;

    // 音乐时长
    private int musicDuration = 10000;

    // 每个子View宽度相加的值
    private int childViewsWidth = 0;

    private long mStartTime;


    public PitchLineScrollLayout(Context context) {
        this(context, null);
    }

    public PitchLineScrollLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PitchLineScrollLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PitchLineScrollLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mScroller = new Scroller(context);
    }

    public void startScroll() {
        /*int scrollX = getScrollX();
        int deltaX = childViewsWidth - scrollX;
        mScroller.startScroll(scrollX, 0, deltaX, 0, musicDuration);*/
        mStartTime = System.currentTimeMillis();

        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
//        Log.d(TAG, String.valueOf(childCount));
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            // 测量每一个子View的宽度
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {

            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
//                Log.d(TAG, String.valueOf(childViewsWidth));
                View childView = getChildAt(i);
                childView.layout(childViewsWidth, 0, childViewsWidth + childView.getMeasuredWidth(), childView.getMeasuredHeight());
                childViewsWidth += childView.getWidth();
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public void computeScroll() {
        /*if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }*/
        long currentTime = System.currentTimeMillis();
        long timePassed = currentTime - mStartTime;
        if (timePassed <= musicDuration) {
            float rate = 1.0f / (float) musicDuration;
            int currX = Math.round(timePassed * rate * childViewsWidth);
            scrollTo(currX, 0);
            postInvalidate();
        }

    }


}
