package com.example.petofyReplica;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.IOException;
import java.util.List;


public class MapFragment extends Fragment implements OnMapReadyCallback {

   private SupportMapFragment mapFragment;
    private FusedLocationProviderClient client;
    private GoogleMap map;
    private SearchView searchView;
    private Button btn_confirm;
    private boolean isLoc_enable;
    //    private LocationManager mManager;
    private boolean is_PlayServicePermission_granted;
    private LocationRequest locationRequest;

    @Override
    public void onStart() {
        super.onStart();
        Log.d("on123", "under onStart>>> ");



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("on123", "Under onCreateView >>>");
        View view = inflater.inflate(R.layout.fragment_map, container, false);


        client = LocationServices.getFusedLocationProviderClient(getActivity());
        searchView = view.findViewById(R.id.sv_location);
        btn_confirm = view.findViewById(R.id.btn_confirm_search);

        dexter_call();   // for easy permission access

        if (is_PlayServicePermission_granted) {

            // initialize map fragment
            mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.google_map);
            mapFragment.getMapAsync(this);

            checkGps();
//                getmyLocation();

            searchViewImpl();

        } else {
            Toast.makeText(getActivity(), "Google Play services not available", Toast.LENGTH_SHORT).show();
            
        }

        
        return view;
    }

    private void dexter_call() {
        Dexter.withContext(getActivity())
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @RequiresApi(api = Build.VERSION_CODES.P)
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                        is_PlayServicePermission_granted = true;

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        //TODO: show Snackbar
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();

    }

    //todo: this f() should work,, after pressing on googleGps dialog ok,,    update depcriated
//   depcriated,,  new way >> https://stackoverflow.com/a/63654043/17435316
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("on123", "under onActivityResult ");
        if (requestCode == 101) {
            if (resultCode == RESULT_OK) {
                Log.d("on123", "under onActivityResult , reqCode : OK ");
                Toast.makeText(getActivity(), "now GPS is enable", Toast.LENGTH_SHORT).show();
                getmyLocation();
            }
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getActivity(), "GPS not granted", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.map = googleMap;
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        map.setMyLocationEnabled(true);



    }



    private void checkGps() {
        // src: https://stackoverflow.com/a/50796199/17435316
        //      https://developer.android.com/training/location/change-location-settings.html
        locationRequest =new LocationRequest().create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(3000);
        Log.d("on123", "checkgps 1.1");
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest)
                .setAlwaysShow(true);

        Task<LocationSettingsResponse> task = LocationServices.getSettingsClient(getContext())
                .checkLocationSettings(builder.build());

        task.addOnSuccessListener(getActivity(), new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(@NonNull LocationSettingsResponse locationSettingsResponse) {
                try {
                    Log.d("on123", "checkgps 1.2");
                    LocationSettingsResponse response = task.getResult(ApiException.class);      // => gps already enabled
                    // All location settings are satisfied. The client can initialize
                    // location requests here.
                    Toast.makeText(getActivity(), "gps already enabled", Toast.LENGTH_SHORT).show();
                    Log.d("on123", "checkgps 1.2: gps already enabled ");
                    getmyLocation();

                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
        });
        task.addOnFailureListener(getActivity(), new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("on123", "checkgps 1.3 -- onFailure");
                if (e instanceof ResolvableApiException) {
                    // Location settings are not satisfied, but this can be fixed
                    // by showing the user a dialog.
                    try {
                        Log.d("on123", "checkgps 1.3 -- onFailure try{}");
                        ResolvableApiException resolvable = (ResolvableApiException) e;
                        resolvable.startResolutionForResult(getActivity(),101); // passing Req, code -- which will check on>> onActivityResult
                        Log.d("on123", "checkgps 1.3 -- onFailure  reqCode 101");
                    } catch (IntentSender.SendIntentException sendIntentException) {
                        sendIntentException.printStackTrace();
                    }

                }

            }
        });



    }

    // AlertDialog -- open --> gps settting
/*
    private boolean showAlertDialog_gps() {     // no use ,,

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Your GPS is disabled, do you want to enable")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), 0);

                        }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        isLoc_enable = false;
                    }
                });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();


return isLoc_enable;
    }
*/

    private void searchViewImpl() {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                String location = searchView.getQuery().toString();
                List<Address> addressList = null;

                if (location != null || !location.equals("")) {

                    Geocoder geocoder = new Geocoder(getActivity());
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

                    btn_confirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            loadMarkerOptions(latLng);
                        }
                    });


                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


    }

    private void getmyLocation() {
        Log.d("on123", "under getMyLocation ");
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> task = client.getLastLocation();         // ab code generated directly by google,, by this code's error line
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {          //  (in case:Mobile current location off) at time of debugging  "location = null", will show,,

                mapFragment.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(@NonNull GoogleMap googleMap) {
                        map = googleMap;
                        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

                        loadMarkerOptions(latLng);

                        Toast.makeText(getActivity(), "You are here", Toast.LENGTH_LONG).show();

                    }
                });

            }
        });


    }


    private void loadMarkerOptions(LatLng latLng) {

        MarkerOptions markerOptions = new MarkerOptions();   // Initialize marker options
        markerOptions.position(latLng);      // set position of marker
        markerOptions.title(latLng.latitude + " : " + latLng.longitude);    // set title of marker
//        map.clear();      // remove all marker  , which  this f is called onceAgain


        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));   // animating to zoom the marker
        map.addMarker(markerOptions);     // add marker on map
    }



    // Async map
        /*
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {

                // when map is loaded
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(@NonNull LatLng latLng) {  // when map is clicked

                        loadMarkerOptions(latLng,googleMap);

                    }
                });

            }
        });
*/

}