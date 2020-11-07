package com.codeeina.app_covid;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;
import java.util.Vector;

public class CountryStatsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_country_stats, container, false);
        InputStream inputStream = getResources().openRawResource(R.raw.out);
        CSVReader csvFile = new CSVReader(inputStream);
        List<String> statsList = csvFile.read();
        Vector<Integer> totalCases = new Vector<>();
        Vector<Integer> newCases = new Vector<>();
        Vector<Double> infectionRate = new Vector<>();

        TextView totalCasesText = view.findViewById(R.id.county_total_cases);
        TextView newCasesText = view.findViewById(R.id.county_new_cases);
        TextView infectionRateText = view.findViewById(R.id.county_infection_rate);

        for(int i = 1; i < statsList.size(); i++) {
            String manipulatedString = statsList.get(i).replaceAll("[\\[\\],]","");
            String[] params = manipulatedString.split(" ");
            int totalCasesInt = Integer.parseInt(params[1]);
            totalCases.add(totalCasesInt);
            int newCasesInt = Integer.parseInt(params[2]);
            newCases.add(newCasesInt);
            double infectionRateDouble = Double.parseDouble(params[3]);
            infectionRate.add(infectionRateDouble);
        }

        int sum = 0;
        for(Integer x : totalCases)
            sum += x;
        totalCasesText.setText(String.valueOf(sum));

        sum = 0;
        for(Integer x : newCases)
            sum += x;
        newCasesText.setText(String.valueOf(sum));

        double dsum = 0;
        for(Double x : infectionRate)
            dsum += x;
        dsum /= infectionRate.size();

        infectionRateText.setText(String.format("%.2f",dsum));
        Button goToDetailed = view.findViewById(R.id.goToDetailedStats);
        goToDetailed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment nextFrag = new CountryStatsDetailedFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .remove(CountryStatsFragment.this)
                        .replace(R.id.fragment_layout, nextFrag)
                        .addToBackStack(null).commit();
            }
        });
        return view;
    }
}