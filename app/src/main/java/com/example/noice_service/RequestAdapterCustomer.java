package com.example.noice_service;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

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
//                    case R.id.menu_remove:
//                        Intent intentR = new Intent(context, DeleteSelectedRequest.class);
//                        intentR.putExtra("REMOVE", req);
//                        context.startActivity(intentR);
//                        DAORequest dao = new DAORequest();
//                        dao.remove(req.getKey()).addOnSuccessListener(sucess -> {
//                            Toast.makeText(context, "Record is Removed", Toast.LENGTH_SHORT).show();
//                            notifyItemRemoved(position);
//                        }).addOnFailureListener((er -> {
//                            Toast.makeText(context, "" + er.getMessage(), Toast.LENGTH_SHORT).show();
//                        }));
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

//    private List<ReqModelCustomer> reqModelCustomerList;
//    private List<ReqModelCustomer> getReqModelCustomerListFiltered;
//    private Context context;
//    private SelectedReqCustomer selectedReqCustomer;
//
//    public RequestAdapterCustomer(List<ReqModelCustomer> reqModelCustomerList, SelectedReqCustomer selectedReqCustomer) {
//        this.reqModelCustomerList = reqModelCustomerList;
//        this.getReqModelCustomerListFiltered = reqModelCustomerList;
//        this.selectedReqCustomer = selectedReqCustomer;
//    }
//
//    @NonNull
//    @Override
//    public ReqAdapterCustomerVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        context = parent.getContext();
//        return new ReqAdapterCustomerVh(LayoutInflater.from(context).inflate(R.layout.row_requests_customer, null));
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ReqAdapterCustomerVh holder, int position) {
//        ReqModelCustomer reqModelCustomer = reqModelCustomerList.get(position);
//        String customerName = reqModelCustomer.getRequestTitle();
//        String prefix = reqModelCustomer.getRequestTitle().substring(0,1);
//
//        holder.tvreqCustomerName.setText(customerName);
//        holder.tvreqPrefix.setText(prefix);
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return reqModelCustomerList.size();
//    }
//
//    @Override
//    public Filter getFilter() {
//        Filter filter = new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence constraint) {
//                FilterResults filterResults = new FilterResults();
//                if(constraint == null | constraint.length()==0){
//                    filterResults.count=getReqModelCustomerListFiltered.size();
//                    filterResults.values = getReqModelCustomerListFiltered;
//                }else{
//                    String searchChr = constraint.toString().toLowerCase();
//                    List<ReqModelCustomer> resultData = new ArrayList<>();
//                    for(ReqModelCustomer reqModelCustomer: getReqModelCustomerListFiltered){
//                        if(reqModelCustomer.getRequestTitle().toLowerCase().contains(searchChr)){
//                            resultData.add(reqModelCustomer);
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
//                reqModelCustomerList = (List<ReqModelCustomer>) results.values;
//                notifyDataSetChanged();
//
//            }
//        };
//        return filter;
//    }
//
//    public interface SelectedReqCustomer{
//        void selectedReqCustomer(ReqModelCustomer reqModelCustomer);
//    }
//
//    public class ReqAdapterCustomerVh extends RecyclerView.ViewHolder {
//        TextView tvreqPrefix;
//        TextView tvreqCustomerName;
//        ImageView imIcon;
//
//        public ReqAdapterCustomerVh(@NonNull View itemView) {
//            super(itemView);
//            tvreqPrefix = itemView.findViewById(R.id.tv_reqPrefixCustomer);
//            tvreqCustomerName = itemView.findViewById(R.id.tv_reqTopic);
//            imIcon = itemView.findViewById(R.id.imageView);
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    selectedReqCustomer.selectedReqCustomer(reqModelCustomerList.get(getAdapterPosition()));
//                }
//            });
//        }
//    }
//}
