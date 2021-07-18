package com.quintus.labs.grocerystore.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.quintus.labs.grocerystore.R;
import com.quintus.labs.grocerystore.activity.MainActivity;
import com.quintus.labs.grocerystore.activity.ProductActivity;
import com.quintus.labs.grocerystore.adapter.CategoryAdapter;
import com.quintus.labs.grocerystore.adapter.HomeSliderAdapter;
import com.quintus.labs.grocerystore.adapter.NewProductAdapter;
import com.quintus.labs.grocerystore.adapter.PopularProductAdapter;
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
/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ViewPager viewPager;
    LinearLayout sliderDotspanel;
    Timer timer;
    int page_position = 0;
    Data data;
    private int dotscount;
    private ImageView[] dots;
//    private List<Category> categoryList = new ArrayList<>();
    private RecyclerView recyclerView, nRecyclerView, pRecyclerView;
    private CategoryAdapter mAdapter;
    private NewProductAdapter nAdapter;
    private PopularProductAdapter pAdapter;
    private Integer[] images = {R.drawable.slider1, R.drawable.slider2, R.drawable.slider3, R.drawable.slider4, R.drawable.slider5};

    public HomeFragment() {
        // Required empty public constructor
    }


    List<Category> categoryList = new ArrayList<>();
    List<Product> newList =new ArrayList<>();
    List<Product> popularList =new ArrayList<>();
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        data = Data.getInstance();

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<List<Product>> callProduct = apiInterface.getPopularList();
        callProduct.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.code() == 200) {
                    pRecyclerView = view.findViewById(R.id.popular_product_rv);
                    pAdapter = new PopularProductAdapter(response.body(), getContext(), "Home");
                    RecyclerView.LayoutManager pLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                    pRecyclerView.setLayoutManager(pLayoutManager);
                    pRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    pRecyclerView.setAdapter(pAdapter);
                }
                else
                    Toast.makeText(getActivity(), "Xảy ra lỗi trạng thái server" + response.body().toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

                Log.d("test pro activity onFailure call: ", call.toString());
                Log.d("test pro activity onFailure Throwable: ", t.getMessage());

                Toast.makeText(getActivity(), "Xảy ra lỗi kết nối server", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });

        callProduct = apiInterface.getNewList();
        callProduct.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.code() == 200) {
                    nRecyclerView = view.findViewById(R.id.new_product_rv);
                    nAdapter = new NewProductAdapter(response.body(), getContext(), "Home");
                    RecyclerView.LayoutManager nLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                    nRecyclerView.setLayoutManager(nLayoutManager);
                    nRecyclerView.setItemAnimator(new DefaultItemAnimator());
                    nRecyclerView.setAdapter(nAdapter);

                }
                else
                    Toast.makeText(getActivity(), "Xảy ra lỗi trạng thái server" + response.body().toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

                Log.d("test pro activity onFailure call: ", call.toString());
                Log.d("test pro activity onFailure Throwable: ", t.getMessage());

                Toast.makeText(getActivity(), "Xảy ra lỗi kết nối server", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });


        Call<List<Category>> callCategory = apiInterface.doGetCategoryList();
        callCategory.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if(response.code() == 200) {
                    Data data = Data.getInstance();
                    data.setCategoryList(response.body());
                    recyclerView = view.findViewById(R.id.category_rv);
                    mAdapter = new CategoryAdapter(response.body(), getContext(), "Home");
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(mAdapter);
                }
                else
                    Toast.makeText(getActivity(), "Xảy ra lỗi trạng thái server" + response.body().toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

                Log.d("test pro activity onFailure call: ", call.toString());
                Log.d("test pro activity onFailure Throwable: ", t.getMessage());

                Toast.makeText(getActivity(), "Xảy ra lỗi kết nối server", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });

        return view;
    }


    public void onLetsClicked(View view) {
        startActivity(new Intent(getContext(), MainActivity.class));
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Thực đơn");

        TextView categories_list = view.findViewById(R.id.categories_list);
        categories_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ProductActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                getContext().startActivity(intent);
            }
        });

        TextView new_list = view.findViewById(R.id.new_list);
        new_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ProductActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                getContext().startActivity(intent);
            }
        });

        TextView popular_list = view.findViewById(R.id.popular_list);
        popular_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ProductActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                getContext().startActivity(intent);
            }
        });
    }
}
