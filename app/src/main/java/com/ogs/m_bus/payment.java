package com.ogs.m_bus;


import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class payment extends Fragment {


    public payment() {
        // Required empty public constructor
    }
Button btnpayment,btnpayment2;
TextView pay;
     MaterialEditText edtphone;
     MaterialEditText mes;
    String phone;
    String message;
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_payment, container, false);
      // pay=(TextView)view.findViewById(R.id.fprice);
       btnpayment= (Button)view.findViewById(R.id.btnpayment);
       //btnpayment2= (Button)view.findViewById(R.id.btnpayment2);
//        btnpayment2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getFragmentManager()
//                .beginTransaction()
//                .replace(R.id.relativelayout_frag, new profile())
//                .commit();
//            }
//        });
        btnpayment.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
    String amt = getArguments().getString("Money");
    Log.d("Message","money"+amt);
  //      String balance = getArguments().getString("Balance");
//pay.setText(amt);
        // Fragment fragment = new payment();
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                 FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.rl_buspass, payment);
//
//                fragmentTransaction.commit();
//        getFragmentManager()
//                .beginTransaction()
//                .replace(R.id.relativelayout_frag, new share())
//                .commit();
//tickets tick=new tickets();
  //      Bundle args = new Bundle();

    //    args.putString("Balance",balance);
      //  tick.setArguments(args);

        tickets tick= new tickets();
        FragmentManager manager =getFragmentManager();
        Bundle bundle = new Bundle();
        bundle.putString("Money",amt);
        tick.setArguments(bundle);
        manager.beginTransaction().replace(R.id.relativelayout_frag,tick,tick.getTag()).commit();

//        getFragmentManager()
//                .beginTransaction()
//                .replace(R.id.relativelayout_frag, new tickets())
//                .commit();

    }
});
        return view;
    }
void showshare(){
    AlertDialog.Builder dialog= new AlertDialog.Builder(payment.this.getContext());
    dialog.setTitle("Share Details");
    dialog.setMessage("Please fill the Phone number to share details " );
    LayoutInflater inflater= LayoutInflater .from(payment.this.getContext());
    View v = inflater.inflate(R.layout.fragment_share,null);
 edtphone=v.findViewById(R.id.edt_phone);
mes=v.findViewById(R.id.edt_message);

    // final MaterialEditText edtname=signup.findViewById(R.id.edt_name);
    //  final MaterialEditText edtphone=signup.findViewById(R.id.edt_phone);
    dialog.setView(v);
// set buttons


    dialog.setPositiveButton("Share",new DialogInterface.OnClickListener(){
        @Override
        public void onClick(DialogInterface dialogInterface ,int i) {
            dialogInterface.dismiss();
            //to send message
            sendSMSMessage();




        }});

    dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
        }
    });
    dialog.show();
}

    private void sendSMSMessage() {
        phone = edtphone.getText().toString();
        message = mes.getText().toString();

        if (ContextCompat.checkSelfPermission(payment.this.getContext(),
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(payment.this.getActivity(),
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(payment.this.getActivity(),
                        new String[]{Manifest.permission.SEND_SMS},
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
                    Toast.makeText(payment.this.getContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                    Log.d("hi", "hello");
                } else {
                    Toast.makeText(payment.this.getContext(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    Log.d("hi", "hate");
                    return;
                }
            }
        }

    }
    }
