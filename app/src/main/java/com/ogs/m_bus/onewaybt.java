package com.ogs.m_bus;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 *

 * A simple {@link Fragment} subclass.
 */
public class onewaybt extends Fragment {
    private FirebaseDatabase db;

    public onewaybt() {

    }
Button proceed;
EditText input_name,input_from,input_to,seats;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v= inflater.inflate(R.layout.fragment_onewaybt, container, false);
          proceed=(Button)v.findViewById(R.id.btn_proceed_bp);
        db=FirebaseDatabase.getInstance("https://mbus-6b4dd.firebaseio.com/");
        input_name=(EditText)v.findViewById(R.id.input_name);
        input_from=(EditText)v.findViewById(R.id.input_from);
        input_to=(EditText)v.findViewById(R.id.input_to);
        seats=(EditText)v.findViewById(R.id.seats);

        proceed.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            uinfo u= new uinfo();



             DatabaseReference dr=db.getReference();
             u.setName(input_name.getText().toString());
             u.setFrom(input_from.getText().toString());
             u.setTo(input_to.getText().toString());
             u.setSeats(seats.getText().toString());

             dr.child("details").child("Route").push().setValue(u);
           //  Toast.makeText(userProfile.this, "Ticket Booked ", Toast.LENGTH_SHORT).show(); uinfo u=new uinfo();


             getFragmentManager()
                     .beginTransaction()
                     .replace(R.id.relativelayout_frag, new payment())
                     .commit();
         }


     });
       return v;
    }

}

