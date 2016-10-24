package com.example.gurpartap.skip_and_buy.Controller;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gurpartap.skip_and_buy.R;


public class ProductInfoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_product_info, container, false);

        TextView productDescription=(TextView)rootView.findViewById(R.id.productDescription);
        productDescription.setText(getArguments().get("productDescription").toString());
        TextView productName=(TextView)rootView.findViewById(R.id.productName);
        productName.setText(getArguments().get("productName").toString());
        TextView productWeight=(TextView)rootView.findViewById(R.id.productWeight);
        productWeight.setText(getArguments().get("productWeight").toString());
        TextView productPrice=(TextView)rootView.findViewById(R.id.productPrice);
        productPrice.setText(getArguments().get("productPrice").toString());

        return rootView;
    }
}
