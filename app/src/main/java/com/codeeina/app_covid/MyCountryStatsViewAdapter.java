package com.codeeina.app_covid;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Vector;

public class MyCountryStatsViewAdapter extends RecyclerView.Adapter<MyCountryStatsViewAdapter.MyViewHolder> {

    Context context;
    Vector<String> countyNames;
    Vector<Integer> totalCases;
    Vector<Integer> newCases;
    Vector<Double> infectionRate;

    public MyCountryStatsViewAdapter(Context context, Vector<String> countyNames, Vector<Integer> totalCases, Vector<Integer> newCases, Vector<Double> infectionRate) {
        this.context = context;
        this.countyNames = countyNames;
        this.totalCases = totalCases;
        this.newCases = newCases;
        this.infectionRate = infectionRate;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =  inflater.inflate(R.layout.country_stats_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.countyNameText.setText(countyNames.elementAt(position));
        holder.totalCasesText.setText(totalCases.elementAt(position).toString());
        holder.newCasesText.setText(newCases.elementAt(position).toString());
        holder.infectionRateText.setText(infectionRate.elementAt(position).toString());
        if(infectionRate.elementAt(position) > 3) {
            holder.itemView.findViewById(R.id.cardLayout).setBackgroundColor(Color.parseColor("#ffbdb0"));
        }
        else if(infectionRate.elementAt(position) > 1.5) {
            holder.itemView.findViewById(R.id.cardLayout).setBackgroundColor(Color.parseColor("#f7f0ab"));
        }
        else {
            holder.itemView.findViewById(R.id.cardLayout).setBackgroundColor(Color.parseColor("#b0ffc5"));
        }
    }

    @Override
    public int getItemCount() {
        return totalCases.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView totalCasesText, newCasesText, infectionRateText, countyNameText;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            countyNameText = itemView.findViewById(R.id.county_name);
            totalCasesText = itemView.findViewById(R.id.number_infection_rate);
            newCasesText = itemView.findViewById(R.id.number_new_cases);
            infectionRateText = itemView.findViewById(R.id.number_total_cases);
        }
    }
}
