package com.example.hp.miniproject1;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.miniproject1.Modules.DirectionFinder;
import com.example.hp.miniproject1.Modules.DirectionFinderListener;
import com.example.hp.miniproject1.Modules.Route;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback,DirectionFinderListener{
    public static final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 1;
    private GoogleMap mMap;
    private Button btnTraCuu,btnTimDuong,btnFindPath;
    private TextView txtDistance,txtDuration;
    private EditText editOrigin,editDestinaton;
    private List<Marker> originMarkers = new ArrayList<>();
    private List<Marker> destinationMarkers = new ArrayList<>();
    private List<Polyline> polylinesPaths = new ArrayList<>();
    private ProgressDialog progressDialog;
 //   public LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
//    public Location myLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btnTraCuu = (Button) findViewById(R.id.btnTraCuu);
        btnTimDuong = (Button) findViewById(R.id.btnTimDuong);

        btnTimDuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Some code her
                Intent intent = new Intent(MapsActivity.this, timduong.class);
                startActivityForResult(intent, 123);
            }
        });

        btnTraCuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Some code here
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 123){
            String s1 = data.getStringExtra("origin");
            String s2 = data.getStringExtra("destination");
            sendRequest(s1,s2);
        }
    }

    public void sendRequest(String origin, String destination){
        if(origin.isEmpty()){
            Toast.makeText(MapsActivity.this,"Bạn chưa nhập địa điểm bắt đầu",Toast.LENGTH_LONG).show();
            return;
        }
        if(destination.isEmpty()){
            Toast.makeText(MapsActivity.this,"Bạn chưa nhập địa điểm kết thúc",Toast.LENGTH_LONG).show();
            return;
        }
        if(origin == "[current]"){
       //     LatLng userLocation = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());

        if(origin == "[current]"){
  //          LatLng userLocationdes = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
        }
        try{
            new DirectionFinder(this, origin, destination).execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in University of Science and move the camera
        LatLng universityofscience = new LatLng(10.7624165, 106.6812013);
        mMap.addMarker(new MarkerOptions().position(universityofscience).title("Trường Đại Học Khoa Học Tự Nhiên"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(universityofscience, 17));

        checkPermission();
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location loca    tion) {
        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();
    }

    public void checkPermission(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            if(ActivityCompat.shouldShowRequestPermissionRationale(MapsActivity.this,Manifest.permission.ACCESS_FINE_LOCATION)){
            } else{
                ActivityCompat.requestPermissions(MapsActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},MY_PERMISSIONS_REQUEST_FINE_LOCATION);
            }
        }
        else{
            mMap.setMyLocationEnabled(true);
           // lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_FINE_LOCATION) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                checkPermission();
            } else {
                // Permission was denied. Display an error message.
                Toast.makeText(MapsActivity.this, "Bị từ chối cấp phép", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onDirectionFinderStart() {
        progressDialog = ProgressDialog.show(this,"Please Wait","Finding direction", true);
        if (originMarkers  != null){
            for(Marker marker: originMarkers){
                marker.remove();
            }
        }
        if (destinationMarkers  != null){
            for(Marker marker: destinationMarkers){
                marker.remove();
            }
        }
        if (polylinesPaths != null){
            for(Polyline polyline: polylinesPaths){
                polyline.remove();
            }
        }

    }

    @Override
    public void onDirectionFinderSuccess(List<Route> routes) {
        progressDialog.dismiss();
        polylinesPaths = new ArrayList<>();
        originMarkers = new ArrayList<>();
        destinationMarkers = new ArrayList<>();

        for(Route route : routes){
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(route.startLocation,16));
            originMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.start_blue))
                    .title(route.startAddress)
                    .position(route.startLocation)));

            destinationMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.end_green))
                    .title(route.endAddress)
                    .position(route.endLocation)));

            PolylineOptions polylineOptions = new PolylineOptions()
                    .geodesic(true)
                    .color(Color.BLUE)
                    .width(10);

            for(int i = 0;i < route.points.size(); i++ ){
                polylineOptions.add(route.points.get(i));
            }

            polylinesPaths.add(mMap.addPolyline(polylineOptions));
        }
    }

}
