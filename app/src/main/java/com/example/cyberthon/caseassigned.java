package com.example.cyberthon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class caseassigned extends AppCompatActivity {
    Firstadapter1 adp;
    ArrayList<Helper> datalist;
    RecyclerView rec1,rec2;
    DatabaseReference ref,re1;

    Button btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caseassigned);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Search Cases ");
        rec1=findViewById(R.id.unseen);
        rec2=findViewById(R.id.seen);
        re1= FirebaseDatabase.getInstance().getReference("Case_Details").child("Unsolved_Cases");
        rec2.setHasFixedSize(true);
        rec1.setHasFixedSize(true);
        rec2.setLayoutManager(new LinearLayoutManager(caseassigned.this));
        rec1.setLayoutManager(new LinearLayoutManager(caseassigned.this));
        datalist=new ArrayList<>();
        adp=new Firstadapter1(caseassigned.this,datalist);
        rec2.setAdapter(adp);
        rec1.setAdapter(adp);

        re1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Helper help=dataSnapshot.getValue(Helper.class);
                    datalist.add(help);
                }
                adp.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                recyclerView=findViewById(R.id.unseen);
//                ref= FirebaseDatabase.getInstance().getReference("Case_Details").child("Unsolved_Cases");
//                recyclerView.setHasFixedSize(true);
//                recyclerView.setLayoutManager(new LinearLayoutManager(caseassigned.this));
//                datalist=new ArrayList<>();
//                adp=new Firstadapter(caseassigned.this,datalist);
//                recyclerView.setAdapter(adp);
//
//                ref.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        for(DataSnapshot dataSnapshot: snapshot.getChildren()){
//                            Helper help=dataSnapshot.getValue(Helper.class);
//                            datalist.add(help);
//                        }
//                        adp.notifyDataSetChanged();
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//            }
//        }); btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                recyclerView=findViewById(R.id.unseen);
//                ref= FirebaseDatabase.getInstance().getReference("Case_Details").child("Solved_Cases");
//                recyclerView.setHasFixedSize(true);
//                recyclerView.setLayoutManager(new LinearLayoutManager(caseassigned.this));
//                datalist=new ArrayList<>();
//                adp=new Firstadapter(caseassigned.this,datalist);
//                recyclerView.setAdapter(adp);
//
//                ref.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        for(DataSnapshot dataSnapshot: snapshot.getChildren()){
//                            Helper help=dataSnapshot.getValue(Helper.class);
//                            datalist.add(help);
//                        }
//                        adp.notifyDataSetChanged();
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//
//            }
//        });
//        recyclerView=findViewById(R.id.unseen);
//        ref= FirebaseDatabase.getInstance().getReference("Case_Details").child("Solved_Cases");
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        datalist=new ArrayList<>();
//        adp=new Firstadapter(this,datalist);
//        recyclerView.setAdapter(adp);
//
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
//                    Helper help=dataSnapshot.getValue(Helper.class);
//                    datalist.add(help);
//                }
//                adp.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
    }
}