package com.codeeina.app_covid;

import android.widget.ImageView;

public class GlobalEntryItem {
    private String countryName;
    private int totalCases;
    private int newCases;
    private int casesPerMillion;
    private int index;
    private int id;

    public GlobalEntryItem(String countryName, int totalCases, int newCases, int casesPerMillion, int index, int id) {
        this.countryName=  countryName;
        this.totalCases = totalCases;
        this.newCases = newCases;
        this.casesPerMillion = casesPerMillion;
        this.index = index;
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public int getTotalCases(){
        return totalCases;
    }

    public int getNewCases(){
        return newCases;
    }
    public int getCasesPerMillion(){
        return casesPerMillion;
    }
    public int getIndex() {
        return index;
    }
    public int getId() { return id;}
}
