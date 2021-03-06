package com.quintus.labs.grocerystore.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.quintus.labs.grocerystore.R;
import com.quintus.labs.grocerystore.activity.MainActivity;
import com.quintus.labs.grocerystore.model.User;
import com.quintus.labs.grocerystore.util.CustomToast;
import com.quintus.labs.grocerystore.util.Utils;
import com.quintus.labs.grocerystore.util.localstorage.LocalStorage;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.quintus.labs.grocerystore.retrofit.*;
/**
 * Grocery App
 * https://github.com/quintuslabs/GroceryStore
 * Created on 18-Feb-2019.
 * Created by : Santosh Kumar Dash:- http://santoshdash.epizy.com
 */
public class Login_Fragment extends Fragment implements OnClickListener {
    private static View view;

    private static EditText emailid, password;
    private static Button loginButton;
    private static TextView forgotPassword, signUp;
    private static CheckBox show_hide_password;
    private static LinearLayout loginLayout;
    private static Animation shakeAnimation;
    private static FragmentManager fragmentManager;

    ProgressDialog progressDialog;
    LocalStorage localStorage;
    String userString;
    User user;

    public Login_Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.login_layout, container, false);
        initViews();
        setListeners();
        return view;
    }

    // Initiate Views
    private void initViews() {
        localStorage = new LocalStorage(getContext());
        if (localStorage.isUserLoggedIn()) {
            startActivity(new Intent(getContext(), MainActivity.class));
        }
        fragmentManager = getActivity().getSupportFragmentManager();

        emailid = view.findViewById(R.id.login_emailid);
        password = view.findViewById(R.id.login_password);
        loginButton = view.findViewById(R.id.loginBtn);
        forgotPassword = view.findViewById(R.id.forgot_password);
//        signUp = view.findViewById(R.id.createAccount);
        show_hide_password = view
                .findViewById(R.id.show_hide_password);
        loginLayout = view.findViewById(R.id.login_layout);
        progressDialog = new ProgressDialog(getContext());
        localStorage = new LocalStorage(getContext());
        String userString = localStorage.getUserLogin();
        Gson gson = new Gson();
        userString = localStorage.getUserLogin();
        user = gson.fromJson(userString, User.class);
        Log.d("User", userString);
        // Load ShakeAnimation
        shakeAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.shake);

        // Setting text selector over textviews
        @SuppressLint("ResourceType") XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
        try {
            ColorStateList csl = ColorStateList.createFromXml(getResources(),
                    xrp);

            forgotPassword.setTextColor(csl);
            show_hide_password.setTextColor(csl);
//            signUp.setTextColor(csl);
        } catch (Exception e) {
        }
    }

    // Set Listeners
    private void setListeners() {
        loginButton.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);
