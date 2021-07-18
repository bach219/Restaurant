package com.quintus.labs.grocerystore.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.quintus.labs.grocerystore.R;
import com.quintus.labs.grocerystore.helper.Converter;
import com.quintus.labs.grocerystore.model.Cart;
import com.quintus.labs.grocerystore.model.Product;
import com.quintus.labs.grocerystore.retrofit.APIClient;
import com.quintus.labs.grocerystore.retrofit.APIInterface;
import com.quintus.labs.grocerystore.retrofit.Host;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Grocery App
 * https://github.com/quintuslabs/GroceryStore
 * Created on 18-Feb-2019.
 * Created by : Santosh Kumar Dash:- http://santoshdash.epizy.com
 */
public class ProductViewActivity extends BaseActivity {
    private static int cart_count = 0;
    public TextView quantity, inc, dec;
    String _id, _title, _image, _description, _price, _currency, _discount, _unit, _qty;
    TextView id, title, description, price, currency, discount, unit, qty;
    ImageView imageView;
    ProgressBar progressBar;
    LinearLayout addToCart, share;
    RelativeLayout quantityLL;
    List<Cart> cartList = new ArrayList<>();
    int cartId;
    Cart cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);


        Intent intent = getIntent();

        _id = intent.getStringExtra("id");
//        _title = intent.getStringExtra("title");
//        _image = intent.getStringExtra("image");
//        _description = intent.getStringExtra("description");
//        _price = intent.getStringExtra("price");
//        _currency = intent.getStringExtra("currency");
//        _discount = intent.getStringExtra("discount");
//        _unit = intent.getStringExtra("unit");

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<JsonObject> callProduct = apiInterface.getProductDetail(_id);
        callProduct.enqueue(new retrofit2.Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.code() == 200) {
                    String jsonString = response.body().toString();
                    final Gson gson = new Gson();
                    Product product = gson.fromJson(jsonString, Product.class);

                    _title = product.getTitle();
                    _image = product.getImage();
                    _description = product.getDescription();
                    _price = product.getPrice();
                    _currency = product.getCurrency();
                    _discount = product.getDiscount();
                    _unit = product.getUnit();
                    _qty = product.getQuantity();
                    _description = product.getDescription();


                    getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFFFFF")));
                    changeActionBarTitle(getSupportActionBar());
                    getSupportActionBar().setDisplayShowHomeEnabled(true);
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    final Drawable upArrow = getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp);
                    //upArrow.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
                    getSupportActionBar().setHomeAsUpIndicator(upArrow);

                    cart_count = cartCount();

                    title = findViewById(R.id.apv_title);
                    qty = findViewById(R.id.apv_qty);
                    description = findViewById(R.id.apv_description);
                    currency = findViewById(R.id.apv_currency);
                    price = findViewById(R.id.apv_price);
                    unit = findViewById(R.id.apv_unit);
                    discount = findViewById(R.id.apv_discount);
                    imageView = findViewById(R.id.apv_image);
                    progressBar = findViewById(R.id.progressbar);
                    addToCart = findViewById(R.id.add_to_cart_ll);
                    share = findViewById(R.id.apv_share);
                    quantityLL = findViewById(R.id.quantity_rl);
                    quantity = findViewById(R.id.quantity);
                    inc = findViewById(R.id.quantity_plus);
                    dec = findViewById(R.id.quantity_minus);

                    cartList = getCartList();
                    title.setText(_title);
                    qty.setText(_qty + " " + _unit);
                    description.setText(_description);
                    price.setText(_price);
                    currency.setText(_currency);
                    unit.setText(_unit);
                    discount.setText(_discount);
                    Log.d(TAG, "Discount : " + _discount);
                    if (_discount == null || _discount.length() == 0 || _discount == "" || _discount.contains("0")) {
                        discount.setVisibility(View.INVISIBLE);
                        discount.setVisibility(View.GONE);
                    } else {
//            discount.setVisibility(View.GONE);
                        discount.setVisibility(View.VISIBLE);
                    }
                    if (_image != null) {
                        Picasso.get().load(Host.host + _image).error(R.drawable.no_image).into(imageView, new Callback() {
                            @Override
                            public void onSuccess() {
                                progressBar.setVisibility(View.GONE);
                            }

                            @Override
                            public void onError(Exception e) {
                                progressBar.setVisibility(View.GONE);
                            }
                        });
                    }

                    if (!cartList.isEmpty()) {
                        for (int i = 0; i < cartList.size(); i++) {
                            if (cartList.get(i).getId().equalsIgnoreCase(_id)) {
                                addToCart.setVisibility(View.GONE);
                                quantityLL.setVisibility(View.VISIBLE);
                                quantity.setText(cartList.get(i).getQuantity());
                                cartId = i;

                            }
                        }
                    }


                    share.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String userEntry = _image + "\n" + _title + "\n" + _description + "\n" + _unit + "-" + _currency + _price + "(" + _discount + ")";

                            Intent textShareIntent = new Intent(Intent.ACTION_SEND);
                            textShareIntent.putExtra(Intent.EXTRA_TEXT, userEntry);
                            textShareIntent.setType("text/plain");
                            startActivity(textShareIntent);
                        }
                    });


                    addToCart.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            _price = price.getText().toString();

                            cart = new Cart(_id, _title, _image, _currency, _price, _unit, "1", _price);
                            cartList.add(cart);
                            String cartStr = gson.toJson(cartList);
                            Log.d("CART", cartStr);
                            localStorage.setCart(cartStr);
                            onAddProduct();
                            addToCart.setVisibility(View.GONE);
                            quantityLL.setVisibility(View.VISIBLE);
                        }
                    });


                    inc.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            _price = price.getText().toString();


                            // int total_item = Integer.parseInt(cartList.get(cartId).getQuantity());
                            int total_item = Integer.parseInt(quantity.getText().toString());
                            total_item++;
                            Log.d("totalItem", total_item + "");
                            quantity.setText(total_item + "");
                            String subTotal = String.valueOf(Integer.parseInt(_price) * total_item);
                            cartList.get(cartId).setQuantity(quantity.getText().toString());
                            cartList.get(cartId).setSubTotal(subTotal);
                            cartList.get(cartId).setUnit(unit.getText().toString());
                            cartList.get(cartId).setPrice(_price);
                            String cartStr = gson.toJson(cartList);
