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

    private Context mContext;

    private List<PitchLineData> mList;

    public PitchRecyclerViewAdapter(Context context, @NonNull List<PitchLineData> list) {
        super();
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.pitch_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.pv.setData(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public PitchLineView pv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pv = itemView.findViewById(R.id.pitch_view_item);
        }
    }
}
