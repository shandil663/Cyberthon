package com.example.cyberthon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class training extends AppCompatActivity {
    Firstadapter3 adp;
    ArrayList<helperforinsight> datalist;
    RecyclerView recyclerView;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        recyclerView=findViewById(R.id.casestudy);
        ref= FirebaseDatabase.getInstance().getReference("Phishing_Cases");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        datalist=new ArrayList<>();
        adp=new Firstadapter3(this,datalist);
        recyclerView.setAdapter(adp);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    helperforinsight help=dataSnapshot.getValue(helperforinsight.class);
                    datalist.add(help);
                }
                adp.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}