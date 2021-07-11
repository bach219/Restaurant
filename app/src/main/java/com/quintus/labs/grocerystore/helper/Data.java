package com.quintus.labs.grocerystore.helper;

import com.google.gson.JsonObject;
import com.quintus.labs.grocerystore.model.Category;
import com.quintus.labs.grocerystore.model.Offer;
import com.quintus.labs.grocerystore.model.Product;
import com.quintus.labs.grocerystore.retrofit.APIClient;
import com.quintus.labs.grocerystore.retrofit.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Grocery App
 * https://github.com/quintuslabs/GroceryStore
 * Created on 18-Feb-2019.
 * Created by : Santosh Kumar Dash:- http://santoshdash.epizy.com
 */
public final class Data {

    private final static Data INSTANCE = new Data();
    private Data() {}
    public static Data getInstance() {
        return INSTANCE;
    }

    private List<Category> categoryList;
    private List<Product> productList;
    private List<Product> newList;
    private List<Product> popularList;
    private List<Offer> offerList;

    public List<Category> getCategoryList() {
        return this.categoryList;
    }

    public List<Product> getProductList() {
        return this.productList;
    }

    public List<Product> getNewList() {
        return this.newList;
    }

    public List<Product> getPopularList() {
        return this.popularList;
    }

    public List<Offer> getOfferList() {
        return this.offerList;
    }

    public void setCategoryList(List<Category> categoryList) {
         this.categoryList = categoryList;
    }

    public void setProductList(List<Product> productList) {
         this.productList = productList;
    }

    public void setNewList(List<Product> newList) {
        this.newList = newList;
    }

    public void setPopularList(List<Product> popularList) {
         this.popularList = popularList;
    }

    public void setOfferList(List<Offer> offerList) {
         this.offerList = offerList;
    }
}
