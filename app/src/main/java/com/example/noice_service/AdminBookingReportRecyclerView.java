package com.example.noice_service;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdminBookingReportRecyclerView extends RecyclerView.Adapter {
    List<ServiceReportModel> adminBookingsList;

    public AdminBookingReportRecyclerView(List<ServiceReportModel> adminBookingsList){
        this.adminBookingsList = adminBookingsList;
    }

    public class BookingReportViewHolder extends RecyclerView.ViewHolder{

        public TextView booking_name, booking_price, booking_count;
        public RelativeLayout admin_booking_report_parent;
        public CardView card_item;

        public BookingReportViewHolder(View itemView) {
            super(itemView);

            booking_name = itemView.findViewById(R.id.booking_name);
            booking_count = itemView.findViewById(R.id.count);
            booking_price = itemView.findViewById(R.id.price);
            admin_booking_report_parent = itemView.findViewById(R.id.admin_booking_report_parent);
            card_item = itemView.findViewById(R.id.card_item);
        }
    }

    public BookingReportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_admin_booking_report, null);
        return new BookingReportViewHolder(view);
    }

    public void onBindViewHolder( RecyclerView.ViewHolder holder, int position) {
        BookingReportViewHolder bookingViewHolder = (BookingReportViewHolder) holder;

        bookingViewHolder.booking_name.setText(adminBookingsList.get(position).getName());
        bookingViewHolder.booking_price.setText(adminBookingsList.get(position).getPrice());
        bookingViewHolder.booking_count.setText(Integer.toString(adminBookingsList.get(position).getCount()));
    }

    public int getItemCount() {
        return adminBookingsList.size();
    }
}
