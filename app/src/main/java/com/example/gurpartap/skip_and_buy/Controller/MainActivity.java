package com.example.gurpartap.skip_and_buy.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.gurpartap.skip_and_buy.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.paypal.android.sdk.payments.*;
import com.paypal.android.sdk.payments.PaymentActivity;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Fragment fragment=new Fragment();
    String scannerResult="";
    String paypalResult="";
    LocationManager mlocManager;
    private String paymentAmount;
    //Paypal intent request code to track onActivityResult method
    public static final int PAYPAL_REQUEST_CODE = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        scannerResult="";

        Window window = this.getWindow();
        window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimary));
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        android.support.v4.app.FragmentManager manager=getSupportFragmentManager();

        FragmentTransaction transaction=manager.beginTransaction();


        getSupportActionBar().setTitle("Shop");
        fragment=new ShopFragment();
        transaction.replace(R.id.contentFrameMain,fragment);
        transaction.commit();
        System.out.println("LOCATION STARTED");

        new CountDownTimer(3000, 1000) {

    public void onTick(long millisUntilFinished) {
        //TextView timerText=(TextView)findViewById(R.id.timerText);
        //timerText.setText(""+(millisUntilFinished/1000)+"");
        if(millisUntilFinished/1000==9){
            mlocManager =

                    (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            System.out.println("LOCATION ONE");
            LocationListener mlocListener = new MainActivity.MyLocationListener();
            System.out.println("LOCATION TWO");
            try {
                mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlocListener);
                System.out.println("LOCATION THREE");
            } catch (SecurityException e) {
                System.out.println("SADsadsd "+e.getMessage());
            }

        }

    }

    @Override
    public void onFinish() {
        android.support.v4.app.FragmentManager manager=getSupportFragmentManager();

        FragmentTransaction transaction=manager.beginTransaction();
        getSupportActionBar().setTitle("Shop");
        fragment=new StoreFragment();

        transaction.replace(R.id.contentFrameMain,fragment);
        transaction.commit();
        Bundle b = new Bundle();
        b.putString("storeTitle","Walmart Canada");
        b.putString("storeDescription","Groceries & Household Goods");
        b.putString("address","7899 McLaughlin Rd, Brampton, ON L6Y 5H9");
        fragment.setArguments(b);
    }

}.start();
        }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        android.support.v4.app.FragmentManager manager=getSupportFragmentManager();

        FragmentTransaction transaction=manager.beginTransaction();

        getSupportActionBar().setTitle("Shop");
        fragment=new ShopFragment();
        int id = item.getItemId();

        if (id == R.id.nav_shop) {
            getSupportActionBar().setTitle("Shop");
            fragment=new ShopFragment();

        } else if (id == R.id.nav_history) {


        } else if (id == R.id.nav_cart) {
            getSupportActionBar().setTitle("Shopping Cart");
            fragment=new ShoppingCartFragment();
        } else if (id == R.id.nav_profile) {
            getSupportActionBar().setTitle("Profile");
            fragment=new UserProfileFragment();
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        transaction.replace(R.id.contentFrameMain,fragment);
        transaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void replaceAddCardFragment(View view){
        getSupportActionBar().setTitle("Select PaymentActivity Method");
        android.support.v4.app.FragmentManager manager=getSupportFragmentManager();

        FragmentTransaction transaction=manager.beginTransaction();

        Fragment fragment=new Fragment();

        Snackbar.make(view, "Adding new card", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        fragment=new ShoppingCartFragment();
        transaction.replace(R.id.contentFrameMain,fragment);
        transaction.commit();
    }

    public void confirmOrder(){

        getSupportActionBar().setTitle("Payment Confirmation");
        android.support.v4.app.FragmentManager manager=getSupportFragmentManager();

        FragmentTransaction transaction=manager.beginTransaction();

        Fragment fragment=new Fragment();
        Bundle bundle = new Bundle();
        bundle.putString("PaymentDetails", "Walmart");
        bundle.putString("PaymentAmount", "20");

        fragment=new ConfirmationFragment();
        fragment.setArguments(bundle);
        transaction.replace(R.id.contentFrameMain,fragment);
        transaction.commit();

    }

    public void scanItemActivity(View view){
        /*Intent intent=new Intent(this, scanItemScreen.class);
        startActivity(intent);*/
        getSupportActionBar().setTitle("Scan Item");
        android.support.v4.app.FragmentManager manager=getSupportFragmentManager();

        FragmentTransaction transaction=manager.beginTransaction();

        Fragment fragment=new Fragment();
        fragment=new ScanItemFragment();
        transaction.replace(R.id.contentFrameMain,fragment);
        transaction.commit();
    }

    public void paymentFragment(View view){
        /*Intent intent=new Intent(this, scanItemScreen.class);
        startActivity(intent);*/
        getSupportActionBar().setTitle("Order Summary");
        android.support.v4.app.FragmentManager manager=getSupportFragmentManager();

        FragmentTransaction transaction=manager.beginTransaction();

        Fragment fragment=new Fragment();
        fragment=new PaymentFragment();
        transaction.replace(R.id.contentFrameMain,fragment);
        transaction.commit();
    }

    public void viewCartActivity(View view){
        /*Intent intent=new Intent(this, scanItemScreen.class);
        startActivity(intent);*/
        getSupportActionBar().setTitle("Shopping Cart");
        android.support.v4.app.FragmentManager manager=getSupportFragmentManager();

        FragmentTransaction transaction=manager.beginTransaction();

        Fragment fragment=new Fragment();
        fragment=new ShoppingCartFragment();
        transaction.replace(R.id.contentFrameMain,fragment);
        transaction.commit();
    }
    public void viewProductInfo(){
        /*Intent intent=new Intent(this, scanItemScreen.class);
        startActivity(intent);*/
        getSupportActionBar().setTitle("Product Information");
        android.support.v4.app.FragmentManager manager=getSupportFragmentManager();

        FragmentTransaction transaction=manager.beginTransaction();

        Fragment fragment=new Fragment();
        fragment=new ProductInfoFragment();
        Bundle b = new Bundle();
        b.putString("productName","Excel Gum");
        b.putString("productDescription","Happiness right out of the pack. Enjoy the rich, flaky, chewgum in your mouth taste of Excel in a variety of delicious flavours.");
        b.putString("productWeight","100g");
        b.putString("productPrice","$2 / Pc.");
        fragment.setArguments(b);
        transaction.replace(R.id.contentFrameMain,fragment);
        transaction.commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        IntentResult scanningResult= IntentIntegrator.parseActivityResult(requestCode,resultCode,intent);
        if(scanningResult!=null){

            Toast toast= Toast.makeText(getApplicationContext(),"SCANNING RESULT \n"+scanningResult.getContents().toString(),Toast.LENGTH_SHORT);

            toast.show();
            scannerResult=scanningResult.getContents().toString();
        }
        else{

            System.out.println("THIS IS ELSEE STATEMENT");
            //If the result is from paypal
            //if (requestCode == PAYPAL_REQUEST_CODE) {
                System.out.println("THIS IS ELSEE STATEMENT 1");
                //If the result is OK i.e. user has not canceled the payment
                if (resultCode == Activity.RESULT_OK) {
                    System.out.println("THIS IS ELSEE STATEMENT");
                    //Getting the payment confirmation
                    PaymentConfirmation confirm = intent.getParcelableExtra(com.paypal.android.sdk.payments.PaymentActivity.EXTRA_RESULT_CONFIRMATION);

                    //if confirmation is not null
                    if (confirm != null) {
                        System.out.println("ENTERERD THIS METHOD 23");
                        try {
                            //Getting the payment details
                            System.out.println("ENTERERD THIS METHOD 45");
                            String paymentDetails = confirm.toJSONObject().toString(5);
                            Log.i("paymentExample", paymentDetails);

                            System.out.println("ENTERERD THIS METHOD");
                            paypalResult="worked";
                        } catch (JSONException e) {
                            Log.e("paymentExample", "an extremely unlikely failure occurred: ", e);
                        }
                        catch(Exception e){
                            System.out.println("EXCEPTION "+e.getMessage());
                        }
                    }
                } else if (resultCode == Activity.RESULT_CANCELED) {
                    Log.i("paymentExample", "The user cancelled.");
                } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                    Log.i("paymentExample", "An invalid PaymentActivity or PayPalConfiguration was submitted. .");
                }
            //}
        }
    }


    @Override
    public void onResume() {
        if(scannerResult==""){

            if(paypalResult=="")
            {

            }
            else{
                confirmOrder();
                paypalResult="";
            }
            }
        else{


            viewProductInfo();

            scannerResult="";
        }
        super.onResume();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //No call for super(). Bug on API Level > 11.
    }
    public class MyLocationListener implements LocationListener

    {

        @Override

        public void onLocationChanged(Location loc)

        {
            System.out.println("LOCATION TRACKED ONE");


            double Lat=loc.getLatitude();

            double Long=loc.getLongitude();
            String Text="";
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
            try
            {

                addresses = geocoder.getFromLocation(Lat, Long, 1);
                System.out.println("LOCATION TRACKED TWO");
                if (addresses != null && addresses.size() > 0)
                {
                    if(fragment.getClass().toString().equalsIgnoreCase("class com.example.gurpartap.skip_and_buy.Controller.ShopFragment")){
                        android.support.v4.app.FragmentManager manager=getSupportFragmentManager();

                        FragmentTransaction transaction=manager.beginTransaction();
                        getSupportActionBar().setTitle("Shop");
                        fragment=new StoreFragment();

                        transaction.replace(R.id.contentFrameMain,fragment);
                        transaction.commit();


                    }
                    System.out.println("LOCATION TRACKED THREE");
                    String address = addresses.get(0).getAddressLine(0);
                    String address11 = addresses.get(0).getAddressLine(1);
                    String city = addresses.get(0).getLocality();
                    Text = "My current location is: " +

                            "Latitud = " + loc.getLatitude() +

                            "Longitud = " + loc.getLongitude()+
                            "address = " + address +
                            "address1 = " + address11 +
                            "city = " + city;
                    try {
                        mlocManager.removeUpdates(this);
                    }
                    catch(SecurityException e){
                        Toast.makeText(getApplicationContext(),

                                "SECURITY EXCEPTION OCCURED",

                                Toast.LENGTH_SHORT).show();
                    }
                    Bundle b = new Bundle();
                    b.putString("storeTitle","Walmart Canada");
                    b.putString("storeDescription","Groceries & Household Goods");
                    b.putString("address",address+" "+address11);
                    fragment.setArguments(b);
                }
            }
            catch (IOException e) {
                System.out.println("fsdfdsfdsf "+e.getMessage());
            }





        }


        @Override

        public void onProviderDisabled(String provider)

        {

            Toast.makeText(getApplicationContext(),

                    "Gps Disabled",

                    Toast.LENGTH_SHORT).show();

        }

        @Override

        public void onProviderEnabled(String provider)

        {

            Toast.makeText(getApplicationContext(), "Gps Enabled", Toast.LENGTH_SHORT).show();

        }


        @Override

        public void onStatusChanged(String provider, int status, Bundle extras)

        {


        }
    }
}
