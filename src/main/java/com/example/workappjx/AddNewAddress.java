package com.example.workappjx;

public class AddNewAddress {
    int id;
    String address;

    public AddNewAddress(int id, String address) {
        this.id = id;
        this.address = address;
    }

    public int getID(){
        return id;
    }

    public String getAddress(){
        return address;
    }
}
