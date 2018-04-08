package com.ogs.m_bus;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class profile extends Fragment {

TextView username,user_email,u_phone,password,u_money;
    FirebaseAuth auth123;
    FirebaseDatabase fbdb;
    FirebaseUser user;
    DatabaseReference dbref;
    String userid;
    String name;
//    String qkey[]=new String[100];
    int i=0;
    public profile() {
        // Required empty public constructor
    }

String uid;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v=  inflater.inflate(R.layout.fragment_profile, container, false);
        auth123=FirebaseAuth.getInstance();
        fbdb=FirebaseDatabase.getInstance("https://mbus-6b4dd.firebaseio.com/");
        user = FirebaseAuth.getInstance().getCurrentUser();
 dbref=fbdb.getInstance().getReference();
       DatabaseReference ref = dbref.child("Users");
        Users usersinfo=new Users();
name=usersinfo.getName();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                          Iterator<DataSnapshot> dataSnapshotsChat = dataSnapshot.getChildren().iterator();

                while(dataSnapshotsChat.hasNext()) {
                    DataSnapshot dataSnapshotChild = dataSnapshotsChat.next();
                    Users usersinfo = new Users();

                    usersinfo= dataSnapshotChild.getValue(Users.class);


                    Log.d("Value", "Money : " +usersinfo.getMoney());
                    Log.d("Value", "Email : " +usersinfo.getEmail());
                    Log.d("Value", "Name : " +usersinfo.getName());
                    Log.d("Value", "password : " +usersinfo.getPassword());
                    Log.d("Value", "phone number : " +usersinfo.getPhone());

                    username=(TextView)v.findViewById(R.id.username);
                                       user_email=(TextView)v.findViewById(R.id.emailid);
                    u_phone=(TextView)v.findViewById(R.id.pno);
                    password=(TextView)v.findViewById(R.id.password);
                   // u_money=(TextView)v.findViewById(R.id.Money);
                    username.setText(usersinfo.getName());
                    user_email.setText(usersinfo.getEmail());
                    u_phone.setText(usersinfo.getPhone());
                    password.setText(usersinfo.getPassword());
                   // u_money.setText(usersinfo.getMoney());
                    // Inflate the layout for this fragment
                    if (getArguments() != null) {

                        userid  = getArguments().getString("uid");
                    }
                  //  UID.setText(userid);

                    //textView13.setText(name);
                }





            }

//            private void showdata(DataSnapshot dataSnapshot) {
//                for(DataSnapshot ds : dataSnapshot.getChildren()){
//                    Users usersinfo=new Users();
////                    usersinfo.setName(ds.child(userid).getValue(Users.class).getName());
////                    usersinfo.setEmail(ds.child(uid).getValue(Users.class).getEmail());
////                    usersinfo.setMoney(ds.child(uid).getValue(Users.class).getMoney());
//                    Log.d("User name retrieved","Email :"+usersinfo.getEmail());
//                    Log.d("User name retrieved","Name :"+usersinfo.getName());
//                    Log.d("User name retrieved","Money :"+usersinfo.getMoney());
//                    Toast.makeText(profile.this.getContext(),"Email :"+usersinfo.getEmail()+"Name :"+usersinfo.getName()+"Money :"+usersinfo.getMoney(),Toast.LENGTH_SHORT).show();
//                }
//            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });
//        if (user != null) {
//            // Name, email address, and profile photo Url
////            String userEmail = user.getEmail();
//           String userid1 = user.getUid();
//     //            Log.d("User name retrieved","email :"+userEmail);
////            Log.d("User name retrieved","uid :"+userid1);
////        }

        return v;

}}
