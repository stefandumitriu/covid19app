package com.codeeina.app_covid;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.InputStream;
import java.util.List;
import java.util.Vector;

public class CountryStatsDetailedFragment extends Fragment {

    FragmentActivity mActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Activity) {
            mActivity = (FragmentActivity) context;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_country_stats_detailed, container, false);

        InputStream inputStream = getResources().openRawResource(R.raw.out);
        CSVReader csvFile = new CSVReader(inputStream);
        List<String> statsList = csvFile.read();
        RecyclerView listStatsItems = (RecyclerView) rootView.findViewById(R.id.list_country_stats);

        Vector<String> countyNames = new Vector<>();
        Vector<Integer> totalCases = new Vector<>();
        Vector<Integer> newCases = new Vector<>();
        Vector<Double> infectionRate = new Vector<>();
        for(int i = 1; i < statsList.size(); i++) {
            String manipulatedString = statsList.get(i).replaceAll("[\\[\\],]","");
            String[] params = manipulatedString.split(" ");
            countyNames.add(params[0]);
            int totalCasesInt = Integer.parseInt(params[1]);
            totalCases.add(totalCasesInt);
            int newCasesInt = Integer.parseInt(params[2]);
            newCases.add(newCasesInt);
            double infectionRateDouble = Double.parseDouble(params[3]);
            infectionRate.add(infectionRateDouble);
        }
        MyCountryStatsViewAdapter myAdapter = new MyCountryStatsViewAdapter(mActivity, countyNames, totalCases, newCases, infectionRate);
        listStatsItems.setLayoutManager(new LinearLayoutManager(mActivity));
        listStatsItems.setAdapter(myAdapter);
        return rootView;
    }
}