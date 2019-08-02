package com.solvd.javalab.stax;

public class Address {
    private int id;
    private String country;
    private String city;
    private String addressLine;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    @Override
    public String toString() {
        return "Address ID: " + this.id + " Country: " + this.country + " City: " + this.city +
                " Street: " + this.addressLine;
    }

}

