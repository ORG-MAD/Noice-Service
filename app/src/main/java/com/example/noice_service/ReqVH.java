package com.example.noice_service;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReqVH extends RecyclerView.ViewHolder {
    public TextView tv_reqCustomerName, tv_reqPrefix, txt_option;

    public ReqVH(@NonNull View itemView) {
        super(itemView);
        tv_reqPrefix = itemView.findViewById(R.id.tv_reqPrefix);
        tv_reqCustomerName = itemView.findViewById(R.id.tv_reqCustomerName);
        txt_option = itemView.findViewById(R.id.txt_option);
    }
}
