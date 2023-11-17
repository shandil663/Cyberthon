package com.example.cyberthon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class insightscaseview extends AppCompatActivity {
    Firstadapter2 adp;
    ArrayList<helperforinsight> datalist;
    RecyclerView recyclerView;
    DatabaseReference ref;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insightscaseview);
        recyclerView=findViewById(R.id.insightscaseview);
        ref= FirebaseDatabase.getInstance().getReference("Phishing_Cases");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        datalist=new ArrayList<>();
        adp=new Firstadapter2(this,datalist);
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