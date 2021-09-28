package com.example.noice_service;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class jReadFeedbacksAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    ArrayList<Feedbacks> list = new ArrayList<>();

    public jReadFeedbacksAdapter(Context context) {
        this.context = context;
    }

    public void setItems(ArrayList <Feedbacks> feedback){
        list.addAll(feedback);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item, parent, false);
        return new FeedbackReadVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        FeedbackReadVH vh = (FeedbackReadVH) holder;
        Feedbacks feed = list.get(position);
        vh.tv_type.setText(feed.getType());
        vh.tv_msg.setText(feed.getMessage());
        vh.tv_subject.setText(feed.getSubject());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
