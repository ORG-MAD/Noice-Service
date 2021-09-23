package com.example.noice_service;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdminBookingsRecyclerView extends RecyclerView.Adapter {
    List<MyBookings> adminBookingsList;
    MyBookingClickListner bookingClickListner;

    public AdminBookingsRecyclerView(List<MyBookings> adminBookingsList, MyBookingClickListner bookingClickListner){
        this.adminBookingsList = adminBookingsList;
        this.bookingClickListner = bookingClickListner;
    }


    public class BookingViewHolder extends RecyclerView.ViewHolder{

        public TextView booking_name, create_time,car_no;
        public RelativeLayout admin_booking_parent;
        public CardView card_item;

        public BookingViewHolder(View itemView) {
            super(itemView);

            booking_name = itemView.findViewById(R.id.booking_name);
            create_time = itemView.findViewById(R.id.create_time);
            car_no = itemView.findViewById(R.id.carno);
            admin_booking_parent = itemView.findViewById(R.id.admin_booking_parent);
            card_item = itemView.findViewById(R.id.card_item);
        }
    }

    public BookingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_row_admin, null);
        return new BookingViewHolder(view);
    }

    public void onBindViewHolder( RecyclerView.ViewHolder holder, int position) {
        BookingViewHolder bookingViewHolder = (BookingViewHolder) holder;

        bookingViewHolder.booking_name.setText(adminBookingsList.get(position).getBooking_name());
        bookingViewHolder.create_time.setText(adminBookingsList.get(position).getTime_slot());
        bookingViewHolder.car_no.setText(adminBookingsList.get(position).getCar_no());

        bookingViewHolder.admin_booking_parent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                bookingClickListner.onClickItem(adminBookingsList.get(position));

            }
        });
    }

    public int getItemCount() {
        return adminBookingsList.size();
    }
}