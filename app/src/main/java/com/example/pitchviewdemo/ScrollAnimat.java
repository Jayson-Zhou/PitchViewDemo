package com.example.pitchviewdemo;

import android.util.Log;

import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

public class ScrollAnimat {

    // 记录动画启动时间
    private long startTime;

    // 记录动画走过的长度
    private int scrolledLength;

    // 音乐时长
    private long musicDuration;

    // 动画作用的RecyclerView
    private RecyclerView mRv;

    public ScrollAnimat(RecyclerView view, long duration) {
        mRv = view;
        musicDuration = duration;
    }

    private Runnable scrollRunnable = new Runnable() {
        @Override
        public void run() {

            if (mRv.canScrollHorizontally(1)) {
                Log.d("ScrollAnimat:", "滑动结束");
                return;
            }
            long currentTime = System.currentTimeMillis();
            int currentLength = (int) (mRv.getWidth() * (currentTime - startTime) / musicDuration);
            // 无效，RecyclerView中的scrollTo为空实现，不支持绝对滑动
            // mRv.scrollTo(scrolledLength, currentLength);
            mRv.scrollBy(currentLength - scrolledLength, 0);
            scrolledLength = currentLength;
            ViewCompat.postOnAnimation(mRv, this);
        }
    };

    public void startAnimation() {
        startTime = System.currentTimeMillis();
        scrolledLength = 0;
        ViewCompat.postOnAnimation(mRv, scrollRunnable);
    }
}
