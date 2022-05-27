package com.mainpackage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
//TEST ZA GITT SYNCANJE  test test :)
public class MainActivity extends AppCompatActivity {
    EditText username,password;
    TextView onUsername, onPassword;
    TextView textForgotPassword,textRegisterAccount;
    SharedPreferences sp;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.registerActivity_username_editText);
        password = (EditText) findViewById(R.id.registerActivity_password_editText);
        onPassword = (TextView) findViewById(R.id.registerActivity_login_onEditTextPassw);
        onUsername = (TextView) findViewById(R.id.registerActivity_login_onEditTextUser);
        textRegisterAccount = (TextView) findViewById(R.id.registerActivity_hasAccount_text);
        textRegisterAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), RegisterAccount.class);
                startActivity(i);
            }
        });
        setNavigationBar();
        // checks for focus on EditText's
        editTextListeners();
        //pogledam ce je v shared pref ze user id
        checkSharedPrefs();




    }

   void checkSharedPrefs(){
       sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

       if(sp.contains("USER ID")){
            Intent i = new Intent(getBaseContext(), BottomNavigationActivity.class);
            finish();
            startActivity(i);
        }
   }
    public void setNavigationBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setTitle("Prijava");
        actionBar.show();
    }
    public void editTextListeners(){
        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    username.setText("");
                    onUsername.setText("Uporabniško ime");
                }else{
                    if(TextUtils.isEmpty(username.getText().toString())){
                        username.setText("Uporabniško ime");
                    }
                    onUsername.setText("");
                }
            }
        });
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());

                    password.setText("");
                    onPassword.setText("Geslo");
                }else{
                    if(TextUtils.isEmpty(password.getText().toString())){
                        password.setInputType(InputType.TYPE_CLASS_TEXT);
                        password.setText("Geslo");
                        Log.i("Main","Dela");
                    }
                    onPassword.setText("");
                }
            }
        });
    }
    public void postRequestLoginUser(String username, String password){
        AsyncHttpClient client = new AsyncHttpClient();
        JSONObject jsonParams = new JSONObject();
        try {
            jsonParams.put("username", username);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jsonParams.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        StringEntity entity = null;
        try {
            entity = new StringEntity(jsonParams.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        client.post(MainActivity.this, "https://projektptuj.ddns.net/api.php/baza/user/android/prijava", entity, "application/json", new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(MainActivity.this, responseString, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.i("MainActivity", responseString);

                try {
                    JSONObject json = new JSONObject(responseString);
                    String failResponse = json.get("info").toString();
                    Log.i("MainActivity", failResponse);
                   // String successResponse = json.get("idUser").toString();
                   // Log.i("MainActivity", successResponse);
                    Toast.makeText(MainActivity.this, failResponse, Toast.LENGTH_LONG).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JSONObject json = null;
                try {
                    sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = sp.edit();

                    json = new JSONObject(responseString);
                    String successResponse = json.get("idUser").toString();
                    editor.putString("USER ID",successResponse.toString());
                    editor.apply();

                    Intent i = new Intent(getBaseContext(), BottomNavigationActivity.class);
                    Toast.makeText(MainActivity.this, "Prijava uspešna", Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(i);
                    Log.i("MainActivity", successResponse);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
    }
/*
    private void checkForPermission(){
        if(ContextCompat.checkSelfPermission(MainDashboard.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestStoragePermission();
        }else{
            Toast.makeText(MainDashboard.this, "Permission allowed :)", Toast.LENGTH_LONG).show();
        }
    }
    private void requestStoragePermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){
            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed to access your samsung health data")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainDashboard.this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);

                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }else{
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        if(requestCode == STORAGE_PERMISSION_CODE) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show();
            }
        }
    }*/


    public void MainActivity_login_button(View view) {
        postRequestLoginUser(username.getText().toString(),password.getText().toString());

    }
}