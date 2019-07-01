package com.example.pitchviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PitchRecyclerViewAdapter extends RecyclerView.Adapter<PitchRecyclerViewAdapter.ViewHolder> {

    private final String TAG = "PitchRecyclerViewAdap";

    /*private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;*/

    private Context mContext;

    // 正确音高数据
    private List<PitchLineData> correctPitchDataList;

    /*// 实际音高数据
    private List<Integer> realPitchDataList;

    private ScoreLineView headerView;*/

    public PitchRecyclerViewAdapter(Context context) {
        super();
        mContext = context;
    }

    public void setCorrectPitchData(@NonNull List<PitchLineData> list) {
        correctPitchDataList = list;
        notifyDataSetChanged();
    }

    /*public void setRealPitchData(@NonNull List<Integer> list) {
        headerView = new ScoreLineView(mContext);
        realPitchDataList = list;
        notifyItemInserted(2);
        headerView.setScore(list.get(10));
    }*/



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*if (viewType == TYPE_HEADER) {
            return new ViewHolder(headerView);
        }*/
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.pitch_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        /*if (getItemViewType(position) == TYPE_HEADER) {
            headerView.setScore(200);
            return;
        }*/
        holder.pv.setData(correctPitchDataList.get(position));
    }

    /*@Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_HEADER;
        return TYPE_NORMAL;
    }*/

    @Override
    public int getItemCount() {
        return correctPitchDataList == null ? 0 : correctPitchDataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public PitchLineView pv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            /*if (itemView == headerView)
                return;*/
            pv = itemView.findViewById(R.id.pitch_view_item);
        }
    }
}
