package com.quintus.labs.grocerystore.retrofit;

import androidx.annotation.NonNull;

import com.google.gson.JsonObject;
import com.quintus.labs.grocerystore.model.Category;
import com.quintus.labs.grocerystore.model.Event;
import com.quintus.labs.grocerystore.model.Product;
import com.quintus.labs.grocerystore.model.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PATCH;
import retrofit2.http.Query;

public interface APIInterface {

    @Headers("Accept: application/json; charset=utf-8")
    @FormUrlEncoded
    @POST("/api/auth/employee/me")
    Call<JsonObject> me(@Header("Authorization") String token);
//
//    @FormUrlEncoded
//    @PATCH("/api/employee")
//    Call<User> updateUser(@Body User user);

    @Headers("Accept: application/json; charset=utf-8")
    @FormUrlEncoded
    @POST("/api/forgot")
    Call<JsonObject> forgot(@Field(encoded = true, value = "email") @NonNull String email);


    @Headers("Accept: application/json; charset=utf-8")
    @FormUrlEncoded
    @POST("/api/auth/employee/update")
    Call<JsonObject> updateProfile(@Header("Authorization") String token, @Field(encoded = true, value = "email") @NonNull String email, @Field("passNow") String passNow, @Field("passNew") String passNew, @Field("passVerify") String passVerify);

    @Headers("Accept: application/json; charset=utf-8")
    @FormUrlEncoded
    @POST("/api/employee/login")
    Call<JsonObject> login(@Field(encoded = true, value = "email") @NonNull String email, @Field("password") String password);

    @POST("/api/auth/employee/logout")
    Call<JsonObject> logout(@Header("Authorization") String token);


    //Category
    @GET("/api/getListCategory")
    Call<List<Category>> doGetCategoryList();

    //Product
    @GET("/api/getListProduct")
    Call<List<Product>> doGetProductList();

    @GET("/api/getNewList")
    Call<List<Product>> getNewList();

    @GET("/api/getPopularList")
    Call<List<Product>> getPopularList();

    @GET("/api/getFilterList")
    Call<List<Product>> getFilterList(@Field(value = "cate") @NonNull String cate, @Field(value = "status") @NonNull String status);


    @FormUrlEncoded
    @POST("/api/getProductDetail")
    Call<JsonObject> getProductDetail(@Field(encoded = true, value = "id") @NonNull String id);



    //Attendance
    @POST("/api/auth/employee/getAttendanceList")
    Call<List<Event>> getAttendanceList(@Header("Authorization") String token);


    @Headers("Accept: application/json; charset=utf-8")
    @FormUrlEncoded
    @POST("/api/auth/employee/attendancein")
    Call<List<Event>> createAttendanceIn(@Header("Authorization") String token, @Field(value = "type") @NonNull String type);

    @POST("/api/auth/employee/attendanceout")
    Call<List<Event>> createAttendanceOut(@Header("Authorization") String token);

}