//        signUp.setOnClickListener(this);

        // Set check listener over checkbox for showing and hiding password
        show_hide_password
                .setOnCheckedChangeListener(new OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton button,
                                                 boolean isChecked) {

                        // If it is checkec then show password else hide
                        // password
                        if (isChecked) {

                            show_hide_password.setText(R.string.hide_pwd);// change
                            // checkbox
                            // text

                            password.setInputType(InputType.TYPE_CLASS_TEXT);
                            password.setTransformationMethod(HideReturnsTransformationMethod
                                    .getInstance());// show password
                        } else {
                            show_hide_password.setText(R.string.show_pwd);// change
                            // checkbox
                            // text

                            password.setInputType(InputType.TYPE_CLASS_TEXT
                                    | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            password.setTransformationMethod(PasswordTransformationMethod
                                    .getInstance());// hide password

                        }

                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBtn:
                checkValidation();
                break;

            case R.id.forgot_password:

                // Replace forgot password fragment with animation
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer,
                                new ForgotPassword_Fragment(),
                                Utils.ForgotPassword_Fragment).commit();
                break;
//            case R.id.createAccount:
//
//                // Replace signup frgament with animation
//                fragmentManager
//                        .beginTransaction()
//                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
//                        .replace(R.id.frameContainer, new SignUp_Fragment(),
//                                Utils.SignUp_Fragment).commit();
//                break;
        }

    }

    // Check Validation before login
    private void checkValidation() {
        // Get email id and password
        final String getEmailId = emailid.getText().toString();
        final String getPassword = password.getText().toString();

        // Check patter for email id
        Pattern p = Pattern.compile(Utils.regEx);

        Matcher m = p.matcher(getEmailId);

        // Check for both field is empty or not
//        if (getEmailId.equals("") || getEmailId.length() == 0
//                || getPassword.equals("") || getPassword.length() == 0) {
//            loginLayout.startAnimation(shakeAnimation);
//            new CustomToast().Show_Toast(getActivity(), view,
//                    "Enter both credentials.");
//            vibrate(200);
//        }
        if (getEmailId.equals("") || getEmailId.length() == 0) {
            loginLayout.startAnimation(shakeAnimation);
            new CustomToast().Show_Toast(getActivity(), view,
                    "H??y nh??p ?????a ch??? Email.");
            vibrate(200);
            emailid.setError("?????a ch??? Email b??? b??? tr???ng");
            emailid.requestFocus();
        }
        else if (getPassword.equals("") || getPassword.length() == 0) {
            loginLayout.startAnimation(shakeAnimation);
            new CustomToast().Show_Toast(getActivity(), view,
                    "H??y nh??p m???t kh???u.");
            vibrate(200);
            password.setError("M???t kh???u b??? b??? tr???ng");
            password.requestFocus();
        }
        // Check if email id is valid or not
        else if (!m.find()) {
            new CustomToast().Show_Toast(getActivity(), view,
                    "?????a ch??? Email sai ?????nh d???ng.");
            vibrate(200);
            // Else do login and do your stuff
        } else {

            progressDialog.setMessage("Vui l??ng ?????i....");
            progressDialog.show();

            Handler mHand = new Handler();
            mHand.postDelayed(new Runnable() {

                @Override
                public void run() {
//                    if (user != null) {
//                        if (!user.getEmail().equals(getEmailId)) {
//                            new CustomToast().Show_Toast(getActivity(), view,
//                                    "H??y ki???m tra l???i ?????a ch??? Email");
//                        }else if (!user.getPassword().equals(getPassword)) {
//                            new CustomToast().Show_Toast(getActivity(), view,
//                                    "H??y ki???m tra l???i m???t kh???u");
//                        }
//                        else {
//                            startActivity(new Intent(getActivity(), MainActivity.class));
//                            getActivity().finish();
//                            getActivity().overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
//                        }
//                    } else {
//                        new CustomToast().Show_Toast(getActivity(), view,
//                                "?????a ch??? Email n??y kh??ng t???n t???i");
//                    }

                    APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
                    Call<JsonObject> callUser = apiInterface.login(getEmailId,getPassword);
                    callUser.enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            if(response.code() == 200){
                                String jsonString = response.body().toString();
                                Gson gson = new Gson();
                                User u = gson.fromJson(jsonString, User.class);
                                localStorage = new LocalStorage(getContext());
                                localStorage.createUserLoginSession(gson.toJson(u));
//                                new CustomToast().Show_Toast(getActivity(), view,
//                                        "????ng nh???p th??nh c??ng");
                                startActivity(new Intent(getActivity(), MainActivity.class));
                                getActivity().finish();
                                getActivity().overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                            }
                            else if(response.code() == 301)
                                new CustomToast().Show_Toast(getActivity(), view,
                                        "?????a ch??? Email kh??ng t???n t???i");
                            else if(response.code() == 401)
                                new CustomToast().Show_Toast(getActivity(), view,
                                        "M???t kh???u ch??a ????ng");
//                            Log.i("test", response.body().toString());
                            else
                                new CustomToast().Show_Toast(getActivity(), view,
                                        "K???t n???i v???i m??y ch??? b??? l???i");
                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {
                            Log.e("test", call.toString());
                            Log.e("test", t.toString());
                            new CustomToast().Show_Toast(getActivity(), view,
                                    "K???t n???i v???i m??y ch??? b??? l???i");
                            call.cancel();
                        }
                    });

                    progressDialog.dismiss();

                }
            }, 5000);

        }
    }

    public void vibrate(int duration) {
        Vibrator vibs = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        vibs.vibrate(duration);
    }
}
