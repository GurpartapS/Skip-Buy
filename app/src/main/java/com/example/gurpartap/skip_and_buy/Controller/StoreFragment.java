package com.example.gurpartap.skip_and_buy.Controller;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gurpartap.skip_and_buy.R;


public class StoreFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_store, container, false);

        TextView storeDescription=(TextView)rootView.findViewById(R.id.storeDescription);
        storeDescription.setText(getArguments().get("storeDescription").toString());
        TextView storeTitle=(TextView)rootView.findViewById(R.id.storeTitle);
        storeTitle.setText(getArguments().get("storeTitle").toString());
        TextView address=(TextView)rootView.findViewById(R.id.address);
        address.setText(getArguments().get("address").toString());

        return rootView;
    }



}
