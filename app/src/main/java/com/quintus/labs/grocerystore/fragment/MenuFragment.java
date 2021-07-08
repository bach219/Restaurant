package com.quintus.labs.grocerystore.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;
import com.quintus.labs.grocerystore.R;
import com.quintus.labs.grocerystore.activity.CalendarViewWithNotesActivity;
import com.quintus.labs.grocerystore.model.User;
import com.quintus.labs.grocerystore.retrofit.Host;
import com.quintus.labs.grocerystore.util.localstorage.LocalStorage;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

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

        LocalStorage localStorage = new LocalStorage(getContext());
        String userString = localStorage.getUserLogin();
        Gson gson = new Gson();
        User user = gson.fromJson(userString, User.class);

//        Fragment fragment = null;
        CardView mainOrder = view.findViewById(R.id.mainOrder);
        CardView processOrder = view.findViewById(R.id.processOrder);
        CardView attendance = view.findViewById(R.id.attendance);
        CardView profile = view.findViewById(R.id.profile);

        CircleImageView img = view.findViewById(R.id.user_photo);
        Picasso.get()
                .load(Host.host + user.getPhoto())
                .placeholder(R.drawable.no_image).error(R.drawable.no_image)
                .into(img);
        TextView user_name = view.findViewById(R.id.user_name);
        user_name.setText(user.getName());
        TextView user_position = view.findViewById(R.id.user_position);
        user_position.setText(user.getPosition());

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
//                changeFragment(new HomeFragment());
//                startActivity(CalendarViewWithNotesActivitySDK21.makeIntent(getActivity()));
                startActivity(new Intent(getActivity(), CalendarViewWithNotesActivity.class));
                getActivity().overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
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

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }
    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }
}