package com.example.gurpartap.skip_and_buy.Controller;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gurpartap.skip_and_buy.Model.ShoppingCartItem;
import com.example.gurpartap.skip_and_buy.R;

/**
 * Created by GURPARTAP on 2016-09-16.
 */
public class ShoppingCartAdapter extends ArrayAdapter<ShoppingCartItem> {

    Context context;
    int layoutResourceId;
    ShoppingCartItem data[]=null;


    public ShoppingCartAdapter(Context context, int layoutResourceId, ShoppingCartItem data[]) {
        super(context, layoutResourceId,data);
        this.layoutResourceId=layoutResourceId;
        this.context=context;
        this.data=data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View row=convertView;

        CardHolder holder=null;

        if(row==null){

            LayoutInflater inflater=((Activity)context).getLayoutInflater();
            row=inflater.inflate(layoutResourceId,parent,false);

            holder=new CardHolder();
            holder.icon=(ImageView)row.findViewById(R.id.iconView);
            holder.name=(TextView)row.findViewById(R.id.iconTitle);

            row.setTag(holder);
        }

        else{
            holder=(CardHolder)row.getTag();
        }

        ShoppingCartItem card=data[position];
        holder.name.setText(card.name);
        holder.icon.setImageResource(card.icon);

        return row;
    }

    static class CardHolder{
        ImageView icon;
        TextView name;
    }
}
