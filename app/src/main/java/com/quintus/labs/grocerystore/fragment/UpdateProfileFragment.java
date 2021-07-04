package com.quintus.labs.grocerystore.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.quintus.labs.grocerystore.R;
import com.quintus.labs.grocerystore.model.User;
import com.quintus.labs.grocerystore.retrofit.APIClient;
import com.quintus.labs.grocerystore.retrofit.APIInterface;
import com.quintus.labs.grocerystore.util.CustomToast;
import com.quintus.labs.grocerystore.util.localstorage.LocalStorage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpdateProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateProfileFragment extends Fragment implements
        View.OnClickListener {
    private static View view;
    private static EditText email;
    private static EditText password_now;
    private static EditText password_new;
    private static EditText password_verify;
    private static TextView update;
    private static LinearLayout updateLayout;
    private static Animation shakeAnimation;
    LocalStorage localStorage;
    String userString;
    Gson gson ;
    User user ;
    String mail;
    public UpdateProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_update_profile, container, false);
        initViews();
        setListeners();
//        return inflater.inflate(R.layout.fragment_update_profile, container, false);
        return view;
    }

    private void initViews() {
        email = view.findViewById(R.id.update_emailid);
        password_now = view.findViewById(R.id.update_password_now);
        password_new = view.findViewById(R.id.update_password_new);
        password_verify = view.findViewById(R.id.update_password_verify);
        update = view.findViewById(R.id.txt_update_profile);
//        update.setOnClickListener(this);
        updateLayout = view.findViewById(R.id.updateLayout);
        shakeAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
        // Setting text selector over textviews
//        @SuppressLint("ResourceType") XmlResourceParser xrp = getResources().getXml(R.drawable.text_selector);
//        try {
//            ColorStateList csl = ColorStateList.createFromXml(getResources(),
//                    xrp);
//
//            update.setTextColor(csl);
//
//        } catch (Exception e) {
//        }
        localStorage = new LocalStorage(getContext());
        userString = localStorage.getUserLogin();
        gson = new Gson();
        user = gson.fromJson(userString, User.class);

        email.setText(user.getEmail());
    }

    private void setListeners() {
        update.setOnClickListener(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Chỉnh sửa thông tin");
    }

//    void changeFragment(Fragment fragment){
//        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        FragmentTransaction ft = fragmentManager.beginTransaction();
//        ft.setCustomAnimations(R.anim.slide_from_right, R.anim.slide_to_left);
//        ft.replace(R.id.content_frame, fragment);
//        ft.commit();
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_update_profile:
                checkValidation();
                break;
        }

    }

    void checkValidation(){
                APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
                mail = email.getText().toString();
                Log.i("test lúc gửi nè", mail);
                Call<JsonObject> callUser = apiInterface.updateProfile("Bearer " + user.getAccess_token(), mail, password_now.getText().toString(), password_new.getText().toString(), password_verify.getText().toString());
                callUser.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if(response.code() == 200){
                            Log.i("test response.body().toString() nè", response.body().toString());
                            String jsonString = response.body().toString();
                            gson = new Gson();
                            User u = gson.fromJson(jsonString, User.class);
                            u.setAccess_token(user.getAccess_token());
                            u.setEmail(mail);
                            Log.i("test u nè", u.toString());
//                            localStorage = new LocalStorage(getActivity());
                            localStorage.createUserLoginSession(gson.toJson(u));
                            email.setText(u.getEmail());
                            updateLayout.startAnimation(shakeAnimation);
                            Toast.makeText(getActivity(), "Cập nhật dữ liệu thành công!", Toast.LENGTH_LONG).show();
//                            new CustomToast().Show_Toast(getActivity(), view,
//                                    "Cập nhật dữ liệu thành công!");
                            vibrate(200);
                        }
                        if(response.code() == 201)
                            new CustomToast().Show_Toast(getActivity(), view,
                                    "Mật khẩu xác nhận sai");
//                            Toast.makeText(getActivity(), "Mật khẩu xác nhận sai!", Toast.LENGTH_LONG).show();
                        if(response.code() == 202)
                            new CustomToast().Show_Toast(getActivity(), view,
                                    "Mật khẩu mới phải có ít nhất 6 kí tự");

//                            Toast.makeText(getActivity(), "Mật khẩu mới phải có ít nhất 6 kí tự!", Toast.LENGTH_LONG).show();
                        if(response.code() == 203)
                            new CustomToast().Show_Toast(getActivity(), view,
                                    "Mật khẩu hiện tại sai");

//                            Toast.makeText(getActivity(), "Mật khẩu hiện tại sai!", Toast.LENGTH_LONG).show();
                        if(response.code() == 204)
                            new CustomToast().Show_Toast(getActivity(), view,
                                    "Địa chỉ Email đã tồn tại");
//                            Toast.makeText(getActivity(), "Địa chỉ Email đã tồn tại!", Toast.LENGTH_LONG).show();
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