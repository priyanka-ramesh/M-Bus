package com.ogs.m_bus;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Bundle bundle = getIntent().getExtras();

//Extract the data…
        String stuff = bundle.getString("c");
        Log.d("value map ",stuff);
        // Add a marker in Sydney and move the camera
        if (stuff.equalsIgnoreCase("1")) {
            LatLng a1 = new LatLng(13.080762, 80.181832);
            mMap.addMarker(new MarkerOptions().position(a1).title("Arrives in 5 mins at JJ Nagar East"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(a1, 12.23f));
            LatLng a10 = new LatLng(13.082053, 80.275602);
            mMap.addMarker(new MarkerOptions().position(a10).title("Reaches  Chennai Central in 20 minutes"));
            //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(a10,13.23f));
            List<ColoredPoint> sourcePoints = new ArrayList<>();

            ////////bus number a1= JJ Nagar East to Chennai Central
            sourcePoints.add(new ColoredPoint(new LatLng(13.080762, 80.181832), Color.BLUE));
            sourcePoints.add(new ColoredPoint(new LatLng(13.085110, 80.187475), Color.BLUE));
            sourcePoints.add(new ColoredPoint(new LatLng(13.086865, 80.188098), Color.BLUE));
            sourcePoints.add(new ColoredPoint(new LatLng(13.085465, 80.198934), Color.BLUE));
            sourcePoints.add(new ColoredPoint(new LatLng(13.084775, 80.216186), Color.BLUE));
            sourcePoints.add(new ColoredPoint(new LatLng(13.084133, 80.224887), Color.BLUE));
            sourcePoints.add(new ColoredPoint(new LatLng(13.083098, 80.229715), Color.BLUE));
            sourcePoints.add(new ColoredPoint(new LatLng(13.083056, 80.241130), Color.BLUE));
            sourcePoints.add(new ColoredPoint(new LatLng(13.085094, 80.244918), Color.BLUE));
            sourcePoints.add(new ColoredPoint(new LatLng(13.082053, 80.275602), Color.BLUE));

            ////////////// bus number a2 =  JJ Nagar East to Chennai Central;

//Bus number b1 = Police Station to
//     sourcePoints.add(new ColoredPoint(new LatLng( 11.018939,76.9605), Color.GREEN));
//       sourcePoints.add(new ColoredPoint(new LatLng( 11.018949,76.9605), Color.GREEN));
//       sourcePoints.add(new ColoredPoint(new LatLng(11.0198,76.966), Color.GREEN));
//      sourcePoints.add(new ColoredPoint(new LatLng(11.0186,76.966), Color.GREEN));
//       sourcePoints.add(new ColoredPoint(new LatLng(11.0164,76.971), Color.GREEN));
//        sourcePoints.add(new ColoredPoint(new LatLng(11.0115,76.972), Color.GREEN));
//        sourcePoints.add(new ColoredPoint(new LatLng(11.013391,76.986), Color.GREEN));
            showPolyline(sourcePoints);
        }
        if (stuff.equalsIgnoreCase("2")) {
            LatLng a1 = new LatLng(13.080762, 80.181832);
            mMap.addMarker(new MarkerOptions().position(a1).title("Arrives in 15 mins at JJ Nagar East"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(a1, 12.23f));
            LatLng a10 = new LatLng(13.082053, 80.275602);
            mMap.addMarker(new MarkerOptions().position(a10).title("Reaches Chennai Central 10 mins"));
            //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(a10,13.23f));
            List<ColoredPoint> sourcePoints = new ArrayList<>();

//            sourcePoints.add(new ColoredPoint(new LatLng(11.018939, 76.9605), Color.GREEN));
//            sourcePoints.add(new ColoredPoint(new LatLng(11.018949, 76.9605), Color.GREEN));
//            sourcePoints.add(new ColoredPoint(new LatLng(11.0198, 76.966), Color.GREEN));
//            sourcePoints.add(new ColoredPoint(new LatLng(11.0186, 76.966), Color.GREEN));
//            sourcePoints.add(new ColoredPoint(new LatLng(11.0164, 76.971), Color.GREEN));
//            sourcePoints.add(new ColoredPoint(new LatLng(11.0115, 76.972), Color.GREEN));
//            sourcePoints.add(new ColoredPoint(new LatLng(11.013391, 76.986), Color.GREEN));
//            sourcePoints.add(new ColoredPoint(new LatLng(13.088648, 80.169547), Color.RED));
//            sourcePoints.add(new ColoredPoint(new LatLng(13.084342, 80.1701912), Color.RED));
            //sourcePoints.add(new ColoredPoint(new LatLng(13.081332 ,80.176885), Color.RED));

            sourcePoints.add(new ColoredPoint(new LatLng(13.080762, 80.181832), Color.RED));
            sourcePoints.add(new ColoredPoint(new LatLng(13.062396, 80.166157), Color.RED));
            sourcePoints.add(new ColoredPoint(new LatLng(13.067370, 80.181349), Color.RED));
            sourcePoints.add(new ColoredPoint(new LatLng(13.059971, 80.195554), Color.RED));
            sourcePoints.add(new ColoredPoint(new LatLng(13.047387, 80.196069), Color.RED));
            sourcePoints.add(new ColoredPoint(new LatLng(13.049854, 80.212419), Color.RED));
            sourcePoints.add(new ColoredPoint(new LatLng(13.053700, 80.234692), Color.RED));
            sourcePoints.add(new ColoredPoint(new LatLng(13.056919, 80.256751), Color.RED));
            sourcePoints.add(new ColoredPoint(new LatLng(13.075689, 80.278466), Color.RED));
            sourcePoints.add(new ColoredPoint(new LatLng(13.081291, 80.275119), Color.RED));
            sourcePoints.add(new ColoredPoint(new LatLng(13.082053, 80.275602), Color.RED));
            showPolyline(sourcePoints);
        }
        if (stuff.equalsIgnoreCase("3")) {
            LatLng a1 = new LatLng(13.080762, 80.181832);
            mMap.addMarker(new MarkerOptions().position(a1).title("Arrives in 20 minutes at JJ Nagar East"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(a1, 12.23f));
            LatLng a10 = new LatLng(13.082053, 80.275602);
            mMap.addMarker(new MarkerOptions().position(a10).title("Reaches Chennai Central in 45 minutes "));
            //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(a10,13.23f));
            List<ColoredPoint> sourcePoints = new ArrayList<>();

//            sourcePoints.add(new ColoredPoint(new LatLng(11.018939, 76.9605), Color.GREEN));
//            sourcePoints.add(new ColoredPoint(new LatLng(11.018949, 76.9605), Color.GREEN));
//            sourcePoints.add(new ColoredPoint(new LatLng(11.0198, 76.966), Color.GREEN));
//            sourcePoints.add(new ColoredPoint(new LatLng(11.0186, 76.966), Color.GREEN));
//            sourcePoints.add(new ColoredPoint(new LatLng(11.0164, 76.971), Color.GREEN));
//            sourcePoints.add(new ColoredPoint(new LatLng(11.0115, 76.972), Color.GREEN));
//            sourcePoints.add(new ColoredPoint(new LatLng(11.013391, 76.986), Color.GREEN));
//            sourcePoints.add(new ColoredPoint(new LatLng(13.088648, 80.169547), Color.RED));
//            sourcePoints.add(new ColoredPoint(new LatLng(13.084342, 80.1701912), Color.RED));
            //sourcePoints.add(new ColoredPoint(new LatLng(13.081332 ,80.176885), Color.RED));

            sourcePoints.add(new ColoredPoint(new LatLng(13.080762, 80.181832), Color.GREEN));
            sourcePoints.add(new ColoredPoint(new LatLng(13.062396, 80.166157), Color.GREEN));
            sourcePoints.add(new ColoredPoint(new LatLng(13.067370, 80.181349), Color.GREEN));
            sourcePoints.add(new ColoredPoint(new LatLng(13.059971, 80.195554), Color.GREEN));
            sourcePoints.add(new ColoredPoint(new LatLng(13.047387, 80.196069), Color.GREEN));
            sourcePoints.add(new ColoredPoint(new LatLng(13.049854, 80.212419), Color.GREEN));
            sourcePoints.add(new ColoredPoint(new LatLng(13.053700, 80.234692), Color.GREEN));
            sourcePoints.add(new ColoredPoint(new LatLng(13.056919, 80.256751), Color.GREEN));
            sourcePoints.add(new ColoredPoint(new LatLng(13.075689, 80.278466), Color.GREEN));
            sourcePoints.add(new ColoredPoint(new LatLng(13.081291, 80.275119), Color.GREEN));
            sourcePoints.add(new ColoredPoint(new LatLng(13.082053, 80.275602), Color.GREEN));
            showPolyline(sourcePoints);
        }
        if (stuff.equalsIgnoreCase("4")) {
            LatLng a1 = new LatLng(13.080762, 80.181832);
            mMap.addMarker(new MarkerOptions().position(a1).title("Arrives in 10 minutes at JJ Nagar East"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(a1, 12.23f));
            LatLng a10 = new LatLng(13.082053, 80.275602);
            mMap.addMarker(new MarkerOptions().position(a10).title("Reaches Chennai Central in 25 mins "));
            //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(a10,13.23f));
            List<ColoredPoint> sourcePoints = new ArrayList<>();

//            sourcePoints.add(new ColoredPoint(new LatLng(11.018939, 76.9605), Color.GREEN));
//            sourcePoints.add(new ColoredPoint(new LatLng(11.018949, 76.9605), Color.GREEN));
//            sourcePoints.add(new ColoredPoint(new LatLng(11.0198, 76.966), Color.GREEN));
//            sourcePoints.add(new ColoredPoint(new LatLng(11.0186, 76.966), Color.GREEN));
//            sourcePoints.add(new ColoredPoint(new LatLng(11.0164, 76.971), Color.GREEN));
//            sourcePoints.add(new ColoredPoint(new LatLng(11.0115, 76.972), Color.GREEN));
//            sourcePoints.add(new ColoredPoint(new LatLng(11.013391, 76.986), Color.GREEN));
//            sourcePoints.add(new ColoredPoint(new LatLng(13.088648, 80.169547), Color.RED));
//            sourcePoints.add(new ColoredPoint(new LatLng(13.084342, 80.1701912), Color.RED));
            //sourcePoints.add(new ColoredPoint(new LatLng(13.081332 ,80.176885), Color.RED));

            sourcePoints.add(new ColoredPoint(new LatLng(13.080762, 80.181832), Color.YELLOW));
            sourcePoints.add(new ColoredPoint(new LatLng(13.062396, 80.166157), Color.YELLOW));
            sourcePoints.add(new ColoredPoint(new LatLng(13.067370, 80.181349), Color.YELLOW));
            sourcePoints.add(new ColoredPoint(new LatLng(13.059971, 80.195554), Color.YELLOW));
            sourcePoints.add(new ColoredPoint(new LatLng(13.047387, 80.196069), Color.YELLOW));
            sourcePoints.add(new ColoredPoint(new LatLng(13.049854, 80.212419), Color.YELLOW));
            sourcePoints.add(new ColoredPoint(new LatLng(13.053700, 80.234692), Color.YELLOW));
            sourcePoints.add(new ColoredPoint(new LatLng(13.056919, 80.256751), Color.YELLOW));
            sourcePoints.add(new ColoredPoint(new LatLng(13.075689, 80.278466), Color.YELLOW));
            sourcePoints.add(new ColoredPoint(new LatLng(13.081291, 80.275119), Color.YELLOW));
            sourcePoints.add(new ColoredPoint(new LatLng(13.082053, 80.275602), Color.YELLOW));
            showPolyline(sourcePoints);
        }
    }


    private void showPolyline(List<ColoredPoint> points) {

        if (points.size() < 2)
            return;

        int ix = 0;
        ColoredPoint currentPoint  = points.get(ix);
        int currentColor = currentPoint.color;
        List<LatLng> currentSegment = new ArrayList<>();
        currentSegment.add(currentPoint.coords);
        ix++;

        while (ix < points.size()) {
            currentPoint = points.get(ix);

            if (currentPoint.color == currentColor) {
                currentSegment.add(currentPoint.coords);
            } else {
                currentSegment.add(currentPoint.coords);
                mMap.addPolyline(new PolylineOptions()
                        .addAll(currentSegment)
                        .color(currentColor)
                        .width(20));
                currentColor = currentPoint.color;
                currentSegment.clear();
                currentSegment.add(currentPoint.coords);
            }

            ix++;
        }

        mMap.addPolyline(new PolylineOptions()
                .addAll(currentSegment)
                .color(currentColor)
                .width(20));

    }
}

