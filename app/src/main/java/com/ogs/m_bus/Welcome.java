package com.ogs.m_bus;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import jp.wasabeef.blurry.Blurry;

public class Welcome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Bundle bundle = getIntent().getExtras();
       uid = bundle.getString("uid");

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragment=null;
        FragmentManager manager =getSupportFragmentManager();
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Toast.makeText(this,"profile",Toast.LENGTH_SHORT).show();
            fragment= new profile();
            }
            else if (id == R.id.nav_buspass) {
            Toast.makeText(this,"buspass",Toast.LENGTH_SHORT).show();
            fragment= new buspass();
            //FragmentManager manager =getSupportFragmentManager();
            //manager.beginTransaction().replace(R.id.relativelayout_frag,buspass,buspass.getTag()).commit();
        }
        else if (id == R.id.nav_busticket) {
            Toast.makeText(this, "bus ticket", Toast.LENGTH_SHORT).show();
            fragment= new busticket();
           // FragmentManager manager = getSupportFragmentManager();
            //manager.beginTransaction().replace(R.id.relativelayout_frag, busticket, busticket.getTag()).commit();
        }
//         else if (id == R.id.nav_remember) {
//            Toast.makeText(this,"remember",Toast.LENGTH_SHORT).show();
//            remember remember = new remember();
//            FragmentManager manager =getSupportFragmentManager();
//            manager.beginTransaction().replace(R.id.relativelayout_frag,remember,remember.getTag()).commit();
//        }
        else if (id == R.id.logout) {
            Toast.makeText(this,"Log out ",Toast.LENGTH_SHORT).show();
            fragment= new logout();
//            FragmentManager manager =getSupportFragmentManager();
//            manager.beginTransaction().replace(R.id.relativelayout_frag,logout,logout.getTag()).commit();
        }

        Bundle bundle = new Bundle();
        bundle.putString("uid",uid);
        fragment.setArguments(bundle);
        manager.beginTransaction().replace(R.id.relativelayout_frag,fragment,fragment.getTag()).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
