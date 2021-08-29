package com.example.noice_service;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.ReqAdapterVh> implements Filterable {
    private List<ReqModel> reqModelList;
    private List<ReqModel> getReqModelListFiltered;
    private Context context;
    private SelectedReq selectedReq;

    public RequestAdapter(List<ReqModel> reqModelList, SelectedReq selectedReq) {
        this.reqModelList = reqModelList;
        this.getReqModelListFiltered = reqModelList;
        this.selectedReq = selectedReq;
    }

    @NonNull
    @Override
    public ReqAdapterVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ReqAdapterVh(LayoutInflater.from(context).inflate(R.layout.row_requests, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ReqAdapterVh holder, int position) {
        ReqModel reqModel = reqModelList.get(position);
        String customerName = reqModel.getCustomerName();
        String prefix = reqModel.getCustomerName().substring(0,1);

        holder.tvreqCustomerName.setText(customerName);
        holder.tvreqPrefix.setText(prefix);

    }

    @Override
    public int getItemCount() {
        return reqModelList.size();
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if(constraint == null | constraint.length()==0){
                    filterResults.count=getReqModelListFiltered.size();
                    filterResults.values = getReqModelListFiltered;
                }else{
                    String searchChr = constraint.toString().toLowerCase();
                    List<ReqModel> resultData = new ArrayList<>();
                    for(ReqModel reqModel: getReqModelListFiltered){
                        if(reqModel.getCustomerName().toLowerCase().contains(searchChr)){
                            resultData.add(reqModel);
                        }
                    }
                    filterResults.count = resultData.size();
                    filterResults.values = resultData;
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                reqModelList = (List<ReqModel>) results.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }

    public interface SelectedReq{
        void selectedReq(ReqModel reqModel);
    }

    public class ReqAdapterVh extends RecyclerView.ViewHolder {
        TextView tvreqPrefix;
        TextView tvreqCustomerName;
        ImageView imIcon;

        public ReqAdapterVh(@NonNull View itemView) {
            super(itemView);
            tvreqPrefix = itemView.findViewById(R.id.tv_reqPrefix);
            tvreqCustomerName = itemView.findViewById(R.id.tv_reqCustomerName);
            imIcon = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedReq.selectedReq(reqModelList.get(getAdapterPosition()));
                }
            });
        }
    }
}
