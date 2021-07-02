package com.quintus.labs.grocerystore.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.quintus.labs.grocerystore.R;
import com.quintus.labs.grocerystore.activity.MainActivity;
import com.quintus.labs.grocerystore.fragment.HomeFragment;
import com.quintus.labs.grocerystore.fragment.MyOrderFragment;
import com.quintus.labs.grocerystore.fragment.ProfileFragment;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.quintus.labs.grocerystore.util.CustomToast;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment {

    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Trang chủ");

//        Fragment fragment = null;
        CardView mainOrder = view.findViewById(R.id.mainOrder);
        CardView processOrder = view.findViewById(R.id.processOrder);
        CardView attendance = view.findViewById(R.id.attendance);
        CardView profile = view.findViewById(R.id.profile);

        mainOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new HomeFragment());
//                new CustomToast().Show_Toast(getActivity(), view,
//                        "Hãy nhâp địa chỉ Email.");
            }
        });

        processOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new MyOrderFragment());
            }
        });

        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new HomeFragment());
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new ProfileFragment());
            }
        });
    }

    void changeFragment(Fragment fragment){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.slide_from_right, R.anim.slide_to_left);
        ft.replace(R.id.content_frame, fragment);
        ft.commit();
    }
}