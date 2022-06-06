package com.mainpackage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class OtherAppsFragement extends Fragment implements OnMapReadyCallback {
    private MapView mapV;
    private String bundleKey = "MapViewBundleKey";

    ApplicationMy app;

    RoadsList entry;
    FirebaseDatabase database;
    DatabaseReference myRef;

    ArrayList<RoadsList> entries;

    TextView debug;

    String Lat = "", Lon = "", LatEnd = "", LonEnd = "";
    int rq; // road quality

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragement_other_apps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mapV = (MapView) getView().findViewById(R.id.mapView);

        app = (ApplicationMy) getActivity().getApplication();

        debug = (TextView) getView().findViewById(R.id.textView2);

        database = FirebaseDatabase.getInstance("https://auth-d6ca2-default-rtdb.europe-west1.firebasedatabase.app/");
        myRef = database.getReference("locations");

        entries = new ArrayList<RoadsList>();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot roadsSnapshot: snapshot.getChildren()){
                    entry = roadsSnapshot.getValue(RoadsList.class);
                    entries.add(entry);
                }
                System.out.println("Here dumbass!" + entries);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Bundle mapViewBundle = null;
        if(savedInstanceState != null){
            mapViewBundle = savedInstanceState.getBundle(bundleKey);
        }

        mapV.onCreate(mapViewBundle);

        mapV.getMapAsync(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(bundleKey);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(bundleKey, mapViewBundle);
        }

        mapV.onSaveInstanceState(mapViewBundle);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapV.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mapV.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapV.onStop();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        //map.addMarker(new MarkerOptions().position(new LatLng(0,0)).title("Marker"));
        //if(app.startLat != "default" && app.startLon != "default"){
        //    map.addMarker(new MarkerOptions().position(new LatLng(Double.valueOf(app.startLat), Double.valueOf(app.startLon))).title("Road Quality: " + String.valueOf(app.roadQuality)));
        //}
        double latitude, longitude, latitudeEnd, longitudeEnd;

        //Toast.makeText(getActivity(), "Number of saved roads: " + String.valueOf(entries.size()), Toast.LENGTH_SHORT).show();
        //debug.setText(String.valueOf(entries.size()));

        if(!entries.isEmpty()){
            for(int i = 0; i < entries.size(); i++){

                if(entries.get(i).getStartLat() == null || entries.get(i).getStartLon() == null || entries.get(i).getEndLat() == null || entries.get(i).getEndLon() == null){
                    i++;
                }

                Lat = entries.get(i).getStartLat();
                Lon = entries.get(i).getStartLon();
                LatEnd = entries.get(i).getEndLat();
                LonEnd = entries.get(i).getEndLon();
                rq = entries.get(i).getRoadQuality();


                //Toast.makeText(getActivity(), "Lat/Lon: " + Lat + " / " + Lon, Toast.LENGTH_SHORT).show();
                entries.get(i).print();


                latitude = Double.valueOf(Lat);
                longitude = Double.valueOf(Lon);
                latitudeEnd = Double.valueOf(LatEnd);
                longitudeEnd = Double.valueOf(LonEnd);

                map.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title(("Start of path " + i + " - Road Quality: " + rq)));
                map.addMarker(new MarkerOptions().position(new LatLng(latitudeEnd, longitudeEnd)).title(("End of path " + i + " - Road Quality: " + rq)));
            }
        }
    }

    @Override
    public void onPause() {
        mapV.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mapV.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapV.onLowMemory();
    }
}
