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
 * A simple {@link Fragment} subclass.
 */
public class roundtripbt extends Fragment {


    public roundtripbt() {
        // Required empty public constructor
    } private FirebaseDatabase db1;
    EditText input_name,input_from,input_to,seats;
Button proceed;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_roundtripbt, container, false);
        proceed = (Button)v.findViewById(R.id.btn_proceed_bp);

        input_name=(EditText)v.findViewById(R.id.input_name);
        input_from=(EditText)v.findViewById(R.id.input_from);
        input_to=(EditText)v.findViewById(R.id.input_to);
        seats=(EditText)v.findViewById(R.id.seats);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db1=FirebaseDatabase.getInstance("https://mbus-6b4dd.firebaseio.com/");
                DatabaseReference data=db1.getReference();
                DatabaseReference dat=db1.getReference();

                //     DatabaseReference dr1=db.getReference();
                uinfo user = new uinfo();
                uinfo us = new uinfo();

                user.setName(input_name.getText().toString());
                user.setFrom(input_from.getText().toString());
                user.setTo(input_to.getText().toString());
                user.setSeats(seats.getText().toString());

                data.push().child("tickets").child("1way").setValue(user);

                user.setName(input_name.getText().toString());
                user.setFrom(input_to.getText().toString());
                user.setTo(input_from.getText().toString());
                user.setSeats(seats.getText().toString());

                dat.child("tickets").child("Return").push().setValue(us);
                Toast.makeText(roundtripbt.this.getContext(),"Ticket Booked for round trip", Toast.LENGTH_SHORT).show();
                //  Toast.makeText(userProfile.this, "Ticket Booked ", Toast.LENGTH_SHORT).show(); uinfo u=new uinfo();


                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.relativelayout_frag, new payment())
                        .commit();
            }
//

        });
        return v;
    }
    public void firebaseeg(EditText from ,EditText to,EditText seat,EditText name){





    }

}
