package com.mainpackage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class OtherAppsFragement extends Fragment implements OnMapReadyCallback {
    private MapView mapV;
    private String bundleKey = "MapViewBundleKey";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragement_other_apps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mapV = (MapView) getView().findViewById(R.id.mapView);

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
        map.addMarker(new MarkerOptions().position(new LatLng(0,0)).title("Marker"));
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
