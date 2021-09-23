package com.example.noice_service;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RequestAdapterCustomer extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    ArrayList<ReqModel> list = new ArrayList<>();

    public RequestAdapterCustomer(Context ctx){
        this.context = ctx;
    }
    public void setItems(ArrayList<ReqModel> emp){
        list.addAll(emp);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_requests_customer, parent, false);
        return new ReqCusVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ReqCusVH vhc = (ReqCusVH) holder;
        ReqModel req = list.get(position);
        String customerName = req.getCustomerName();
        String prefix = req.getCustomerName().substring(0,1);
        vhc.tv_reqCustomerName.setText(customerName);
        vhc.tv_reqPrefix.setText(prefix);
        vhc.txt_option.setOnClickListener(v->{
            PopupMenu popupMenu = new PopupMenu(context, vhc.txt_option);
            popupMenu.inflate(R.menu.options_menu_cust);
            popupMenu.setOnMenuItemClickListener(item->
            {
                switch (item.getItemId()){
                    case R.id.menu_update:
                        Intent intentU = new Intent(context, EditSelectedRequest.class);
                        intentU.putExtra("UPDATE", req);
                        context.startActivity(intentU);
                        break;
                }
                return false;
            });
            popupMenu.show();
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
