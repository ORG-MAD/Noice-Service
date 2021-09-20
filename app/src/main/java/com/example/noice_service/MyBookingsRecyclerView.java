package com.example.noice_service;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyBookingsRecyclerView extends RecyclerView.Adapter  {

    List<MyBookings> myBookingsList;
    MyBookingClickListner bookingClickListner;

    public MyBookingsRecyclerView(List<MyBookings> myBookingsList, MyBookingClickListner bookingClickListner){
        this.myBookingsList = myBookingsList;
        this.bookingClickListner = bookingClickListner;
    }


    public class BookingViewHolder extends RecyclerView.ViewHolder{

        public TextView booking_name, create_time;
        public RelativeLayout booking_parent;
        public CardView card_item;

        public BookingViewHolder(View itemView) {
            super(itemView);

            booking_name = itemView.findViewById(R.id.booking_name);
            create_time = itemView.findViewById(R.id.create_time);
            booking_parent = itemView.findViewById(R.id.booking_parent);
            card_item = itemView.findViewById(R.id.card_item);
        }
    }

    public BookingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_booking_row, null);
        return new BookingViewHolder(view);
    }

    public void onBindViewHolder( RecyclerView.ViewHolder holder, int position) {
        BookingViewHolder bookingViewHolder = (BookingViewHolder) holder;

        bookingViewHolder.booking_name.setText(myBookingsList.get(position).getBooking_name());
        bookingViewHolder.create_time.setText(myBookingsList.get(position).getBooking_time());

        bookingViewHolder.booking_parent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                bookingClickListner.onClickItem(myBookingsList.get(position));

            }
        });
    }

    public int getItemCount() {
        return myBookingsList.size();
    }
}
