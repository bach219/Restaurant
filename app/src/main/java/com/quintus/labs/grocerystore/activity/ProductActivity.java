package com.quintus.labs.grocerystore.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.quintus.labs.grocerystore.R;
import com.quintus.labs.grocerystore.adapter.ProductAdapter;
import com.quintus.labs.grocerystore.helper.Converter;
import com.quintus.labs.grocerystore.helper.Data;
import com.quintus.labs.grocerystore.model.Category;
import com.quintus.labs.grocerystore.model.Product;
import com.quintus.labs.grocerystore.retrofit.APIClient;
import com.quintus.labs.grocerystore.retrofit.APIInterface;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Grocery App
 * https://github.com/quintuslabs/GroceryStore
 * Created on 18-Feb-2019.
 * Created by : Santosh Kumar Dash:- http://santoshdash.epizy.com
 */
public class ProductActivity extends BaseActivity {
    private static int cart_count = 0;
    Data data;
    ProductAdapter mAdapter;
    String Tag = "List";
    private RecyclerView recyclerView;
    private List<String> categories,status;
    private ArrayAdapter categoryAdapter,statusAdapter;
    private Spinner catSpinner,statusSpinner;
    private String catergoryString,statusString;
    private TextView filter;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
        changeActionBarTitle(getSupportActionBar());
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
        //upArrow.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        cart_count = cartCount();
        recyclerView = findViewById(R.id.product_rv);
        data = Data.getInstance();
        setUpRecyclerView();



        Data data = Data.getInstance();
//        List<String> listCate = data.getCategoryList().stream().map(cate -> cate.getTitle()).collect(Collectors.toList());
        List<String> listCate = new ArrayList<>();
        for (Category p : data.getCategoryList()) {
            listCate.add(p.getTitle());
        }
        catSpinner = findViewById(R.id.cat_spinner);
        statusSpinner = findViewById(R.id.status_spinner);
        filter = findViewById(R.id.filter);
        categories = new ArrayList<>();
        status = new ArrayList<>();
        categories.add("Lo???i m??n");
        categories.addAll(listCate);
        status.add("T??nh tr???ng");
        status.add("C??n h??ng");
        status.add("H???t h??ng");
        categoryAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,categories);
        statusAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,status);
        catSpinner.setAdapter(categoryAdapter);
        statusSpinner.setAdapter(statusAdapter);


        catSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                catergoryString = (String) parent.getItemAtPosition(position);
                catSpinner.setSelection(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                catergoryString = "";

            }
        });
        statusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                statusString = (String) parent.getItemAtPosition(position);
                statusSpinner.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                statusString = "";

            }
        });

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
                Call<List<Product>> callProduct = apiInterface.doGetProductList();
                callProduct.enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                        if(response.code() == 200) {
//                            Data data = Data.getInstance();
                            List<Product> listFilter = new ArrayList<>();
                            if(catergoryString.equalsIgnoreCase("Lo???i m??n") && statusString.equalsIgnoreCase("T??nh tr???ng"))
                            {
                                listFilter = response.body();
                            }
                            else
                            {
                                for(Product product : response.body())
                                {
                                    if(catergoryString.equalsIgnoreCase("Lo???i m??n") && !TextUtils.isEmpty(statusString))
                                    {
                                        if(statusString.equalsIgnoreCase("C??n h??ng")){
                                            if(!product.getQuantity().equalsIgnoreCase("0"))
                                                listFilter.add(product);
                                        }else{
                                            if(product.getQuantity().equalsIgnoreCase("0"))
                                                listFilter.add(product);
                                        }

                                    }
                                    else if(statusString.equals("T??nh tr???ng") && !TextUtils.isEmpty(catergoryString))
                                    {
                                        if(product.getCategoryName().equalsIgnoreCase(catergoryString))
                                            listFilter.add(product);
                                    }
                                    else
                                    {
                                        if(product.getCategoryName().equalsIgnoreCase(catergoryString) && statusString.equalsIgnoreCase("C??n h??ng")){
                                            if(!product.getQuantity().equalsIgnoreCase("0"))
                                                listFilter.add(product);
                                        }
                                        if(product.getCategoryName().equalsIgnoreCase(catergoryString) && statusString.equalsIgnoreCase("H???t h??ng")){
                                            if(product.getQuantity().equalsIgnoreCase("0"))
                                                listFilter.add(product);
                                        }

                                    }

                                }
                            }
                            mAdapter = new ProductAdapter(listFilter, ProductActivity.this, Tag);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                            recyclerView.setLayoutManager(mLayoutManager);
                            recyclerView.setItemAnimator(new DefaultItemAnimator());
                            recyclerView.setAdapter(mAdapter);
                        }
                        else
                            Toast.makeText(getApplicationContext(), "X???y ra l???i tr???ng th??i server" + response.body().toString(), Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onFailure(Call<List<Product>> call, Throwable t) {

                        Log.d("test pro activity onFailure call: ", call.toString());
                        Log.d("test pro activity onFailure Throwable: ", t.getMessage());

                        Toast.makeText(getApplicationContext(), "X???y ra l???i k???t n???i server", Toast.LENGTH_LONG).show();
                        call.cancel();
                    }
                });

