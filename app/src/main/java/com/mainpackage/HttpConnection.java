package com.mainpackage;

import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class HttpConnection {
    //AsyncHttpClient client = new AsyncHttpClient();
   // RequestParams params = new RequestParams();
    JSONObject jsonParams = new JSONObject();

    public void getRequestUserInfo(int userId){
        AsyncHttpClient client = new AsyncHttpClient();

        RequestParams params = new RequestParams();
        params.put("id",userId);
        client.get("https://projektptuj.ddns.net/api.php/baza/user/android", params, new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String res) {
                //oast.makeText(MainActivity.this,res,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
               // Toast.makeText(MainActivity.this,"neuspesna prijava",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void postRequestLoginUser(){
        AsyncHttpClient client = new AsyncHttpClient();
        JSONObject jsonParams = new JSONObject();

    }

    }

