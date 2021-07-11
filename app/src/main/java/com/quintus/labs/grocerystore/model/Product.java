package com.quintus.labs.grocerystore.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Grocery App
 * https://github.com/quintuslabs/GroceryStore
 * Created on 18-Feb-2019.
 * Created by : Santosh Kumar Dash:- http://santoshdash.epizy.com
 */
public class Product {
    @SerializedName("id")
    @Expose
    String id;
    @SerializedName("categoryId")
    @Expose
    String categoryId;
    @SerializedName("title")
    @Expose
    String title;
    @SerializedName("description")
    @Expose
    String description;
    @SerializedName("unit")
    @Expose
    String unit;

    String currency = "đ";

    @SerializedName("price")
    @Expose
    String price;
    @SerializedName("discount")
    @Expose
    String discount;
    @SerializedName("image")
    @Expose
    String image;
    @SerializedName("quantity")
    @Expose
    String quantity;
    @SerializedName("note")
    @Expose
    String note;

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", unit='" + unit + '\'' +
                ", currency='" + currency + '\'' +
                ", price='" + price + '\'' +
                ", discount='" + discount + '\'' +
                ", image='" + image + '\'' +
                ", quantity='" + quantity + '\'' +
                ", note='" + note + '\'' +
                '}';
    }

    public Product() {
    }

    public Product(String id, String categoryId, String title, String description, String unit, String price, String discount, String image) {
        this.id = id;
        this.categoryId = categoryId;
        this.title = title;
        this.description = description;
        this.unit = unit;
        this.price = price;
        this.discount = discount;
        this.image = image;
    }

    public Product(String id, String categoryId, String title, String description, String unit, String currency, String price, String discount, String image, String quantity, String note) {
        this.id = id;
        this.categoryId = categoryId;
        this.title = title;
        this.description = description;
        this.unit = unit;
        this.currency = currency;
        this.price = price;
        this.discount = discount;
        this.image = image;
        this.quantity = quantity;
        this.note = note;
    }

    //    public Product(String id, String categoryId, String title, String unit, String price, String discount, String image, String quantity) {
//        this.id = id;
//        this.categoryId = categoryId;
//        this.title = title;
//        this.unit = unit;
//        this.price = price;
//        this.discount = discount;
//        this.image = image;
//        this.quantity = quantity;
//    }

    //
//    public Product(String id, String categoryId, String title, String description, String attribute, String currency, String price, String discount, String image) {
//        this.id = id;
//        this.categoryId = categoryId;
//        this.title = title;
//        this.description = description;
//        this.attribute = attribute;
//        this.currency = "đ";
//        this.price = price;
//        this.discount = discount;
//        this.image = image;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCurrency() {
        return "đ";
    }

    public void setCurrency(String currency) {
        this.currency = "đ";
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
