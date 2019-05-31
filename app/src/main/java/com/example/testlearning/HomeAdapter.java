package com.example.testlearning;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> implements View.OnClickListener, View.OnLongClickListener {
    private Context mContext;
    private List<String> mList;
    private OnItemClickListener mOnItemClickListener;
    private List<Integer> mHeight;

    public HomeAdapter(Context context, List<String> mList) {
        this.mContext = context;
        this.mList = mList;
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycler, parent, false);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.itemView.setTag(position);
        holder.mTextView.setText(mList.get(position));
        initItemHeight();
        ViewGroup.LayoutParams layoutParams = holder.mTextView.getLayoutParams();
        layoutParams.height = mHeight.get(position);
        holder.mTextView.setLayoutParams(layoutParams);
    }

    private void initItemHeight() {
        mHeight = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            mHeight.add((int) (100 + Math.random() * 300));
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(view, (int) view.getTag());
        }
    }

    @Override
    public boolean onLongClick(View view) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemLongClick(view, (Integer) view.getTag());
        }
        return true;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tv_item);
        }
    }
}
