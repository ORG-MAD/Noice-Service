package com.example.noice_service;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FeedbackReadVH extends RecyclerView.ViewHolder {

    public TextView tv_type, tv_subject, tv_msg;

    public FeedbackReadVH(@NonNull View itemView) {
        super(itemView);
        tv_type = itemView.findViewById(R.id.tv_type);
        tv_subject = itemView.findViewById(R.id.tv_subject);
        tv_msg = itemView.findViewById(R.id.tv_msg);

    }
}
