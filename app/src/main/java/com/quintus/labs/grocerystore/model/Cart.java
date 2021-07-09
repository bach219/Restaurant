package com.quintus.labs.grocerystore.model;

/**
 * Grocery App
 * https://github.com/quintuslabs/GroceryStore
 * Created on 18-Feb-2019.
 * Created by : Santosh Kumar Dash:- http://santoshdash.epizy.com
 */
public class Cart {
    String id;
    String image;
    String title;
    String currency = "đ";
    String price;
    String unit;
    String quantity;
    String subTotal;


    public Cart() {
    }


    public Cart(String id, String title, String image, String currency, String price, String unit, String quantity, String subTotal) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.currency = "đ";
        this.price = price;
        this.unit = unit;
        this.quantity = quantity;
        this.subTotal = subTotal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public String getCurrency() {
        return "đ";
    }

    public void setCurrency(String currency) {
        this.currency = "đ";
    }
}
