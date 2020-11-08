package com.codeeina.app_covid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;

public class MyGlobalStatsViewAdapter extends RecyclerView.Adapter<MyGlobalStatsViewAdapter.MyViewHolder> implements Filterable {

    Context context;
    List<GlobalEntryItem> entriesList;
    List<GlobalEntryItem> entriesListAll;

    public MyGlobalStatsViewAdapter(Context context, List<GlobalEntryItem> entriesList) {
        this.context = context;
        this.entriesList = entriesList;
        entriesListAll = new ArrayList<>(entriesList);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView totalCasesText, newCasesText, casesPerMText, countryNameText;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            countryNameText = itemView.findViewById(R.id.country_name_global);
            totalCasesText = itemView.findViewById(R.id.number_total_cases_global);
            newCasesText = itemView.findViewById(R.id.number_new_cases_global);
            casesPerMText = itemView.findViewById(R.id.number_casesperm_global);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =  inflater.inflate(R.layout.global_stats_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if(entriesList.size() > position) {
            holder.countryNameText.setText(entriesList.get(position).getCountryName());
            String totalCases = String.valueOf(entriesList.get(position).getTotalCases());
            holder.totalCasesText.setText(totalCases);
            String newCases = String.valueOf(entriesList.get(position).getNewCases());
            holder.newCasesText.setText(newCases);
            String casesPerMillion = String.valueOf(entriesList.get(position).getCasesPerMillion());
            holder.casesPerMText.setText(casesPerMillion);
        }
    }

    @Override
    public int getItemCount() {
        return entriesList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    public void updateList(ArrayList<GlobalEntryItem> newList) {
        entriesList = new ArrayList<>();
        entriesList.addAll(newList);

        notifyDataSetChanged();
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<GlobalEntryItem> filteredList = new ArrayList<>();
            if(constraint.toString().isEmpty()){
                filteredList.addAll(entriesList);
            } else {
                for(GlobalEntryItem entry : entriesListAll) {
                    if(entry.getCountryName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        filteredList.add(entry);
                    }

                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            entriesList.clear();
            entriesList.addAll((Collection<? extends GlobalEntryItem>) results.values);
            notifyDataSetChanged();
        }

    };
}
