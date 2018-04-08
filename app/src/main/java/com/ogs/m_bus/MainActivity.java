package com.ogs.m_bus;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends Activity {

    //varible declaration
     Button btnsignin,btnsignup;
    RelativeLayout rootlayout;
  // Firebase

  private FirebaseDatabase db,db1;
private FirebaseAuth auth;
    private FirebaseAuth signinauth;
    DatabaseReference rootRef,demoRef;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                                              .setDefaultFontPath("fonts/Arkhip_font.ttf")
                                              .setFontAttrId(R.attr.fontPath)
                                              .build());
        setContentView(R.layout.activity_main);
        auth=FirebaseAuth.getInstance();

        //Init view
        btnsignup = (Button)findViewById(R.id.btn_register);
        btnsignin = (Button)findViewById(R.id.btn_signin);
rootlayout=(RelativeLayout)findViewById(R.id.rootlayout);

btnsignup.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
       // startActivity(new Intent(MainActivity.this,signinfile.class));
        showResisterDialog();
    }
});

  btnsignin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          showLoginDialog();
      }
  });      }
    private void showLoginDialog() {
    final EditText edtEmail=(EditText)findViewById(R.id.edt_Email);
        final EditText edtpass=(EditText)findViewById(R.id.edt_pass);

        //database reference pointing to root of database
        rootRef = FirebaseDatabase.getInstance().getReference();
        //database reference pointing to demo node
        demoRef = rootRef.child("Users");

                //check validation
                if (TextUtils.isEmpty(edtEmail.getText().toString())) {

                    Snackbar.make(rootlayout, "Please enter email address", Snackbar.LENGTH_LONG).show();
                    return;

                }
                if (TextUtils.isEmpty(edtpass.getText().toString())) {

                    Snackbar.make(rootlayout, "Please enter password", Snackbar.LENGTH_LONG).show();
                    return;

                }
                if (edtpass.getText().toString().length() < 6) {

                    Snackbar.make(rootlayout, "Password too short !!", Snackbar.LENGTH_LONG).show();
                    return;

                }
                final String edtname=edtEmail.getText().toString().trim();
                final  String edtPass=edtpass.getText().toString().trim();
                auth.signInWithEmailAndPassword(edtname, edtPass)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.

                                if (!task.isSuccessful()) {
                                    // there was an error

                                        Toast.makeText(MainActivity.this, "login failed", Toast.LENGTH_LONG).show();

                                } else {

                                    Toast.makeText(MainActivity.this, "login success", Toast.LENGTH_LONG).show();

                                    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
                                    Toast.makeText(MainActivity.this, "User id " + currentFirebaseUser.getUid(), Toast.LENGTH_SHORT).show();
                                    Log.d("TAG", "User id " + currentFirebaseUser.getUid());
 Intent intent = new Intent(MainActivity.this, Welcome.class);
                                    intent.putExtra("uid", currentFirebaseUser.getUid());
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }

    private void showResisterDialog() {

    AlertDialog.Builder dialog= new AlertDialog.Builder(this);
    dialog.setTitle("SIGN UP");
    dialog.setMessage("Please use  email to register" );
    LayoutInflater inflater= LayoutInflater .from(this);
     View signup = inflater.inflate(R.layout.register,null);
     final MaterialEditText edtEmail=signup.findViewById(R.id.edt_Email);
    final MaterialEditText edtpass=signup.findViewById(R.id.edt_pass);
    final MaterialEditText edtname=signup.findViewById(R.id.edt_name);
    final MaterialEditText edtphone=signup.findViewById(R.id.edt_phone);
dialog.setView(signup);

// set buttons
        db=FirebaseDatabase.getInstance("https://mbus-6b4dd.firebaseio.com/");

    dialog.setPositiveButton("SIGN UP",new DialogInterface.OnClickListener(){
        @Override
                public void onClick(DialogInterface dialogInterface ,int i){
            dialogInterface.dismiss();
            //check validation
            final String edtName=edtEmail.getText().toString().trim();
            final  String edtPass=edtpass.getText().toString().trim();

            auth.createUserWithEmailAndPassword(edtName, edtPass)
                    .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (TextUtils.isEmpty(edtEmail.getText().toString())) {

                                Snackbar.make(rootlayout, "Please enter email address", Snackbar.LENGTH_LONG).show();
                                return;

                            }
                            if ((TextUtils.isEmpty(edtpass.getText().toString()))) {

                                Snackbar.make(rootlayout, "Please enter password", Snackbar.LENGTH_LONG).show();
                                return;

                            }  if (TextUtils.isEmpty(edtname.getText().toString())) {

                                Snackbar.make(rootlayout, "Please enter password", Snackbar.LENGTH_LONG).show();
                                return;

                            }if ((TextUtils.isEmpty(edtphone.getText().toString()))&& (TextUtils.isDigitsOnly(edtpass.getText().toString()))) {

                                Snackbar.make(rootlayout, "Please enter password", Snackbar.LENGTH_LONG).show();
                                return;

                            }
                            if (edtpass.getText().toString().length() < 6) {

                                Snackbar.make(rootlayout, "Password too short !!", Snackbar.LENGTH_LONG).show();
                                return;

                            }
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Authentication failed." + task.getException(),
                                        Toast.LENGTH_SHORT).show();
                            } else {

                                Toast.makeText(MainActivity.this, "Signed Up Successfully", Toast.LENGTH_SHORT).show();

                              Users u=new Users();
                                DatabaseReference dr=db.getReference();
                                u.setEmail(edtEmail.getText().toString().trim());
                                u.setPassword(edtpass.getText().toString());
                                u.setName(edtname.getText().toString());
                                u.setPhone(edtphone.getText().toString());
                                u.setMoney("5000");
                                dr.child("Users").push().setValue(u);
                            }
                        }
                    });
        }

    });

    dialog.setNegativeButton("CANCEL", new OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
        }
    });
    dialog.show();
    }

}