package com.quintus.labs.grocerystore.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.gson.JsonObject;
import com.quintus.labs.grocerystore.R;
import com.quintus.labs.grocerystore.activity.LoginRegisterActivity;
import com.quintus.labs.grocerystore.retrofit.APIClient;
import com.quintus.labs.grocerystore.retrofit.APIInterface;
import com.quintus.labs.grocerystore.util.CustomToast;
import com.quintus.labs.grocerystore.util.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Grocery App
 * https://github.com/quintuslabs/GroceryStore
 * Created on 18-Feb-2019.
 * Created by : Santosh Kumar Dash:- http://santoshdash.epizy.com
 */
public class ForgotPassword_Fragment extends Fragment implements
        OnClickListener {
    private static View view;

    private static EditText emailId;
    private static TextView submit, back;
    private static LinearLayout forgotPassLayout;
    private static Animation shakeAnimation;
    ProgressDialog progressDialog;
    public ForgotPassword_Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.forgotpassword_layout, container,
                false);
        initViews();
        setListeners();
        return view;
    }

    // Initialize the views
    private void initViews() {
        emailId = view.findViewById(R.id.registered_emailid);
        submit = view.findViewById(R.id.forgot_button);
        back = view.findViewById(R.id.backToLoginBtn);
        progressDialog = new ProgressDialog(getContext());
        forgotPassLayout = view.findViewById(R.id.forgot_password_layout);
        // Load ShakeAnimation
        shakeAnimation = AnimationUtils.loadAnimation(getActivity(),
                R.anim.shake);
        // Setting text selector over textviews
        @SuppressLint("ResourceType") XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
        try {
            ColorStateList csl = ColorStateList.createFromXml(getResources(),
                    xrp);

            back.setTextColor(csl);
            submit.setTextColor(csl);

        } catch (Exception e) {
        }

    }

    // Set Listeners over buttons
    private void setListeners() {
        back.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backToLoginBtn:

                // Replace Login Fragment on Back Presses
                new LoginRegisterActivity().replaceLoginFragment();
                break;

            case R.id.forgot_button:

                // Call Submit button task
                submitButtonTask();
                break;

        }

    }

    private void submitButtonTask() {
        String getEmailId = emailId.getText().toString();

        // Pattern for email id validation
        Pattern p = Pattern.compile(Utils.regEx);

        // Match the pattern
        Matcher m = p.matcher(getEmailId);

        // First check if email id is not null else show error toast
        if (getEmailId.equals("") || getEmailId.length() == 0) {
            forgotPassLayout.startAnimation(shakeAnimation);
            new CustomToast().Show_Toast(getActivity(), view,
                    "H??y nh???p ?????a ch??? Email mu???n l???y l???i.");
            vibrate(200);
            emailId.setError("?????a ch??? Email b??? b??? tr???ng");
            emailId.requestFocus();
        }    // Check if email id is valid or not
        else if (!m.find())
            new CustomToast().Show_Toast(getActivity(), view,
                    "?????a ch??? Email kh??ng ????ng ?????nh d???ng.");

            // Else submit email id and fetch passwod or do your stuff
        else{
            progressDialog.setMessage("Vui l??ng ?????i....");
            progressDialog.show();

            Handler mHand = new Handler();
            mHand.postDelayed(new Runnable() {
              @Override
              public void run() {

                  checkValidation(emailId.getText().toString());
                  progressDialog.dismiss();
              }
            }, 7000);
//            Toast.makeText(getActivity(), "L???y m???t kh???u ???? qu??n.",
//                    Toast.LENGTH_SHORT).show();
        }
    }

    void checkValidation(String mail){
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<JsonObject> callUser = apiInterface.forgot(mail);
        callUser.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.code() == 200) {
                    Toast.makeText(getActivity(), "M???t kh???u m???i ???? ???????c g???i trong Email!", Toast.LENGTH_LONG).show();
//                            new CustomToast().Show_Toast(getActivity(), view,
//                                    "C???p nh???t d??? li???u th??nh c??ng!");
                    vibrate(200);
                }
                if(response.code() == 401)
                    new CustomToast().Show_Toast(getActivity(), view,
                            "?????a ch??? Email kh??ng t???n t???i");
//                            Toast.makeText(getActivity(), "?????a ch??? Email ???? t???n t???i!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("test", call.toString());
                Log.e("test", t.toString());
                call.cancel();
            }
        });
    }

    public void vibrate(int duration) {
        Vibrator vibs = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        vibs.vibrate(duration);
    }
}