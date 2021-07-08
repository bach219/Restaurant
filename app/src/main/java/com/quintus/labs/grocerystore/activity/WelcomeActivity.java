package com.quintus.labs.grocerystore.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.quintus.labs.grocerystore.R;
import com.quintus.labs.grocerystore.adapter.ViewPagerAdapter;
import com.quintus.labs.grocerystore.model.User;
import com.quintus.labs.grocerystore.retrofit.APIClient;
import com.quintus.labs.grocerystore.retrofit.APIInterface;
import com.quintus.labs.grocerystore.util.localstorage.LocalStorage;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Grocery App
 * https://github.com/quintuslabs/GroceryStore
 * Created on 18-Feb-2019.
 * Created by : Santosh Kumar Dash:- http://santoshdash.epizy.com
 */
public class WelcomeActivity extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    Timer timer;
    int page_position = 0;
    LocalStorage localStorage;
    private int dotscount;
    private ImageView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        localStorage = new LocalStorage(getApplicationContext());
        setContentView(R.layout.activity_welcome);

        if (localStorage.isUserLoggedIn()) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

    }

    public void onLetsClicked(View view) {
        startActivity(new Intent(getApplicationContext(), LoginRegisterActivity.class));
        finish();
        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
    }
}