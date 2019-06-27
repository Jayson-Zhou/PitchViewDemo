package com.example.pitchviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    private LinearLayout ll;
    private RecyclerView rv;

    // RecycleView适配器
    private PitchRecyclerViewAdapter adapter;
    // 滑动动画
    private ScrollAnimat animation;
    // RecyclerView的长度
    private int rvWidth;

    // 模拟音乐时长
    private long musicDuration = 100000;

    // 音高数据
    private List<PitchLineData> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        /*PitchLineView test = findViewById(R.id.test);
        PitchLineData data = new PitchLineData();
        data.heightY = 10;
        data.lineLength = 100;
        data.needSing = true;
        test.setData(data);*/
        initViewAndData();
    }

    private void initViewAndData() {
        ll = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_main, null);
        rv = new RecyclerView(this) {
            @Override
            public boolean dispatchTouchEvent(MotionEvent ev) {
                return true;
            }
        };
        ll.addView(rv, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayout.LayoutParams rvParams = (LinearLayout.LayoutParams) rv.getLayoutParams();
        rvParams.gravity = Gravity.CENTER;
        Log.d(TAG, String.valueOf((rvParams.gravity == Gravity.CENTER)));
        rv.setLayoutParams(rvParams);
        rv.setLayoutManager(manager);
        dataList = new ArrayList<>();
        initData();
        adapter = new PitchRecyclerViewAdapter(this, dataList);
        rv.setAdapter(adapter);
        setContentView(ll);
        startScrollAnimat();
    }

    private void initData() {
        if (dataList != null) {
            // 插入模拟数据
            PitchLineData data1 = new PitchLineData();
            data1.lineLength = 1000;
            data1.needSing = false;
            dataList.add(data1);
            for (int i = 0; i < 30; i++) {
                PitchLineData d = new PitchLineData();
                d.heightY = i % 5 * 50;
                d.lineLength = i * 50;
                d.needSing = i % 4 != 0;
                dataList.add(d);
            }
            PitchLineData data2 = new PitchLineData();
            data2.lineLength = 1000;
            data2.needSing = false;
            dataList.add(data2);

            rvWidth = 0;
            for (int i = 0; i < dataList.size(); i ++) {
                rvWidth += dataList.get(i).lineLength;
//                Log.d(TAG, String.valueOf(rvWidth));
            }
//            Log.d(TAG, "初始化模拟数据");
        }
    }

    private void startScrollAnimat() {
        animation = new ScrollAnimat(rv, musicDuration);
        animation.startAnimation(rvWidth);
    }
}
