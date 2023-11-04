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

public class Caseevidences extends AppCompatActivity {
RecyclerView recyclerView;
DatabaseReference base;
Firstadapterforevi evi;

ArrayList<Helperforevi> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caseevidences);
        recyclerView=findViewById(R.id.eviview);
base=FirebaseDatabase.getInstance().getReference("Case_Evidence");
recyclerView.setHasFixedSize(true);
recyclerView.setLayoutManager(new LinearLayoutManager(this));
list=new ArrayList<>();
evi=new Firstadapterforevi(this,list);
recyclerView.setAdapter(evi);

base.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        for(DataSnapshot dataSnapshot: snapshot.getChildren()){
            Helperforevi helperforevi=dataSnapshot.getValue(Helperforevi.class);
            list.add(helperforevi);
        } evi.notifyDataSetChanged();
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});
    }
}