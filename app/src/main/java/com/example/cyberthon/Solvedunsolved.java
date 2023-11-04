package com.example.cyberthon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Solvedunsolved extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference ref;
    TextView txt;
    Button btn1,btn2;

    Firstadapter adp;
    ArrayList<Helper> datalist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solvedunsolved);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        txt=findViewById(R.id.txt);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt.setText("Unsolved Cases");
                recyclerView=findViewById(R.id.caseview);
                ref= FirebaseDatabase.getInstance().getReference("Case_Details").child("Unsolved_Cases");
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(Solvedunsolved.this));
                datalist=new ArrayList<>();
                adp=new Firstadapter(Solvedunsolved.this,datalist);
                recyclerView.setAdapter(adp);

                ref.addValueEventListener(new ValueEventListener() {
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
            }
        }); btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt.setText("Solved Cases");
                recyclerView=findViewById(R.id.caseview);
                ref= FirebaseDatabase.getInstance().getReference("Case_Details").child("Solved_Cases");
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(Solvedunsolved.this));
                datalist=new ArrayList<>();
                adp=new Firstadapter(Solvedunsolved.this,datalist);
                recyclerView.setAdapter(adp);

                ref.addValueEventListener(new ValueEventListener() {
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

            }
        });
        recyclerView=findViewById(R.id.caseview);
        ref= FirebaseDatabase.getInstance().getReference("Case_Details").child("Solved_Cases");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        datalist=new ArrayList<>();
        adp=new Firstadapter(this,datalist);
        recyclerView.setAdapter(adp);

        ref.addValueEventListener(new ValueEventListener() {
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
    }
}