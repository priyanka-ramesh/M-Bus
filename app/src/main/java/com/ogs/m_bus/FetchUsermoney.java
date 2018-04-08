package com.ogs.m_bus;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class FetchUsermoney {
    FirebaseAuth auth123;
    FirebaseDatabase fbdb;
    FirebaseUser user;
    DatabaseReference dbref;
String name; String money;
int flag=0;int result;
    public int fetchmoney(final int cost) {

        auth123 = FirebaseAuth.getInstance();
        fbdb = FirebaseDatabase.getInstance("https://mbus-6b4dd.firebaseio.com/");
        user = FirebaseAuth.getInstance().getCurrentUser();
        dbref = fbdb.getInstance().getReference();
        DatabaseReference ref = dbref.child("Users");
        Users usersinfo1 = new Users();
        name = usersinfo1.getName();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> dataSnapshotsChat = dataSnapshot.getChildren().iterator();
                Users usersinfo = new Users();

                while (dataSnapshotsChat.hasNext()) {
                    DataSnapshot dataSnapshotChild = dataSnapshotsChat.next();

                    usersinfo = dataSnapshotChild.getValue(Users.class);

money=usersinfo.getMoney();
                    Log.d("Value", "Money : " + usersinfo.getMoney());
                    Log.d("Value", "Email : " + usersinfo.getEmail());
                    Log.d("Value", "Name : " + usersinfo.getName());
                    Log.d("Value", "password : " + usersinfo.getPassword());
                    Log.d("Value", "phone number : " + usersinfo.getPhone());
                }
               result = Integer.parseInt(String.valueOf(money));

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
              //  Toast.makeText(buspass.this.getContext(),"Money is  not deduccted from wwallet",Toast.LENGTH_SHORT).show();

            Log.d("messsge:","Money is  not deducted from wallet");}
         });

        Log.d("messge:","wallet:"+result);

 return result;
}}