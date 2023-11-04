package com.example.cyberthon;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.color.DynamicColors;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import org.checkerframework.checker.nullness.qual.NonNull;

public class Registeration extends AppCompatActivity {
TextView txt;

FirebaseAuth mAuth;
FirebaseDatabase db;
EditText txt1,txt2;
Button btn;
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            startActivity(new Intent(Registeration.this,MainPage.class));
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DynamicColors.applyToActivitiesIfAvailable(this.getApplication());
        setContentView(R.layout.activity_registeration);
        mAuth=FirebaseAuth.getInstance();
        txt1=findViewById(R.id.email);
        txt2=findViewById(R.id.pass);
        btn=findViewById(R.id.regbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, pass;
                email=String.valueOf(txt1.getText());
                pass=String.valueOf(txt2.getText());

                if(TextUtils.isEmpty(email)&&TextUtils.isEmpty(pass)){
                    Toast.makeText(Registeration.this, "Enter required fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email,pass)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    db=FirebaseDatabase.getInstance();
                                    FirebaseUser user= mAuth.getCurrentUser();
                                    db.getReference("Users").child(user.getUid()).child("email").setValue(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@androidx.annotation.NonNull Task<Void> task) {
                                            Toast.makeText(Registeration.this, "Values added", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    Toast.makeText(Registeration.this, "Registered", Toast.LENGTH_SHORT).show();


                                    startActivity(new Intent(getApplicationContext(),Login.class));
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(Registeration.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });


            }
        });
        txt=findViewById(R.id.loginpageopen);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });
    }
}