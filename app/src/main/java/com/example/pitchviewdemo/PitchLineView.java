package com.example.pitchviewdemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

public class PitchLineView extends View {

    // 线高
    private int lineHeight = 25;

    // 画笔
    private Paint paint;

    // 音高线类对象
    private PitchLineData mData;

    public PitchLineView(Context context) {
        super(context);
    }

    public PitchLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initData();
    }

    public PitchLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PitchLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initData();
    }

    private void initData() {
        // 设置画笔属性线高等
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(lineHeight);
//        该方法在API23后过时
//        paint.setColor(getContext().getColor(R.color.PitchLineColor));
        paint.setColor(ContextCompat.getColor(getContext(), R.color.PitchLineColor));
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSpec, heightSpec;
        if (mData != null && mData.lineLength > 0) {
            widthSpec = MeasureSpec.makeMeasureSpec(mData.lineLength, MeasureSpec.EXACTLY);
            heightSpec = MeasureSpec.makeMeasureSpec(lineHeight, MeasureSpec.EXACTLY);
        } else {
            widthSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.EXACTLY);
            heightSpec = MeasureSpec.makeMeasureSpec(lineHeight, MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthSpec, heightSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mData == null || !mData.needSing)
            return;
        canvas.drawLine(0, mData.heightY, mData.lineLength, mData.heightY, paint);
    }

    /**
     * 设置音高线属性
     *
     * @param data
     */
    public void setData(PitchLineData data) {
        mData = data;
        invalidate();
    }

}