//                Data data = Data.getInstance();
//                List<Product> listFilter = new ArrayList<>();
//                if(catergoryString.equalsIgnoreCase("Lo???i m??n") && statusString.equalsIgnoreCase("T??nh tr???ng"))
//                {
//                    listFilter = data.getProductList();
//                }
//                else
//                {
//                    for(Product product : data.getProductList())
//                    {
//                        if(catergoryString.equalsIgnoreCase("Lo???i m??n") && !TextUtils.isEmpty(statusString))
//                        {
//                            if(statusString.equalsIgnoreCase("C??n h??ng")){
//                                if(!product.getQuantity().equalsIgnoreCase("0"))
//                                    listFilter.add(product);
//                            }else{
//                                if(product.getQuantity().equalsIgnoreCase("0"))
//                                    listFilter.add(product);
//                            }
//
//                        }
//                        else if(statusString.equals("T??nh tr???ng") && !TextUtils.isEmpty(catergoryString))
//                        {
//                            if(product.getCategoryName().equalsIgnoreCase(catergoryString))
//                                listFilter.add(product);
//                        }
//                        else
//                        {
//                            if(product.getCategoryName().equalsIgnoreCase(catergoryString) && statusString.equalsIgnoreCase("C??n h??ng")){
//                                if(!product.getQuantity().equalsIgnoreCase("0"))
//                                    listFilter.add(product);
//                            }
//                            if(product.getCategoryName().equalsIgnoreCase(catergoryString) && statusString.equalsIgnoreCase("H???t h??ng")){
//                                if(product.getQuantity().equalsIgnoreCase("0"))
//                                    listFilter.add(product);
//                            }
//
//                        }
//
//                    }
//                }
//                mAdapter = new ProductAdapter(listFilter, ProductActivity.this, Tag);
//                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//                recyclerView.setLayoutManager(mLayoutManager);
//                recyclerView.setItemAnimator(new DefaultItemAnimator());
//                recyclerView.setAdapter(mAdapter);
//                mainRecyclerAdapter.setWinnerDetails(w);
            }
        });

    }

    private void changeActionBarTitle(ActionBar actionBar) {
        // Create a LayoutParams for TextView
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                RelativeLayout.LayoutParams.WRAP_CONTENT); // Height of TextView
        TextView tv = new TextView(getApplicationContext());
        // Apply the layout parameters to TextView widget
        tv.setLayoutParams(lp);
        tv.setGravity(Gravity.CENTER);
        tv.setTypeface(null, Typeface.BOLD);
        // Set text to display in TextView
        tv.setText("Danh s??ch m??n"); // ActionBar title text
        tv.setTextSize(20);

        // Set the text color of TextView to red
        // This line change the ActionBar title text color
        tv.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

        // Set the ActionBar display option
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        // Finally, set the newly created TextView as ActionBar custom view
        actionBar.setCustomView(tv);
    }

    private void setUpRecyclerView() {
//        data = new Data();
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<List<Product>> callUser = apiInterface.doGetProductList();
        callUser.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.code() == 200) {

                    mAdapter = new ProductAdapter(response.body(), ProductActivity.this, Tag);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(mAdapter);
                }
                else
                    Toast.makeText(ProductActivity.this, "X???y ra l???i tr???ng th??i server" + response.body().toString(), Toast.LENGTH_LONG).show();

                }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

                Log.d("test pro activity onFailure call: ", call.toString());
                Log.d("test pro activity onFailure Throwable: ", t.getMessage());

                Toast.makeText(ProductActivity.this, "X???y ra l???i k???t n???i server", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });

    }

    private void setUpGridRecyclerView() {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<List<Product>> callUser = apiInterface.doGetProductList();
        callUser.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.code() == 200){
                    mAdapter = new ProductAdapter(response.body(), ProductActivity.this, Tag);
                    RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(mAdapter);
                }
                else
                    Toast.makeText(ProductActivity.this, "X???y ra l???i tr???ng th??i server", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

                Log.d("test pro activity onFailure call: ", call.toString());
                Log.d("test pro activity onFailure Throwable: ", t.getMessage());

                Toast.makeText(ProductActivity.this, "X???y ra l???i k???t n???i server", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });

    }

    public void onToggleClicked(View view) {
        if (Tag.equalsIgnoreCase("List")) {
            Tag = "Grid";
            setUpGridRecyclerView();
        } else {
            Tag = "List";
            setUpRecyclerView();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                Intent intent = new Intent(ProductActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;

            case R.id.cart_action:
                startActivity(new Intent(getApplicationContext(), CartActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem menuItem = menu.findItem(R.id.cart_action);
        menuItem.setIcon(Converter.convertLayoutToImage(ProductActivity.this, cart_count, R.drawable.ic_shopping_basket));
        return true;
    }


    @Override
    public void onAddProduct() {
        cart_count++;
        invalidateOptionsMenu();

    }

    @Override
    public void onRemoveProduct() {
        cart_count--;
        invalidateOptionsMenu();

    }

}
