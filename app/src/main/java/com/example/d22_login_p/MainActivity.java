package com.example.d22_login_p;

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

import com.example.d22_login_p.model.Login.LoginResponse;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;

    LoginResponse loginResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getActionBar().hide();

        Toolbar  toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nav = findViewById(R.id.navmenu);
        drawerLayout = findViewById(R.id.drawer);

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



        loadFragment(new HomeFragment());

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
//        transaction.replace(R.id.mainContainer, fragment);

        transaction.add(R.id.mainContainer, fragment,"backStack");                  //TODO: Fragment backstack1
        transaction.addToBackStack("back");

        transaction.commit();

    }


}