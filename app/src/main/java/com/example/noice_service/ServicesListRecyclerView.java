package com.example.noice_service;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ServicesListRecyclerView extends RecyclerView.Adapter  {

    List<ServicesModel> servicesModel;
    ServiceListClickListener serviceListClickListener;

    public ServicesListRecyclerView(List<ServicesModel> servicesModel, ServiceListClickListener serviceListClickListener) {
        this.servicesModel = servicesModel;
        this.serviceListClickListener = serviceListClickListener;
    }

    public class ServiceViewHolder extends RecyclerView.ViewHolder{

        public TextView s_price, s_title;
        public RelativeLayout services_parent;
        public CardView card_item;

        public ServiceViewHolder(View itemView) {
            super(itemView);

            s_price = itemView.findViewById(R.id.s_price);
            s_title = itemView.findViewById(R.id.s_title);
            services_parent = itemView.findViewById(R.id.services_parent);
            card_item = itemView.findViewById(R.id.card_item);
        }
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.services_row, null);
        return new ServiceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ServiceViewHolder serviceViewHolder = (ServiceViewHolder) holder;

        serviceViewHolder.s_title.setText(servicesModel.get(position).getS_title());
        serviceViewHolder.s_price.setText(servicesModel.get(position).getS_price());

        serviceViewHolder.services_parent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                serviceListClickListener.onClickItem(servicesModel.get(position));

            }
        });
    }

    @Override
    public int getItemCount() {
        return servicesModel.size();
    }
}
