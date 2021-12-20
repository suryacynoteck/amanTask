package com.example.petofyReplica;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
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
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.IOException;
import java.util.List;


public class MapFragment extends Fragment implements OnMapReadyCallback {

    SupportMapFragment mapFragment;
    FusedLocationProviderClient client;
    GoogleMap map;
    SearchView searchView;
    Button btn_confirm;
    ConstraintLayout layout;
    private boolean isLoc_enable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_map, container, false);


// initialize map fragment
        mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.google_map);
        client = LocationServices.getFusedLocationProviderClient(getActivity());
        searchView = view.findViewById(R.id.sv_location);
        btn_confirm = view.findViewById(R.id.btn_confirm_search);
        layout = view.findViewById(R.id.map_layout);

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


        Dexter.withContext(getActivity())
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @RequiresApi(api = Build.VERSION_CODES.P)
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                        final LocationManager manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);


                        if (!manager.isLocationEnabled()) {
                            isLoc_enable= showAlertDialog_gps(view,manager);         //todo: make it eff,  via onApp on location,,
                                                                // https://developers.google.com/android/reference/com/google/android/gms/location/SettingsApi
                        }else {
                            isLoc_enable = true;
                        }

                        if (isLoc_enable) {                 // todo: make it execute only,, when if(!manager.___) condition passes
                            getmyLocation();
                        } else {
                            Toast.makeText(getActivity(), "still, permission not given", Toast.LENGTH_SHORT).show();        // TODO: better to show snackbar
                            // todo: issue of overlapping with button on fragment
//        Snackbar snackbar = Snackbar.make(view.findViewById(R.id.google_map),"Turn on the location",    // what iff, wanted to set snackbar in BottomNav.
//                Snackbar.LENGTH_LONG)
//                .setAction(
//                        "retry",
//                        new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//
//                                startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), 0);
//
//                            }
//                        }
//                );
//        snackbar.show();

                        }

                        searchViewImpl();

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


        return view;

    }

    private boolean showAlertDialog_gps(View view,LocationManager manager) {


        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Your GPS is disabled, do you want to enable")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.P)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        startActivityForResult(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), 0);

                        if (manager.isLocationEnabled()) {
                            isLoc_enable = true;          //todo: make it global,,
                        } else {
                            isLoc_enable = false;
                        }
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

        mapFragment.getMapAsync(this);

    }

    private void getmyLocation() {

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

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.map = googleMap;
    }
}