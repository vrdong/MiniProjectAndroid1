package com.example.hp.miniproject1;

import android.app.IntentService;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
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
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class timduong extends FragmentActivity implements GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        OnMapReadyCallback{

    public EditText editOrigin;
    public EditText editDestination;
    public Button btnFind,btnCurOrigin,btnCurDestination;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timduong);

        editOrigin = (EditText)findViewById(R.id.txtOrigin);
        editDestination = (EditText)findViewById(R.id.txtDestination);
        btnCurOrigin = (Button)findViewById(R.id.btnCurOrigin);
        btnCurDestination = (Button)findViewById(R.id.btnCurDestination);
        btnFind = (Button)findViewById(R.id.btnFind);

        btnCurOrigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editOrigin.setText("[current]");
            }
        });

        btnCurDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editDestination.setText("[current]");
            }
        });
        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = getIntent();
                Intent data = new Intent();
                data.putExtra("origin", editOrigin.getText().toString());
                data.putExtra("destination",editDestination.getText().toString());
                setResult(123,data);
                finish();
            }
        });
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}
