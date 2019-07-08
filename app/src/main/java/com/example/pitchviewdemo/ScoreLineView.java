package com.example.pitchviewdemo;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

public class ScoreLineView extends View {

    private final String TAG = "ScoreLineView:";

    // 画笔
    private Paint paint;

    // 线宽
    private final int lineWidth = 7;

    // 小球的半径
    private final int ballRadius = 13;

    // 控件宽度
    private final int viewWidth = 100;

    // 控件高度，取决于音高量化分度值的最大值
    private final int viewHeight = 250;

    // 小球上下移动的动画
    private BallAnimation bAnimation;

    // 移动的距离
    private int dY;

    // 动画时间
    private final int moveTime = 5;

    private int currentPosition = viewHeight - ballRadius;
    private int destPosition;

    public ScoreLineView(Context context) {
        super(context);
        initView();
    }

    public ScoreLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ScoreLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ScoreLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int lineWidth = MeasureSpec.makeMeasureSpec(viewWidth, MeasureSpec.EXACTLY);
        // 线高取决于音高量化分度值的最大值
        int lineHeight = MeasureSpec.makeMeasureSpec(viewHeight, MeasureSpec.EXACTLY);
        super.onMeasure(lineWidth, lineHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setStrokeWidth(lineWidth);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.ScoreLineColor));
        canvas.drawLine(viewWidth - ballRadius, 0, viewWidth - ballRadius, viewHeight, paint);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        canvas.drawCircle(viewWidth - ballRadius, currentPosition, ballRadius, paint);
    }

    public void setScore(int score) {
        destPosition = score;
        bAnimation = new BallAnimation(score);
        this.startAnimation(bAnimation);
    }

    public class BallAnimation extends Animation {

        public BallAnimation(int height) {
            destPosition = viewHeight - ballRadius - height;
        }

        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
            setDuration(moveTime);
            setFillAfter(true);
            //setInterpolator(new LinearInterpolator());
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            dY = (int) (interpolatedTime * (currentPosition - destPosition));
            // 更新小球的位置坐标
            currentPosition -= dY;
            postInvalidate();
        }
    }

}
