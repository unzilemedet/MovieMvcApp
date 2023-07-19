package com.bilgeadam.utility.data;

public class Country {

    public String name;
    String code;
    String timezone;


    public Country() {
    }

    public Country(String name, String code, String timezone) {

        this.name = name;

        this.code = code;

        this.timezone = timezone;

    }


    public void setName(String name) {

        this.name = name;

    }

    public String getName() {

        return this.name;

    }

    public void setCode(String code) {

        this.code = code;

    }

    public String getCode() {

        return this.code;

    }

    public void setTimezone(String timezone) {

        this.timezone = timezone;

    }

    public String getTimezone() {

        return this.timezone;

    }

}