package com.codeeina.app_covid;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

public class MyCountyStatsFragment extends Fragment {
    private FusedLocationProviderClient fusedLocationProviderClient;
    int sum;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_county_stats, container, false);
        TextView countyNameText = view.findViewById(R.id.mycounty_name);
        TextView totalCasesText = view.findViewById(R.id.county_total_cases);
        TextView newCasesText = view.findViewById(R.id.county_new_cases);
        TextView infectionRateText = view.findViewById(R.id.county_infection_rate);
        TextView totalCasesPercentage = view.findViewById(R.id.totalCasesPercentage);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        InputStream inputStream = getResources().openRawResource(R.raw.out);
        CSVReader csvFile = new CSVReader(inputStream);
        List<String> statsList = csvFile.read();

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

        sum = 0;
        for(Integer x : totalCases) {
            sum +=x;
        }

        ProgressBar totalCasesBar = view.findViewById(R.id.progressBar_totalCases);

        if(getActivity().getApplicationContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if(location != null) {
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();
                        Geocoder gcd = new Geocoder(getContext(), Locale.getDefault());
                        List<Address> addressList = null;
                        try {
                            addressList = gcd.getFromLocation(latitude, longitude, 1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if(addressList.size() > 0) {
                            String currentCity = addressList.get(0).getAdminArea();
                            currentCity = currentCity.replace("Județul ","");
                            currentCity = currentCity.replace("Bucharest", "București");
                            currentCity = currentCity.replace(" County", "");
                            System.out.println(currentCity);
                            if(currentCity != null) {
                                if(countyNames.contains(currentCity)) {
                                    countyNameText.setText(currentCity);
                                    int idx = countyNames.indexOf(currentCity);
                                    double perc = sum / totalCases.get(idx);
                                    totalCasesPercentage.setText(String.format("%.2f", perc) + "% of countrywide total cases.");
                                    totalCasesBar.setProgress((int) perc);
                                    totalCasesText.setText(totalCases.get(idx).toString());
                                    newCasesText.setText(newCases.get(idx).toString());
                                    infectionRateText.setText(infectionRate.get(idx).toString());
                                }
                            }
                        }
                    }
                }
            });
        }
        else {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        ImageButton goToHome = view.findViewById(R.id.goToHome_2);
        goToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MainActivity.class));
            }
        });

        return view;
    }
}