package in.leaf.abhi.rajasthanhackathon;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.renderscript.Script;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import in.leaf.abhi.rajasthanhackathon.pojos.Temp_Rating;

public class TopPlacesActivity extends AppCompatActivity implements OnMapReadyCallback,ExpandableListView.OnChildClickListener{
    String category;
    String userid;
    Spinner spinner;
    ExpandableListView expandableListView;
    PlacesAdapter placesAdapter;
    ProgressBar progressBar;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    RatingBar rb;

    LocationManager locationManager;
    private FusedLocationProviderClient mFusedLocationClient;
    LocationListener locationListener;
    private GoogleMap mMap;
    double lon, lat, alt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_places);
        Bundle b=getIntent().getExtras();
        category=b.getString("category");
        userid=b.getString("userid");
        expandableListView=(ExpandableListView)findViewById(R.id.expandableListView);
        progressBar =findViewById(R.id.progressBar);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();

        //*************************MAPS*********************************************************
        Log.i("tag","inside map segment");
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if(ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED&&
                ContextCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED) {
            Log.i("tag","inside if part");
            mFusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            Log.i("tag","inside onSuccess");
                            if (location != null) {
                                lat = location.getLatitude();
                                lon = location.getLongitude();
                                Toast.makeText(TopPlacesActivity.this,String.valueOf(lat)+String.valueOf(lon),Toast.LENGTH_LONG).show();
                            }
                            else
                                Toast.makeText(TopPlacesActivity.this,"null location",Toast.LENGTH_LONG).show();
                        }
                    });

            // Obtain the SupportMapF   ragment and get notified when the map is ready to be used.
        }
        else {
            Log.i("tag","else part");
            Toast.makeText(this,"Location denied",Toast.LENGTH_SHORT);
        }
        Log.i("tag","outside map sement");
        //*************************MAPS*******************************************************

        spinner=(Spinner)findViewById(R.id.spinner);
        ArrayList<String> cities=new ArrayList<>();
        cities.add("Jaipur");
        cities.add("Udaipur");
        cities.add("Ajmer");
        spinner.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,cities));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                String city=(String)adapterView.getItemAtPosition(pos);
                ScriptRunner sr=new ScriptRunner(expandableListView,placesAdapter,TopPlacesActivity.this,progressBar);
                sr.execute(userid,category,city,new LatLng(lat,lon));
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Log.i("tag","nothing selected");
            }

        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

    @Override
    public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
        return false;
    }


}
