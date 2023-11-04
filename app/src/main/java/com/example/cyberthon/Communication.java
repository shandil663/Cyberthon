package com.example.cyberthon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.List;

public class Communication extends AppCompatActivity implements View.OnClickListener {
FirebaseAuth oth;
FirebaseDatabase db;
DatabaseReference ref;
UserModal u;
Chatadapter chat;

List<Messagemodal> list;
RecyclerView revice;
EditText msg;

CardView card;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communication);

        init();
    }

    private void init(){
        oth= FirebaseAuth.getInstance();
        db=FirebaseDatabase.getInstance();
u=new UserModal();
revice=(RecyclerView)findViewById(R.id.chatview);
msg=(EditText) findViewById(R.id.chatedit);
card=(CardView) findViewById(R.id.send);
card.setOnClickListener(this);
list=new ArrayList<>();


    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        final FirebaseUser currentuser=oth.getCurrentUser();
        u.setUid(currentuser.getUid());
        u.setEmail(currentuser.getEmail());

    }
}