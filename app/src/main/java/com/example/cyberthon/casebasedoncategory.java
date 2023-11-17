package com.example.cyberthon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class casebasedoncategory extends AppCompatActivity {
    AutoCompleteTextView txt1;
    ArrayAdapter<String> adapterItems;
    private FirebaseFirestore firestore;
    private FirebaseAuth oth;
    private StorageReference mStorageref;
    Button btn;

    Firstadapter adp;
    ArrayList<Helper> datalist;
    RecyclerView recyclerView;
    DatabaseReference ref;
    TextView txt;

    String[] items1={"Online Fraud and Scams", "Phishing", "Advance Fee Fraud", "Online Shopping Fraud", "Identity Theft", "Personal Information Theft", "Financial Identity Theft", "Malware and Ransomware", "Malicious Software (Malware)", "Ransomware", "Cyberbullying", "Online Harassment", "Revenge Porn", "Data Breaches", "Unauthorized Access", "Data Leaks", "Hacking", "Unauthorized Access", "Denial of Service (DoS) Attacks", "Online Extortion", "Doxing", "Threats and Blackmail", "Online Child Exploitation", "Child Pornography", "Online Grooming", "Social Engineering", "Manipulation"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_casebasedoncategory);
        txt1=findViewById(R.id.auto_complete_txt1);
        recyclerView=findViewById(R.id.catfilterhere);
        ref= FirebaseDatabase.getInstance().getReference("Case_Details").child("Unsolved_Cases");
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

        adapterItems=new ArrayAdapter<String>(this,R.layout.list_item,items1);
        txt1.setAdapter(adapterItems);
        txt1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item =adapterView.getItemAtPosition(i).toString();
            }
        });
    }


}

