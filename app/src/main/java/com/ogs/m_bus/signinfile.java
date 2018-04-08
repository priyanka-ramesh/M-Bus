package com.ogs.m_bus;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signinfile extends AppCompatActivity {

    private EditText userName ,userEmail, userPwd;
    private Button register;
    private TextView signup;
    private FirebaseAuth appAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signinfile);
        setupValues();
        appAuth= FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()) {
                    String omail = userEmail.getText().toString().trim();
                    String opwd = userPwd.getText().toString().trim();
                    appAuth.createUserWithEmailAndPassword(omail, opwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(signinfile.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(signinfile.this, "Registration Failed", Toast.LENGTH_SHORT).show();

                            }

                        }
                    });
                }




            }
        });

    }
    public void setupValues()
    {
        userName=(EditText) findViewById(R.id.ename);
        userEmail=(EditText)findViewById(R.id.emmail);
        userPwd=(EditText)findViewById(R.id.gpwd);
        register=(Button)findViewById(R.id.esubmit);
        signup=(TextView)findViewById(R.id.esign);
    }
    private boolean validate()
    {
        Boolean result=false;
        String name=userName.getText().toString();
        String  mail=userEmail.getText().toString();
        String pwd=userPwd.getText().toString();
        if(name.isEmpty() && pwd.isEmpty() && mail.isEmpty() )
        {
            Toast.makeText(this,"Please enter all the details ", Toast.LENGTH_SHORT).show();
        }
        else
        {
            result=true;
        }
        return result;
    }

}
