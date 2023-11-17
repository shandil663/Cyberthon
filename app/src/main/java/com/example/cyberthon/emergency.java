package com.example.cyberthon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.BasePermissionListener;
import com.karumi.dexter.listener.single.PermissionListener;

public class emergency extends AppCompatActivity {

    AutoCompleteTextView txt1, txt2;
    EditText txt;
    FirebaseDatabase db;
    DatabaseReference ref;

    CardView cardView,card1;
ImageView img,img1;
FirebaseAuth oth;

    SupportMapFragment smf;
    FusedLocationProviderClient client;
    ArrayAdapter<String> adapterItems;
    String[] items = {"5", "10", "15", "20", "30", "10 Teams", "15 Teams"};
    String[] items1 = {"Attack", "Riot", "Bomb", "Criminal with weapon", "Equipment hacked", "Data Breach", "System Malfunction"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        cardView=findViewById(R.id.getloc);
        card1=findViewById(R.id.hide);
        txt=findViewById(R.id.sosedit);
        img=findViewById(R.id.sos);
        oth=FirebaseAuth.getInstance();
        img1=findViewById(R.id.medimg);
        txt1 = findViewById(R.id.auto1);
        txt2 = findViewById(R.id.auto2);
        db=FirebaseDatabase.getInstance();
img.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {


        String remark=txt.getText().toString();
        String no=txt1.getText().toString();
        String Reason=txt2.getText().toString();
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess( final Location location) {
                smf.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {
                        Double lat=location.getLatitude();
                        Double longi=location.getLatitude();
                        String cor="Latitude:"+" "+lat+" "+"Longitude:"+" "+longi;
                        ref=db.getReference(" Cyber_Emergency_alerts").child(oth.getCurrentUser().getUid());
                        sosmodal sm=new sosmodal(remark,no,Reason,oth.getCurrentUser().getEmail(),cor);
                        ref.setValue(sm).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(emergency.this, "Emergency Alert has been sent", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });


    }
});

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String remark=txt.getText().toString();
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                Task<Location> task = client.getLastLocation();
                task.addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess( final Location location) {
                        smf.getMapAsync(new OnMapReadyCallback() {
                            @Override
                            public void onMapReady(GoogleMap googleMap) {
                                Double lat=location.getLatitude();
                                Double longi=location.getLatitude();
                                String cor="Latitude:"+" "+lat+" "+"Longitude:"+" "+longi;
                                ref=db.getReference(" Medical_Emergency_alerts").child(oth.getCurrentUser().getUid());
                                sosmodal sm=new sosmodal(remark,oth.getCurrentUser().getEmail(),cor);
                                ref.setValue(sm).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(emergency.this, "Medical emergency support arriving soon", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    }
                });


            }
        });

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                card1.setVisibility(View.VISIBLE);
                smf = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
                client = LocationServices.getFusedLocationProviderClient(getApplicationContext());

                Dexter.withContext(getApplicationContext()).withPermission(Manifest.permission.ACCESS_FINE_LOCATION).withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        getmylocation();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();


            }
        });




        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, items);
        txt1.setAdapter(adapterItems);
        txt1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
            }
        });
        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, items1);
        txt2.setAdapter(adapterItems);
        txt2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
            }
        });

    }

    private void getmylocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess( final Location location) {
smf.getMapAsync(new OnMapReadyCallback() {
    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());
        MarkerOptions markerOptions=new MarkerOptions().position(latLng).title("You are here");
        googleMap.addMarker(markerOptions);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
        Toast.makeText(emergency.this, "Got Your Location"+" "+"Latitude:"+" "+location.getLatitude()+" "+"Longitude:"+" "+location.getLongitude(), Toast.LENGTH_LONG).show();

    }
});
            }
        });
    }
}