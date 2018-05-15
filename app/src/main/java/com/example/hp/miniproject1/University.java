package com.example.hp.miniproject1;

import java.io.Serializable;

public class University implements Serializable {
    private String Ten;
    private String Phone;
    private String Website;
    private int Image;
    private String Location;
    private String Description;
    private double Lat;
    private double Lng;

    public University(String ten, String phone, String website, int image, String location, String description, double lat, double lng) {
        Ten = ten;
        Phone = phone;
        Website = website;
        Image = image;
        Location = location;
        Description = description;
        Lat = lat;
        Lng = lng;
    }

    public double getLat() {
        return Lat;
    }

    public void setLat(double lat) {
        Lat = lat;
    }

    public double getLng() {
        return Lng;
    }

    public void setLng(double lng) {
        Lng = lng;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
