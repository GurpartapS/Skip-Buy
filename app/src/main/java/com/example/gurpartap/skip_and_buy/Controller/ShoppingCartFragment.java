package com.example.gurpartap.skip_and_buy.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.example.gurpartap.skip_and_buy.Model.ShoppingCartItem;
import com.example.gurpartap.skip_and_buy.R;

public class ShoppingCartFragment extends ListFragment implements AdapterView.OnItemClickListener {

    Button checkout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_card, container, false);
        checkout = (Button)view.findViewById(R.id.checkout);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        ShoppingCartItem shoppingCartItem[]=new ShoppingCartItem[]{
                new ShoppingCartItem(R.drawable.ketchup,"Ketchup    "),
                new ShoppingCartItem(R.drawable.ritz,"Ritz Snack"),
                new ShoppingCartItem(R.drawable.excelchew,"Excel Gum")
        };


        ShoppingCartAdapter adapter=new ShoppingCartAdapter(this.getContext(),R.layout.activity_shopping_cart_item, shoppingCartItem);

        setListAdapter(adapter);

        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(getActivity(),"Item: "+position,Toast.LENGTH_SHORT).show();

    }


}
