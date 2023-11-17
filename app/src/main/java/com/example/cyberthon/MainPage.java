package com.example.cyberthon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainPage extends AppCompatActivity {

    CardView card1,card2,card3,cardprofile,chatcard1,emergencycard,assigncard,insigts,training,category,fakeloadcard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
     card1=findViewById(R.id.newcard);
     card2=findViewById(R.id.casecard);
     card3=findViewById(R.id.evicard1);
     chatcard1=findViewById(R.id.chatcard1);
     emergencycard=findViewById(R.id.emergencycard);
     assigncard=findViewById(R.id.assignedcasescard);
     insigts=findViewById(R.id.incard);
     training=findViewById(R.id.Resources);
     category=findViewById(R.id.category);
     fakeloadcard=findViewById(R.id.load);
     fakeloadcard.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             startActivity(new Intent(MainPage.this, oading.class));
         }
     });
     category.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             startActivity(new Intent(MainPage.this,casebasedoncategory.class));
         }
     });
     training.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             startActivity(new Intent(MainPage.this,training.class));
         }
     });


     insigts.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             startActivity(new Intent(MainPage.this, insights.class));
         }
     });
     assigncard.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             startActivity(new Intent(MainPage.this, caseassigned.class));
         }
     });
     emergencycard.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             startActivity(new Intent(MainPage.this, emergency.class));
         }
     });
     chatcard1.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             startActivity(new Intent(MainPage.this, Communication.class));
         }
     });
     cardprofile=findViewById(R.id.profile);
     cardprofile.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             startActivity(new Intent(MainPage.this,Profile1.class));
         }
     });
     card3.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             startActivity(new Intent(MainPage.this, Caseevidences.class));
         }
     });
     card1.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             startActivity(new Intent(MainPage.this,MainActivity.class));
         }
     });

     card2.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             startActivity(new Intent(MainPage.this, Solvedunsolved.class));
         }
     });

    }
}