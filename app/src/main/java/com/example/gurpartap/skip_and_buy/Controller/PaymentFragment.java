package com.example.gurpartap.skip_and_buy.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.gurpartap.skip_and_buy.Model.PayPalConfig;
import com.example.gurpartap.skip_and_buy.R;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;
import org.json.JSONException;
import java.math.BigDecimal;

public class PaymentFragment extends Fragment {
    //PaymentActivity Amount
    private String paymentAmount;

    //Paypal intent request code to track onActivityResult method
    public static final int PAYPAL_REQUEST_CODE = 123;
    TextView subtotal, taxes, total;
    Button btn, cancelBtn;

    public PaymentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_payment, container, false);

        subtotal = (TextView) view.findViewById(R.id.subTotalNumber);
        taxes = (TextView) view.findViewById(R.id.taxes);
        total = (TextView) view.findViewById(R.id.total);
        btn = (Button)view.findViewById(R.id.payNowbtn);
        cancelBtn = (Button)view.findViewById(R.id.cancelbtn);

        String setSubtotal = "$60.00";
        subtotal.setText(setSubtotal);

        double subTotal = Double.parseDouble(subtotal.getText().toString().substring(1));

        double calculatedTax = Math.round(subTotal*0.13*100)/100.00;
        taxes.setText("$"+String.valueOf(calculatedTax));

        double grandTotal = subTotal+calculatedTax;
        total.setText("$"+String.valueOf(grandTotal));

        //Getting the amount from editText
        paymentAmount = ((TextView)view.findViewById(R.id.total)).getText().toString().substring(1);

        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                getPayment();
            }
        });
        return view;
    }

    //Paypal Configuration Object
    private static PayPalConfiguration config = new PayPalConfiguration()
            // Start with mock environment.  When ready, switch to sandbox (ENVIRONMENT_SANDBOX)
            // or live (ENVIRONMENT_PRODUCTION)
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(PayPalConfig.PAYPAL_CLIENT_ID);

    private void getPayment() {

        //Creating a paypalpayment
        PayPalPayment payment = new PayPalPayment(new BigDecimal(String.valueOf(paymentAmount)), "CAD", "Total Amount:",
                PayPalPayment.PAYMENT_INTENT_SALE);

        //Creating Paypal PaymentActivity activity intent
        Intent intent = new Intent(getActivity(), PaymentActivity.class);

        //putting the paypal configuration to the intent
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);

        //Puting paypal payment to the intent
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);

        //Starting the intent activity for result
        //the request code will be used on the method onActivityResult
        startActivityForResult(intent, PAYPAL_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //If the result is from paypal
        if (requestCode == PAYPAL_REQUEST_CODE) {

            //If the result is OK i.e. user has not canceled the payment
            if (resultCode == Activity.RESULT_OK) {
                //Getting the payment confirmation
                PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);

                //if confirmation is not null
                if (confirm != null) {
                    try {
                        //Getting the payment details
                        String paymentDetails = confirm.toJSONObject().toString(5);
                        Log.i("paymentExample", paymentDetails);
                        Bundle bundle = new Bundle();
                        bundle.putString("PaymentDetails", paymentDetails);
                        bundle.putString("PaymentAmount", paymentAmount);

                        ConfirmationFragment confirmationFragment= new ConfirmationFragment();
                        confirmationFragment.setArguments(bundle);
                        this.getFragmentManager().beginTransaction()
                                .replace(R.id.contentFrameMain, confirmationFragment)
                                .addToBackStack(null)
                                .commit();
                    } catch (JSONException e) {
                        Log.e("paymentExample", "an extremely unlikely failure occurred: ", e);
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i("paymentExample", "The user cancelled.");
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i("paymentExample", "An invalid PaymentActivity or PayPalConfiguration was submitted. .");
            }
        }
    }
}
