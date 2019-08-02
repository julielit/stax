package com.solvd.javalab.stax;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private ArrayList<Address> addresses;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFname() {
        return firstname;
    }
    public void setFname(String firstname) {
        this.firstname = firstname;
    }
    public String getLname() {
        return lastname;
    }
    public void setLname(String lastname) {
        this.lastname = lastname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(ArrayList<Address> addresses) {
        this.addresses = addresses;
    }

    @Override

    public String toString() {
        return "User: ID = " + this.id + "; Name = " + this.firstname + " " + this.lastname + "; Email = " + this.email +
                "; Phone Number = " + this.phone + "; " + this.addresses;
    }

}

