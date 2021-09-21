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

public class RequestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    ArrayList<ReqModel> list = new ArrayList<>();
    public RequestAdapter(Context ctx){
        this.context = ctx;
    }

    public void setItems(ArrayList<ReqModel> emp){
        list.addAll(emp);
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_requests, parent, false);
        return new ReqVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ReqVH vh = (ReqVH) holder;
        ReqModel req = list.get(position);
        String customerName = req.getCustomerName();
        String prefix = req.getCustomerName().substring(0,1);
        vh.tv_reqCustomerName.setText(customerName);
        vh.tv_reqPrefix.setText(prefix);
        vh.txt_option.setOnClickListener(v->{
            PopupMenu popupMenu = new PopupMenu(context, vh.txt_option);
            popupMenu.inflate(R.menu.option_menu);
            popupMenu.setOnMenuItemClickListener(item->
            {
                switch (item.getItemId()){
                    case R.id.menu_edit:
                        Intent intent = new Intent(context, SelectedRequest.class);
                        intent.putExtra("VIEW", req);
                        context.startActivity(intent);
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

//public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ReqAdapterVh> implements Filterable {
//    private Context context;
//    ArrayList<ReqModel> reqModelList = new ArrayList<>();
//    public RequestAdapter(Context ctx){
//        this.context = ctx;
//    }
//    private ArrayList<ReqModel> getReqModelListFiltered;
//    private SelectedReq selectedReq;
//
//    public RequestAdapter(ArrayList<ReqModel> reqModelList, SelectedRequest selectedReq) {
//        this.reqModelList = reqModelList;
//        this.getReqModelListFiltered = reqModelList;
//        this.selectedReq = selectedReq;
//    }
//
////    @NonNull
////    @Override
////    public ReqAdapterVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
////        context = parent.getContext();
////        return new ReqAdapterVh(LayoutInflater.from(context).inflate(R.layout.row_requests, null));
////    }
//
//    public void setItems(ArrayList<ReqModel> req){
//        reqModelList.addAll(req);
//    }
//
//    @NonNull
//    @Override
//    public ReqAdapterVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.row_requests, parent, false);
//        return new ReqAdapterVh(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ReqAdapterVh holder, int position) {
//        ReqModel reqModel = reqModelList.get(position);
//        String customerName = reqModel.getCustomerName();
////        String customerID = reqModel.getCustomerID();
////        String contactNo = reqModel.getContactNumber();
////        String vehicleName = reqModel.getVehicleName();
////        String location = reqModel.getLocation();
//        String prefix = reqModel.getCustomerName().substring(0,1);
//        holder.tvreqCustomerName.setText(customerName);
//        holder.tvreqPrefix.setText(prefix);
//        holder.txtOption.setOnClickListener(v->{
//            PopupMenu popupMenu = new PopupMenu(context, holder.txtOption){
//                popupMenu.inflate(R.menu.option_menu);
//                popupMenu.setOnMenuItemClickListener(item->{
//
//                });
//            }
//        });
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return reqModelList.size();
//    }
//
//    @Override
//    public Filter getFilter() {
//        Filter filter = new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence constraint) {
//                FilterResults filterResults = new FilterResults();
//                if(constraint == null | constraint.length()==0){
//                    filterResults.count=getReqModelListFiltered.size();
//                    filterResults.values = getReqModelListFiltered;
//                }else{
//                    String searchChr = constraint.toString().toLowerCase();
//                    ArrayList<ReqModel> resultData = new ArrayList<>();
//                    for(ReqModel reqModel: getReqModelListFiltered){
//                        if(reqModel.getCustomerName().toLowerCase().contains(searchChr)){
//                            resultData.add(reqModel);
//                        }
//                    }
//                    filterResults.count = resultData.size();
//                    filterResults.values = resultData;
//                }
//                return filterResults;
//            }
//
//            @Override
//            protected void publishResults(CharSequence constraint, FilterResults results) {
//                reqModelList = (ArrayList<ReqModel>) results.values;
//                notifyDataSetChanged();
//
//            }
//        };
//        return filter;
//    }
//
//    public interface SelectedReq{
//        void selectedReq(ReqModel reqModel);
//    }
//
//    public class ReqAdapterVh extends RecyclerView.ViewHolder {
//        TextView tvreqPrefix;
//        TextView tvreqCustomerName;
//        TextView txtOption;
//        ImageView imIcon;
//
//        public ReqAdapterVh(@NonNull View itemView) {
//            super(itemView);
//            tvreqPrefix = itemView.findViewById(R.id.tv_reqPrefix);
//            tvreqCustomerName = itemView.findViewById(R.id.tv_reqCustomerName);
//            txtOption = itemView.findViewById(R.id.txt_option);
//            imIcon = itemView.findViewById(R.id.imageView);
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    selectedReq.selectedReq(reqModelList.get(getAdapterPosition()));
//                }
//            });
//        }
//    }
//}
