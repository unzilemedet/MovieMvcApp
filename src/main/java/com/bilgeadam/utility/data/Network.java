package com.bilgeadam.utility.data;

public class Network {

    int id;
    String name;
    public Country country;
    String officialSite;


    public Network() {
    }

    public Network(int id, String name, Country country, String officialSite) {

        this.id = id;

        this.name = name;

        this.country = country;

        this.officialSite = officialSite;

    }

    public void setId(int id) {

        this.id = id;

    }

    public int getId() {

        return this.id;

    }

    public void setName(String name) {

        this.name = name;

    }

    public String getName() {

        return this.name;

    }

    public void setCountry(Country country) {

        this.country = country;

    }

    public Country getCountry() {


        return this.country;

    }

    public void setOfficialSite(String officialSite) {

        this.officialSite = officialSite;

    }

    public String getOfficialSite() {

        return this.officialSite;

    }

}