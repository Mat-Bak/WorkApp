package com.example.workappjx;

public class AddNewAddress {
    int id;
    String address;
    boolean active;

    public AddNewAddress(int id, String address, boolean active) {
        this.id = id;
        this.address = address;
        this.active = active;
    }

    public int getID(){
        return id;
    }

    public String getAddress(){
        return address;
    }

    public boolean getActive(){
        return active;
    }
}
