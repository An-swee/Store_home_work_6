package com.example.steven.hw6;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    static String map_store_add;

    private GoogleMap mMap;
    private double latitude_all=0;
    private double longitude_all=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


        Geocoder geoCoder = new Geocoder(this, Locale.getDefault());
        String addressString = map_store_add;
        List<Address> addressLocation = null;
        try {
            addressLocation = geoCoder.getFromLocationName(addressString, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Log.d("map",map_store_add);
        //Log.d("map",addressString);
        double latitude = addressLocation.get(0).getLatitude();
        //Log.d("map",""+latitude);
        double longitude = addressLocation.get(0).getLongitude();
        LatLng store_add = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(store_add).title("我的億萬賣場－"+CustomDialogActivity.CDA_change_store_name));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longitude) ,20));
    }

   /* public void show_store_all()
    {

        SQLiteDatabase db = openOrCreateDatabase("expense.db", MODE_PRIVATE, null);
        Cursor c = db.rawQuery("SELECT * FROM main.exp ",null);

        c.moveToFirst();

        Geocoder geoCoder = new Geocoder(this, Locale.getDefault());


        for(int i=1;i<=c.getCount();i++) {

            List<Address> addressLocation = null;
            try {
                addressLocation = geoCoder.getFromLocationName(c.getString(2), 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //Log.d("map",map_store_add);
            //Log.d("map",addressString);
            double latitude = addressLocation.get(0).getLatitude();
            //Log.d("map",""+latitude);
            double longitude = addressLocation.get(0).getLongitude();
            latitude_all = latitude_all + latitude_all ;
            longitude_all = longitude_all + longitude_all ;
            LatLng store_add = new LatLng(latitude, longitude);
            mMap.addMarker(new MarkerOptions().position(store_add).title("我的億萬賣場－"+c.getString(1)));
            //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            Log.d("Map","id:"+c.getString(0)+"經度ＡＬＬ："+latitude_all+"緯度ＡＬＬ："+longitude_all);
            c.moveToNext();
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude_all/c.getCount(),longitude_all/c.getCount()) ,11));
    }*/
}
