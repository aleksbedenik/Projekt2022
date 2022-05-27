package com.mainpackage;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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

public class RegisterAccount extends AppCompatActivity {
    EditText username, password, repeatPassword;
    TextView onUsername, onPassword, onRepeatPassword, hasAccount, failedRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);
        username = (EditText) findViewById(R.id.registerActivity_username_editText);
        password = (EditText) findViewById(R.id.registerActivity_password_editText);
        repeatPassword = (EditText) findViewById(R.id.registerActivity_repeatPassword_editText);
        onRepeatPassword = (TextView) findViewById(R.id.registerActivity_onRepeatPassword_text);
        onPassword = (TextView) findViewById(R.id.registerActivity_login_onEditTextPassw);
        onUsername = (TextView) findViewById(R.id.registerActivity_login_onEditTextUser);
        hasAccount = (TextView) findViewById(R.id.registerActivity_hasAccount_text);
        failedRegister = (TextView) findViewById(R.id.RegisterAccount_registerFail_text);

        hasAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), MainActivity.class);
                startActivity(i);
            }
        });
        editTextListeners();
        setNavigationBar();
    }

    public void setNavigationBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setTitle("Registracija");
        actionBar.show();
    }

    public void editTextListeners() {
        username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    username.setText("");
                    onUsername.setText("Uporabniško ime");
                } else {
                    if (TextUtils.isEmpty(username.getText().toString())) {
                        username.setText("Uporabniško ime");
                    }
                    onUsername.setText("");
                }
            }
        });
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    password.setText("");
                    onPassword.setText("Geslo");
                } else {
                    if (TextUtils.isEmpty(password.getText().toString())) {
                        password.setInputType(InputType.TYPE_CLASS_TEXT);
                        password.setText("Geslo");
                    }
                    onPassword.setText("");
                }
            }
        });
        repeatPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    repeatPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    repeatPassword.setText("");
                    onRepeatPassword.setText("Ponovi geslo");
                } else {
                    if (TextUtils.isEmpty(repeatPassword.getText().toString())) {
                        repeatPassword.setInputType(InputType.TYPE_CLASS_TEXT);
                        repeatPassword.setText("Ponovi geslo");
                    }
                    onRepeatPassword.setText("");
                }
            }
        });
    }

    public void registerActivity_register_button(View view) throws IOException {
        if(repeatPassword.getText().toString().equals(password.getText().toString())) {
            postRequestRegisterUser(username.getText().toString(), password.getText().toString());
            failedRegister.setText("");

        }else{
            failedRegister.setText("Gesli se ne ujemata!");
        }
    }

    public void postRequestRegisterUser(String username, String password) {
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

        client.post(RegisterAccount.this, "https://projektptuj.ddns.net/api.php/baza/user/android", entity, "application/json", new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(RegisterAccount.this, responseString, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Toast.makeText(RegisterAccount.this, responseString, Toast.LENGTH_LONG).show();
            }
        });
    }
}