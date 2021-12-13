package com.example.petofyReplica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petofyReplica.model.Login.LoginResponse;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;

    LoginResponse loginResponse;
    private long backPressedTime; // for back button,,
    private Toast backToast;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        getActionBar().hide();

        Toolbar  toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nav = findViewById(R.id.navmenu);
        drawerLayout = findViewById(R.id.drawer);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

      bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
          @Override
          public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                  Fragment selectedFragment = null;

                  switch (item.getItemId()) {

                      case R.id.nav_home:
                          selectedFragment = new HomeFragment();
                          loadFragment(selectedFragment);
                          break;
                      case R.id.nav_fav:
                          selectedFragment = new SettingFragment();
                          loadFragment(selectedFragment);

                          break;

                      case R.id.nav_map:
                          // todo: add 2nd nav bottom

                          break;
                  }
                  return true;
          }
      });


        // setting toggle bar ,,   via code
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

                                //  gettting Info from  Bundle ,,,  from LoginActivity
        Bundle bundle = getIntent().getExtras();

        String head_txt = bundle.getString("head_txt");
        String subhead_txt = bundle.getString("subhead_txt");

        // accessgin Header fields ( txt_head &  txt_subtext)
        View head = nav.getHeaderView(0);
        TextView txt_head = head.findViewById(R.id.txt_Head);
        txt_head.setText(head_txt);

        TextView txt_subhead = head.findViewById(R.id.txt_subHead);
        txt_subhead.setText(subhead_txt);



        loadFragment(new HomeFragment());               // by default ,,  HomeFragment(RecyclerView) is def. layout of MainActivity

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    
                    case R.id.menu_home:

                        loadFragment(new HomeFragment());
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.menu_setting:

                        loadFragment(new SettingFragment());
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.menu_logout:
                        Log.d("okok", "under menu Logout");

                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                        builder.setMessage("Do you want to Exit?")
                                .setTitle("Logout")
                                .setCancelable(true)             //dismissed the dialog when clicking outside of it,
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                        startActivity(intent);

                                    }
                                })
                                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        dialog.cancel();
                                    }
                                });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }

                return true;
            }
        });


//        Toast.makeText(MainActivity.this, "Email:"+loginResponse.getData().getEmail(), Toast.LENGTH_SHORT).show();




    }




    private void loadFragment(Fragment fragment) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainContainer, fragment);
//        transaction.addToBackStack(null);                     //  creating the issue  of back press, Blank Activity

//        transaction.add(R.id.mainContainer, fragment,"backStack");
//        transaction.addToBackStack("back");

        transaction.commit();

    }


    /*
    @Override
    public void onBackPressed() {                           //TODO:  issue: implementing on every fragment  , wanted to run only on HomeFragment
       Log.d("okok", "____onBAckpressed_____");

        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();

//            this.finishAffinity();         // exits the whole app
            super.onBackPressed();

        } else {
            backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }
    */




}