//                Log.d("CART", cartStr);
                            localStorage.setCart(cartStr);
                        }
                    });

                    dec.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            _price = price.getText().toString();

                            //int total_item = Integer.parseInt(quantity.getText().toString());
                            int total_item = Integer.parseInt(quantity.getText().toString());
                            if (total_item != 1) {
                                total_item--;
                                quantity.setText(total_item + "");
                                Log.d("totalItem", total_item + "");
                                String subTotal = String.valueOf(Integer.parseInt(_price) * total_item);


                                cartList.get(cartId).setQuantity(quantity.getText().toString());
                                cartList.get(cartId).setSubTotal(subTotal);
                                cartList.get(cartId).setUnit(unit.getText().toString());
                                cartList.get(cartId).setPrice(_price);
                                String cartStr = gson.toJson(cartList);
//                    Log.d("CART", cartStr);
                                localStorage.setCart(cartStr);
                            }
                        }
                    });
                }
                else
                    Toast.makeText(getApplicationContext(), "Xảy ra lỗi trạng thái server" + response.body().toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

                Log.d("test pro activity onFailure call: ", call.toString());
                Log.d("test pro activity onFailure Throwable: ", t.getMessage());

                Toast.makeText(getApplicationContext(), "Xảy ra lỗi kết nối server", Toast.LENGTH_LONG).show();
                call.cancel();
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
        tv.setText(_title); // ActionBar title text
        tv.setTextSize(20);

        // Set the text color of TextView to red
        // This line change the ActionBar title text color
        tv.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

        // Set the ActionBar display option
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        // Finally, set the newly created TextView as ActionBar custom view
        actionBar.setCustomView(tv);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here

                Intent intent = new Intent(ProductViewActivity.this, MainActivity.class);
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
        menuItem.setIcon(Converter.convertLayoutToImage(ProductViewActivity.this, cart_count, R.drawable.ic_shopping_basket));
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
