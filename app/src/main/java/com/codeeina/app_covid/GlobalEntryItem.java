package com.codeeina.app_covid;

public class GlobalEntryItem {
    private String countryName;
    private int totalCases;
    private int newCases;
    private int casesPerMillion;
    private int entryNumber;

    public GlobalEntryItem(String countryName, int totalCases, int newCases, int casesPerMillion, int entryNumber) {
        this.countryName=  countryName;
        this.totalCases = totalCases;
        this.newCases = newCases;
        this.casesPerMillion = casesPerMillion;
        this.entryNumber = entryNumber;
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
    public int getEntryNumber() {
        return entryNumber;
    }
}
