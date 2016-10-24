package com.example.gurpartap.skip_and_buy.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gurpartap.skip_and_buy.Controller.LoginActivity;
import com.example.gurpartap.skip_and_buy.R;

import org.json.JSONException;
import org.json.JSONObject;

public class ConfirmationFragment extends Fragment {

    public ConfirmationFragment() {
        // Required empty public constructor
    }

    TextView textViewId,textViewStatus,textViewAmount, textViewTime;
    Button ok;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_confirmation, container, false);
        textViewStatus= (TextView)view. findViewById(R.id.paymentStatus);
        textViewAmount = (TextView) view.findViewById(R.id.paymentAmount);
        textViewTime = (TextView) view.findViewById(R.id.paymentTime);
        ok = (Button)view.findViewById(R.id.okBtn);
        String i=null;
        String j=null;
        try {
            Bundle bundle = this.getArguments();
            if (bundle != null) {
                i = bundle.getString("PaymentDetails");
                j = bundle.getString("PaymentAmount");
            }
            //Displaying payment details
            JSONObject jsonDetails = new JSONObject(i);

            //Displaying payment amount
            showDetails(jsonDetails.getJSONObject("response"),j);
        } catch (JSONException e) {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        ok.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                paymentConfirmation();
            }
        });
        return view;
    }

    private void showDetails(JSONObject jsonDetails, String paymentAmount) throws JSONException {
        //Views
        //Showing the details from json object
        textViewId.setText("101012");
        textViewStatus.setText("Complete");
        textViewAmount.setText("$67.8");
        textViewTime.setText("10:45 AM 10/19/2016");
    }

    private void paymentConfirmation(){
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }
}