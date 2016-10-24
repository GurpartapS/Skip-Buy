package com.example.gurpartap.skip_and_buy.Controller;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.gurpartap.skip_and_buy.Model.SqlConnection;
import com.example.gurpartap.skip_and_buy.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SignUpActivity extends AppCompatActivity {

    EditText nameText;
    EditText emailText;
    EditText passwordText;
    EditText confirmPasswordText;


    //Button alreadyMemberButton;
    Button continueButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up);
                setupUI(findViewById(R.id.signupScreen));
        Window window=this.getWindow();
        //window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimary));
        //window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimary));
        YoYo.with(Techniques.Bounce)
                .duration(2000)
                .playOn(findViewById(R.id.imageView3));
        YoYo.with(Techniques.Bounce)
                .duration(2000)
                .playOn(findViewById(R.id.textView7));
        nameText = (EditText) findViewById(R.id.nameText);
        emailText = (EditText) findViewById(R.id.emailText);
        passwordText = (EditText) findViewById(R.id.passwordText);
        confirmPasswordText = (EditText) findViewById(R.id.confirmPasswordText);

        //alreadyMemberButton = (Button) findViewById(R.id.alreadyMemberButton);
        continueButton = (Button) findViewById(R.id.signupButton);
        emailText.setTextColor(Color.GREEN);
        emailText.getBackground().setColorFilter(getResources().getColor(R.color.editTextField), PorterDuff.Mode.SRC_ATOP);
        nameText.setTextColor(Color.GREEN);
        nameText.getBackground().setColorFilter(getResources().getColor(R.color.editTextField), PorterDuff.Mode.SRC_ATOP);
        passwordText.setTextColor(Color.GREEN);
        passwordText.getBackground().setColorFilter(getResources().getColor(R.color.editTextField), PorterDuff.Mode.SRC_ATOP);
        window.setStatusBarColor(this.getResources().getColor(R.color.colorPrimary));
        confirmPasswordText.setTextColor(Color.GREEN);
        confirmPasswordText.getBackground().setColorFilter(getResources().getColor(R.color.editTextField), PorterDuff.Mode.SRC_ATOP);

        emailText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                emailText.setTextColor(Color.GREEN);
                emailText.getBackground().setColorFilter(getResources().getColor(R.color.editTextField), PorterDuff.Mode.SRC_ATOP);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        nameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                nameText.setTextColor(Color.GREEN);
                nameText.getBackground().setColorFilter(getResources().getColor(R.color.editTextField), PorterDuff.Mode.SRC_ATOP);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        passwordText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                passwordText.setTextColor(Color.GREEN);
                passwordText.getBackground().setColorFilter(getResources().getColor(R.color.editTextField), PorterDuff.Mode.SRC_ATOP);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        confirmPasswordText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                confirmPasswordText.setTextColor(Color.GREEN);
                confirmPasswordText.getBackground().setColorFilter(getResources().getColor(R.color.editTextField), PorterDuff.Mode.SRC_ATOP);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        continueButton.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        if (nameText.getText().toString().equals("")||emailText.getText().toString().equals("")||passwordText.getText().toString().equals("")||confirmPasswordText.getText().toString().equals("")) {
                            Snackbar.make(view, "PLEASE ENTER DETAILS", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();

                            if(confirmPasswordText.getText().toString().equals("")){
                                confirmPasswordText.requestFocus();
                                confirmPasswordText.getBackground().setColorFilter(getResources().getColor(R.color.errorTextField), PorterDuff.Mode.SRC_ATOP);

                                confirmPasswordText.setTextColor(Color.RED);

                            }
                            if(passwordText.getText().toString().equals("")){
                                passwordText.requestFocus();
                                passwordText.getBackground().setColorFilter(getResources().getColor(R.color.errorTextField), PorterDuff.Mode.SRC_ATOP);

                                passwordText.setTextColor(Color.RED);

                            }

                            if(emailText.getText().toString().equals("")){
                                emailText.requestFocus();
                                emailText.getBackground().setColorFilter(getResources().getColor(R.color.errorTextField), PorterDuff.Mode.SRC_ATOP);

                                emailText.setTextColor(Color.RED);

                            }
                            if(nameText.getText().toString().equals("")){
                                nameText.requestFocus();
                                nameText.getBackground().setColorFilter(getResources().getColor(R.color.errorTextField), PorterDuff.Mode.SRC_ATOP);

                                nameText.setTextColor(Color.RED);

                            }

                        } else {
                            String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

                            Boolean emailValidation = emailText.getText().toString().matches(EMAIL_REGEX);
                            if (emailValidation == false) {
                                Snackbar.make(view, "INVALID EMAIL", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                                emailText.getBackground().setColorFilter(getResources().getColor(R.color.errorTextField), PorterDuff.Mode.SRC_ATOP);

                                emailText.setTextColor(Color.RED);

                                emailText.requestFocus();
                            } else if (!passwordText.getText().toString().equals(confirmPasswordText.getText().toString())) {
                                Snackbar.make(view, "PASSWORDS DO NOT MATCH", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                                passwordText.requestFocus();
                                passwordText.getBackground().setColorFilter(getResources().getColor(R.color.errorTextField), PorterDuff.Mode.SRC_ATOP);

                                passwordText.setTextColor(Color.RED);
                                confirmPasswordText.getBackground().setColorFilter(getResources().getColor(R.color.errorTextField), PorterDuff.Mode.SRC_ATOP);

                                confirmPasswordText.setTextColor(Color.RED);

                            } else {
                                Snackbar.make(view, "VALID DETAILS", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();
                                emailText.setTextColor(Color.GREEN);
                                emailText.getBackground().setColorFilter(getResources().getColor(R.color.editTextField), PorterDuff.Mode.SRC_ATOP);
                                passwordText.setTextColor(Color.GREEN);
                                passwordText.getBackground().setColorFilter(getResources().getColor(R.color.editTextField), PorterDuff.Mode.SRC_ATOP);
                                nameText.setTextColor(Color.GREEN);
                                nameText.getBackground().setColorFilter(getResources().getColor(R.color.editTextField), PorterDuff.Mode.SRC_ATOP);
                                confirmPasswordText.setTextColor(Color.GREEN);
                                confirmPasswordText.getBackground().setColorFilter(getResources().getColor(R.color.editTextField), PorterDuff.Mode.SRC_ATOP);
                                try {

                                    JSONObject jsonObject = new JSONObject();
                                    JSONArray jsonArray = new JSONArray();


                                    jsonObject.put("email", emailText.getText().toString());
                                    jsonObject.put("password", passwordText.getText().toString());


                                    FileWriter fw = new FileWriter(getApplicationContext().getFilesDir().getPath() + "/" + "test.txt");

                                    BufferedWriter bw = new BufferedWriter(fw);

                                    bw.write(jsonObject.toString());
                                    bw.close();


                                    new AsyncTaskRunner(view).execute();

                                } catch (JSONException e) {

                                } catch (Exception e) {
                                    Snackbar.make(view, e.getMessage(), Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                }
                            }
                        }
                    }
                }
        );
    }
    public static void hideSoftKeyboard(Activity activity){
        InputMethodManager inputMethodManager=(InputMethodManager)activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),0);
    }
    private class SendfeedbackJob extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String[] params) {
            // do above Server call here
            return "some message";
        }

        @Override
        protected void onPostExecute(String message) {
            //process message
        }
    }


    public void setupUI(View view){
        if(!(view instanceof EditText)){
            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(SignUpActivity.this);
                    return false;
                }
            });
        }

        if(view instanceof ViewGroup){
            for(int i=0;i<((ViewGroup)view).getChildCount();i++){
                View innerView=((ViewGroup)view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

    public void signInActivity(View view){
        Intent intent=new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;

        public AsyncTaskRunner(View view) {
            super();
            try {
                SqlConnection connn = new SqlConnection();

                Connection connect = connn.connect();
                Connection connect1 = connn.connect();

                if (connect != null) {

                    PreparedStatement verifyUser = connect.prepareStatement("Select * from UserAccount where email=?");
                    verifyUser.setString(1,emailText.getText().toString());

                    ResultSet verifyUserResultset = verifyUser.executeQuery();

                    Boolean accountExists=false;

                    if(verifyUserResultset.next()) {

                        Snackbar.make(view, "Account already exists", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        accountExists=true;
                    }
                        else {

                        PreparedStatement statement = connect.prepareStatement("Insert into UserAccount values(?,?)");
                        statement.setString(1, emailText.getText().toString());
                        statement.setString(2, passwordText.getText().toString());

                        if (statement.executeUpdate() > 0) {

                            Snackbar.make(view, "SIGNUP SUCCESSFUL ", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();

                            if (connect1 != null) {
                                PreparedStatement statement1 = connect1.prepareStatement("Insert into customer(customerName,customerEmail) values(?,?)");

                                statement1.setString(1, nameText.getText().toString());
                                statement1.setString(2, emailText.getText().toString());
                                if (statement1.executeUpdate() > 0) {
                                    Snackbar.make(view, "SIGNUP SUCCESSFUL ", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                    Intent intent = new Intent(view.getContext(), WelcomeActivity.class);
                                    startActivity(intent);
                                } else {
                                    Snackbar.make(view, "SIGNUP UNSUCCESSFUL ", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                }
                            } else {
                                Snackbar.make(view, "Connection Problem", Snackbar.LENGTH_LONG)
                                        .setAction("Action", null).show();

                            }

                        } else {
                            Snackbar.make(view, "SIGNUP UNSUCCESSFUL ", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                    }

                }
                else {
                    Snackbar.make(view, "Connection Problem", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
            }
            catch(Exception e){

            }
        }

        @Override
        protected String doInBackground(String... params) {
                        return null;
        }

        /*
         * (non-Javadoc)
         *
         * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
         */
        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation

        }

        /*
         * (non-Javadoc)
         *
         * @see android.os.AsyncTask#onPreExecute()
         */
        @Override
        protected void onPreExecute() {
            // Things to be done before execution of long running operation. For
            // example showing ProgessDialog
        }

        /*
         * (non-Javadoc)
         *
         * @see android.os.AsyncTask#onProgressUpdate(Progress[])
         */
        @Override
        protected void onProgressUpdate(String... text) {

            // Things to be done while execution of long running operation is in
            // progress. For example updating ProgessDialog
        }
    }

}