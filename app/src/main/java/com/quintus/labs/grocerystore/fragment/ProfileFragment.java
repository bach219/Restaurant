package com.quintus.labs.grocerystore.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;
import com.quintus.labs.grocerystore.R;
import com.quintus.labs.grocerystore.customfonts.MyTextViewSansRegular;
import com.quintus.labs.grocerystore.model.User;
import com.quintus.labs.grocerystore.retrofit.Host;
import com.quintus.labs.grocerystore.util.localstorage.LocalStorage;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
/**
 * Grocery App
 * https://github.com/quintuslabs/GroceryStore
 * Created on 18-Feb-2019.
 * Created by : Santosh Kumar Dash:- http://santoshdash.epizy.com
 */
/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Thông tin nhân viên");

        LocalStorage localStorage = new LocalStorage(getContext());
        String userString = localStorage.getUserLogin();
        Gson gson = new Gson();
        User user = gson.fromJson(userString, User.class);

        CircleImageView img = view.findViewById(R.id.imageProfile);
        Picasso.get()
                .load(Host.host + user.getPhoto())
                .placeholder(R.drawable.no_image).error(R.drawable.no_image)
                .into(img);

        MyTextViewSansRegular name = view.findViewById(R.id.name_profile);
        name.setText(user.getName());
        MyTextViewSansRegular email = view.findViewById(R.id.email_profile);
        email.setText(user.getEmail());
        MyTextViewSansRegular phone = view.findViewById(R.id.phone_profile);
        phone.setText(user.getPhone());
        MyTextViewSansRegular address = view.findViewById(R.id.address_profile);
        address.setText(user.getAddress());
        MyTextViewSansRegular position = view.findViewById(R.id.position_profile);
        position.setText(user.getPosition());

        TextView update = view.findViewById(R.id.openUpdateProfile);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(new UpdateProfileFragment());
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
