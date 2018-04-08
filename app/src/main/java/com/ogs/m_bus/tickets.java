package com.ogs.m_bus;


import android.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.pdf417.encoder.BarcodeMatrix;
import com.rengwuxian.materialedittext.MaterialEditText;


import net.glxn.qrgen.android.QRCode;

import java.util.Iterator;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class tickets extends Fragment {
    private TextView s;
    private Button qcon;
    private ImageView qimg;
    private FirebaseDatabase db;
    String qkey[]=new String[100];
    String phone;
    String message;
    MaterialEditText edtphone;
    MaterialEditText mes;
    TextView Otp;
    private int i=0;
    public tickets() {
        // Required empty public constructor
    }
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
Button share;
    String b;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View v= inflater.inflate(R.layout.fragment_tickets, container, false);
     String balance=getArguments().getString("Balance");
        s=(TextView) v.findViewById (R.id.etick);
        qcon=(Button) v.findViewById(R.id.eqr);
        qimg=(ImageView)v.findViewById(R.id.epic);
     Otp=(TextView)v.findViewById(R.id.otp);
        //---------------------
        // Using numeric values
        String numbers = "0123456789";

        // Using random method
        Random rndm_method = new Random();

        char[] otp = new char[4];

        for (int i = 0; i < 4; i++)
        {
            // Use of charAt() method : to get character value
            // Use of nextInt() as it is scanning the value as int
            otp[i] =
                    numbers.charAt(rndm_method.nextInt(numbers.length()));

        }
      b = new String(otp);
        Otp.setText(b);

        //-------------------
        qcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = FirebaseDatabase.getInstance("https://mbus-6b4dd.firebaseio.com/");
                DatabaseReference g=db.getReference();

                 g.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        uinfo u=new uinfo();


                        Iterator<DataSnapshot> dataSnapshotsChat = dataSnapshot.getChildren().iterator();

                        while(dataSnapshotsChat.hasNext()) {
                            DataSnapshot dataSnapshotChild = dataSnapshotsChat.next();

                            //  u = dataSnapshotChild.getValue(uinfo.class);

                            qkey[i] = dataSnapshotChild.getKey().toString();
                            i++;

                        }

                        Log.d("Value", "Key : " + qkey[i-4]);
                        Bitmap bitmape = QRCode.from(qkey[i-4]).bitmap();
                        qimg.setImageBitmap(bitmape);


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                db = FirebaseDatabase.getInstance("https://mbus-6b4dd.firebaseio.com/");
                DatabaseReference f=db.getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());
                f.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Iterator<DataSnapshot> dataSnapshotsChato = dataSnapshot.getChildren().iterator();
                        uinfo u=new uinfo();
                        while(dataSnapshotsChato.hasNext()) {
                            DataSnapshot dataSnapshotChild = dataSnapshotsChato.next();
                            u= dataSnapshotChild.getValue(uinfo.class);
                            // qkey= dataSnapshotChild.getKey().toString();
                            s.setText(u.getSeats());


                            Log.d("Value", "Seats : " + u.getSeats());


                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



            }
        });
        share=(Button) v.findViewById(R.id.route);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        Intent i=new Intent(tickets.this.getContext(),Bmap.class);
       startActivity(i);
            }
        });
  return v;  }

    private void sendSMSMessage() {

        phone = edtphone.getText().toString();
        message = mes.getText().toString();

        if (ContextCompat.checkSelfPermission(tickets.this.getContext(),
                android.Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(tickets.this.getActivity(),
                    android.Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(tickets.this.getActivity(),
                        new String[]{android.Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phone, null, message, null, null);
                    Toast.makeText(tickets.this.getContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                    Log.d("hi", "hello");
                } else {
                    Toast.makeText(tickets.this.getContext(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    Log.d("hi", "hate");
                    return;
                }
            }
        }

    }
    }


