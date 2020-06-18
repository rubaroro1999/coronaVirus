package com.example.cloudcomputingproject;

public class statics {
    private String Country;
    private String TotalConfirmed;
    private String TotalDeaths;
    private String TotalRecovered;

    public void setCountry(String country) {
        Country = country;
    }

    public void setTotalConfirmed(String totalConfirmed) {
        TotalConfirmed = totalConfirmed;
    }

    public void setTotalDeaths(String totalDeaths) {
        TotalDeaths = totalDeaths;
    }

    public void setTotalRecovered(String totalRecovered) {
        TotalRecovered = totalRecovered;
    }

    public String getCountry() {
        return Country;
    }

    public String getTotalConfirmed() {
        return TotalConfirmed;
    }

    public String getTotalDeaths() {
        return TotalDeaths;
    }

    public String getTotalRecovered() {
        return TotalRecovered;
    }


    public statics(String country, String totalConfirmed, String totalDeaths, String totalRecovered) {
        Country = country;
        TotalConfirmed = totalConfirmed;
        TotalDeaths = totalDeaths;
        TotalRecovered = totalRecovered;
    }

    public statics() {
    }

}