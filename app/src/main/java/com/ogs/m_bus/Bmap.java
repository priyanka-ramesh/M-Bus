package com.ogs.m_bus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.lang.reflect.Array;

public class Bmap extends AppCompatActivity {

    private Spinner spin;
    private Button sub;
    private String text;
    private String c;
    private Button avail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmap);
        spin=(Spinner)findViewById(R.id.espin);
        ArrayAdapter<CharSequence>adp= ArrayAdapter.createFromResource(this,R.array.bus,android.R.layout.simple_spinner_item);

        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adp);


        sub=(Button)findViewById(R.id.ereg);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = spin.getSelectedItem().toString();
                if(text.equalsIgnoreCase("5C")) {
                    Intent i = new Intent(Bmap.this,MapsActivity2.class);

                    Bundle bundle=new Bundle();
                    bundle.putString("c","1");
                    i.putExtras(bundle);

                    startActivity(i);

                }
                else
                if(text.equalsIgnoreCase("7H"))
                {

                    Intent i = new Intent(Bmap.this,MapsActivity2.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("c","2");
                    i.putExtras(bundle);


                    startActivity(i);
                }
                else

                if(text.equalsIgnoreCase("7H")) {
                    Intent i = new Intent(Bmap.this,MapsActivity2.class);

                    Bundle bundle=new Bundle();
                    bundle.putString("c","3");
                    i.putExtras(bundle);

                    startActivity(i);


                }
                else

                if (text.equalsIgnoreCase("M70")) {
                    Intent i = new Intent(Bmap.this, MapsActivity2.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("c", "4");
                    i.putExtras(bundle);

                    startActivity(i);

                }


            }
        });



    }
}





