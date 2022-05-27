package com.mainpackage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.userinformation.UserActivities;
import com.example.userinformation.UserActivitiesArray;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import coil.ImageLoader;
import coil.request.ImageRequest;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class ActivitysFragement extends Fragment {
    Gson gson = new Gson();
    SharedPreferences sp;
    ApplicationMy app;
    ImageView imgImport,imgAddActivity;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragement_activitys, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sp = getActivity().getSharedPreferences("com.mainpackage_preferences", Context.MODE_PRIVATE);
        postRequestNewActivity();
        app = (ApplicationMy) getActivity().getApplication();

        getRequestUserActivities(sp.getString("USER ID","25"));
        //imgImport = (ImageView) getView().findViewById(R.id.fragementActivity_import_img);
        imgAddActivity = (ImageView) getView().findViewById(R.id.fragementActivity_add_img);
        imgAddActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), NewActivityActivity.class);
                startActivity(i);
            }
        });

    }
    private Gson getGson() {
        if (gson == null)
            gson = new GsonBuilder().setPrettyPrinting().create();
        return gson;
    }
    public void getRequestUserActivities(String userId){
        AsyncHttpClient client = new AsyncHttpClient();

        RequestParams params = new RequestParams();
        params.put("id",userId);
        client.get("https://projektptuj.ddns.net/api.php/baza/aktivnost/android", params, new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String res) {
                Log.i("Jsonparams",res);

                JSONArray json = null;
                try {
                    json = new JSONArray(res);
                    String text = json.get(0).toString();
                     for(int i = 0 ; i <json.length();i++) {
                         JSONObject jsonobject = json.getJSONObject(i);

                         UserActivities userActivities = new UserActivities(
                                 jsonobject.get("datum").toString(),
                                 jsonobject.get("ocena_aktivnosti").toString(),
                                 jsonobject.get("koraki").toString(),
                                 jsonobject.get("porabljene_kalorije").toString(),
                                 jsonobject.get("povp_srcni_utrip").toString(),
                                 jsonobject.get("povp_hitrost").toString(),
                                 jsonobject.get("razdalja").toString(),
                                 jsonobject.get("ime").toString(),
                                 jsonobject.get("username").toString(),
                                 jsonobject.get("cas").toString()
                         );
                         app.userActivitiesArray = new UserActivitiesArray();

                         app.userActivitiesArray.AddToArray(userActivities);
                         /*
                         String ime = jsonobject.get("datum").toString();
                         String priimek = jsonobject.get("ocena_aktivnosti").toString();
                         String email1 = jsonobject.get("koraki").toString();
                         String telefon = jsonobject.get("porabljene_kalorije").toString();
                         String teza = jsonobject.get("povp_srcni_utrip").toString();
                         String visina = jsonobject.get("povp_hitrost").toString();
                         String spol = jsonobject.get("razdalja").toString();
                         String spol3 = jsonobject.get("cas").toString();
                         String spol1 = jsonobject.get("username").toString();
                         String spol2 = jsonobject.get("ime").toString();

                         Log.i("Jsonparams", ime);
                         Log.i("Jsonparams", priimek);
                         Log.i("Jsonparams", email1);
                         Log.i("Jsonparams", telefon);
                         Log.i("Jsonparams", teza);
                         Log.i("Jsonparams", visina);
                         */
                         Log.i("Jsonparams", app.userActivitiesArray.toString());


                     }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                  //  UserActivitiesArray userActivitiesArray = new UserActivitiesArray("aleks");
                 //   SimpleDateFormat D = new SimpleDateFormat("hh:mm:ss");
                   // Gson gson = new GsonBuilder().setDateFormat(yyyy-mm-dd,hh:mm:ss).create();
                 //   userActivitiesArray = gson.fromJson(res, new TypeToken<ArrayList<UserActivities>>() {
                 //   }.getType());
                   // Toast.makeText(getActivity(), userActivitiesArray.toString(), Toast.LENGTH_LONG).show();


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                // Toast.makeText(MainActivity.this,"neuspesna prijava",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void postRequestNewActivity() {
        AsyncHttpClient client = new AsyncHttpClient();
        JSONObject jsonParams = new JSONObject();

        try {
            jsonParams.put("datum", "2021-6-6 11:15:45");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            jsonParams.put("ocena_aktivnosti", "4");
        } catch (JSONException e) {
            e.printStackTrace();
        } try {
            jsonParams.put("koraki", "45");
        } catch (JSONException e) {
            e.printStackTrace();
        } try {
            jsonParams.put("porabljene_kalorije", "12");
        } catch (JSONException e) {
            e.printStackTrace();
        } try {
            jsonParams.put("povp_srcni_utrip", "0");
        } catch (JSONException e) {
            e.printStackTrace();
        } try {
            jsonParams.put("povp_hitrost", "3");
        } catch (JSONException e) {
            e.printStackTrace();
        } try {
            jsonParams.put("razdalja", "35");
        } catch (JSONException e) {
            e.printStackTrace();
        } try {
            jsonParams.put("cas", "00:00:38");
        } catch (JSONException e) {
            e.printStackTrace();
        }try {
            jsonParams.put("FK_idUser", "28");
        } catch (JSONException e) {
            e.printStackTrace();
        }try {
            jsonParams.put("lat", "46.425799");
        } catch (JSONException e) {
            e.printStackTrace();
        }try {
            jsonParams.put("lon", "15.881354");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        StringEntity entity = null;
        try {
            entity = new StringEntity(jsonParams.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        client.post(getActivity(), "https://projektptuj.ddns.net/api.php/baza/aktivnost/android", entity, "application/json", new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getActivity(), responseString, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Toast.makeText(getActivity(), responseString, Toast.LENGTH_LONG).show();
                Log.i("Jsonparams",responseString);

            }
        });
    }
}
