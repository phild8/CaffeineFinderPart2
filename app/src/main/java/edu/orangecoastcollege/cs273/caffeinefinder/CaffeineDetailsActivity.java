package edu.orangecoastcollege.cs273.caffeinefinder;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class CaffeineDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private CaffeineLocation mSelectedCaffeineLocation;
    // Done: Add member variable for Location mMyLocation;
    private Location mMyLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caffeine_details);

        TextView nameTextView = (TextView) findViewById(R.id.nameTextView);
        TextView addressTextView = (TextView) findViewById(R.id.addressTextView);
        TextView phoneTextView = (TextView) findViewById(R.id.phoneTextView);
        TextView positionTextView = (TextView) findViewById(R.id.positionTextView);
        mSelectedCaffeineLocation = getIntent().getExtras().getParcelable("SelectedLocation");
        nameTextView.setText(mSelectedCaffeineLocation.getName());
        addressTextView.setText(mSelectedCaffeineLocation.getFullAddress());
        phoneTextView.setText(mSelectedCaffeineLocation.getPhone());
        positionTextView.setText(mSelectedCaffeineLocation.getFormattedLatLng());

        // Done: Get the parcelable MyLocation from the intent and assign it to the member variable mMyLocation
        mMyLocation = getIntent().getExtras().getParcelable("MyLocation");


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.caffeineDetailsMapFragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng coordinate = new LatLng(mSelectedCaffeineLocation.getLatitude(), mSelectedCaffeineLocation.getLongitude());
        mMap.addMarker(new MarkerOptions().position(coordinate).title(mSelectedCaffeineLocation.getName()));

        // Done: Add another LatLng coordinate named myCoordinate based off mMyLocation.
        // Done: Add a custom marker at myCoordinate
        // Done: Move the camera position to target myCoordinate
        LatLng myCoordinate = new LatLng(mMyLocation.getLatitude(), mMyLocation.getLongitude());
        mMap.addMarker(new MarkerOptions()
                .position(myCoordinate)
                .title("Current Location")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.my_marker)));

        CameraPosition cameraPosition = new CameraPosition.Builder().target(myCoordinate).zoom(18.0f).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        mMap.moveCamera(cameraUpdate);
    }
}
