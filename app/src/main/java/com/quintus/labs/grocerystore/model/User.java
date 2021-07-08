package com.quintus.labs.grocerystore.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Grocery App
 * https://github.com/quintuslabs/GroceryStore
 * Created on 18-Feb-2019.
 * Created by : Santosh Kumar Dash:- http://santoshdash.epizy.com
 */
public class User {
    @SerializedName("id")
    @Expose
    String id;
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("email")
    @Expose
    String email;
    @SerializedName("phone")
    @Expose
    String phone;
    @SerializedName("address")
    @Expose
    String address;
    @SerializedName("photo")
    @Expose
    String photo;
    @SerializedName("position")
    @Expose
    String position;
    @SerializedName("access_token")
    @Expose
    String access_token;


    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", photo='" + photo + '\'' +
                ", position='" + position + '\'' +
                ", access_token='" + access_token + '\'' +
                '}';
    }

    //    public User(String id, String name, String email, String mobile, String password) {
//        this.id = id;
//        this.name = name;
//        this.email = email;
//        this.mobile = mobile;
//        this.password = password;
//    }
//
//    public User(String id, String name, String email, String mobile, String photo, String password) {
//        this.id = id;
//        this.name = name;
//        this.email = email;
//        this.mobile = mobile;
//        this.photo = photo;
//        this.password = password;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
