package com.codeeina.app_covid;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class GlobalStatsActivity extends AppCompatActivity {
    Vector<String> countryNames = new Vector<>();
    Vector<Integer> totalCases = new Vector<>();
    Vector<Integer> newCases = new Vector<>();
    Vector<Integer> casesPerMillion = new Vector<>();
    MyGlobalStatsViewAdapter myAdapter;
    RecyclerView listStatsItems;
    List<GlobalEntryItem> entryList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.global_stats);

        InputStream inputStream = getResources().openRawResource(R.raw.out_global);
        CSVReaderGlobalData csvFile = new CSVReaderGlobalData(inputStream);
        List<String> globalStatsList = csvFile.read();

        for(int i = 1; i < globalStatsList.size(); i++) {
            String manipulatedString = globalStatsList.get(i).replaceAll("[\\[\\],]","");
            String[] params = manipulatedString.split(" ");
            String countryName = "";
            int idx = 0;
            while(!params[idx].matches("-?\\d+(\\.\\d+)?")) {
                if(!countryName.equals(""))
                    countryName += " ";
                countryName += params[idx];
                idx++;
            }
            countryNames.add(countryName);
            int totalCasesInt = Integer.parseInt(params[idx++]);
            totalCases.add(totalCasesInt);
            int newCasesInt = Integer.parseInt(params[idx++]);
            newCases.add(newCasesInt);
            int casesPerMillionInt = Integer.parseInt(params[idx]);
            casesPerMillion.add(casesPerMillionInt);
        }
        listStatsItems = (RecyclerView) findViewById(R.id.list_global_stats);
        entryList = new ArrayList<>();
        for(int i = 0; i < countryNames.size(); i++) {
            entryList.add(new GlobalEntryItem(countryNames.elementAt(i), totalCases.elementAt(i), newCases.elementAt(i), casesPerMillion.elementAt(i), i + 1));
        }
        myAdapter = new MyGlobalStatsViewAdapter(this, entryList);
        listStatsItems.setLayoutManager(new LinearLayoutManager(this));
        listStatsItems.setAdapter(myAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_global, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                myAdapter.getFilter().filter(newText);
                String userInput = newText.toLowerCase();
                ArrayList<GlobalEntryItem> newList = new ArrayList<>();
                for(GlobalEntryItem entry : entryList) {
                    if(entry.getCountryName().toLowerCase().contains(userInput))
                        newList.add(entry);
                }

                myAdapter.updateList(newList);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
