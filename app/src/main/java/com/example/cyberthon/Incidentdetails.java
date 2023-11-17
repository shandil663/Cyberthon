package com.example.cyberthon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.StorageReference;

public class Incidentdetails extends AppCompatActivity {
 AutoCompleteTextView txt1,txt2;
    EditText txt3,txt4,txt5,txt6;
    private FirebaseFirestore firestore;
    private FirebaseAuth oth;
    private StorageReference mStorageref;
    Button btn;
    String[] items={"Anonymous", "B.D.C", "BDCSector26(Part-II)", "BeatElanteMallPart-1", "BeatKhudaLahora", "Beatpartll", "Burail", "CharanSinghColonyMauliJagran", "CivilSectorPBnHR", "Dadu majra", "Dariya", "Deep Complex Hallo Majra", "DLF Mullanpur", "EWS Colony 1", "EWS COLONY 2", "EWSColony", "Fish Market", "GasColonyDaria", "GawalaColony Govindura,Manimajra", "GrainMarket", "Hallo Majra", "HBCDhanas", "HighCourt", "IAColonyNo.4", "IAPh-III", "Indra Colony", "Industrial Area Phase II", "IT Park", "KabadiMarket", "Kaimbwala", "Khuda jassu", "LakeArea", "LIC,MIGFlatsSector38(West)", "M.C.Dhanas", "MainMarket,Manimajra", "Maloya colony", "Manimajra", "MarbleMarket MarbleMarketDhanas", "ModernHousingComplex,MM", "MotorMarket, Manimajra", "MotorMarketSector38West", "MouliComplex", "NAC,Manimajra", "NewEWSColony1,Maloya", "NewEWSColony2,Maloya", "NewEWSColony3,Maloya", "RailwayColony+NAC", "Raipur Kalan", "Raipur Khurd", "Ram Darbar", "RamDarbarPhasell", "RockGarden", "SanjayColony", "Sector 1", "Sector 10A", "Sector 10D", "Sector 11A", "Sector 11B", "Sector 11D", "Sector 14", "Sector 15A", "Sector 15B", "Sector 15C", "Sector 15D", "Sector 16A", "Sector 16D", "Sector 18", "Sector 19", "Sector 2", "Sector 20", "Sector 21", "Sector 22A", "Sector 22B", "Sector 22C", "Sector 22D", "Sector 23", "Sector 24C", "Sector 24D", "Sector 25", "Sector 25", "Sector 25", "Sector 26", "Sector 27", "Sector 27A", "Sector 27C", "Sector 27D", "Sector 28", "Sector 28 C", "Sector 28 D", "Sector 28A", "Sector 29", "Sector 29D", "Sector 3", "Sector 30", "Sector 31", "Sector 32", "Sector 34"};

    String[] items1={"Online Fraud and Scams", "Phishing", "Advance Fee Fraud", "Online Shopping Fraud", "Identity Theft", "Personal Information Theft", "Financial Identity Theft", "Malware and Ransomware", "Malicious Software (Malware)", "Ransomware", "Cyberbullying", "Online Harassment", "Revenge Porn", "Data Breaches", "Unauthorized Access", "Data Leaks", "Hacking", "Unauthorized Access", "Denial of Service (DoS) Attacks", "Online Extortion", "Doxing", "Threats and Blackmail", "Online Child Exploitation", "Child Pornography", "Online Grooming", "Social Engineering", "Manipulation"};
    ArrayAdapter<String> adapterItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incidentdetails);
        firestore=FirebaseFirestore.getInstance();
        txt3=findViewById(R.id.place);
        oth=FirebaseAuth.getInstance();
        txt4=findViewById(R.id.datefrom);
        txt5=findViewById(R.id.dateto);
        txt6=findViewById(R.id.desc);
        txt1=findViewById(R.id.auto_complete_txt);
        txt2=findViewById(R.id.auto_complete_txt1);
        txt1=findViewById(R.id.auto_complete_txt);
        btn=findViewById(R.id.movenxt1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String place=txt3.getText().toString().trim();
                String datefrom=txt4.getText().toString().trim();
                String dateto=txt5.getText().toString().trim();
                String desc=txt6.getText().toString().trim();
                String loc2=txt1.getText().toString().trim();
                String Nature=txt2.getText().toString().trim();

                if(TextUtils.isEmpty(place)&&TextUtils.isEmpty(datefrom)&&TextUtils.isEmpty(dateto)&&TextUtils.isEmpty(desc)&&TextUtils.isEmpty(loc2)&&TextUtils.isEmpty(Nature)){

                    Toast.makeText(Incidentdetails.this, "Enter all details", Toast.LENGTH_SHORT).show();
                }

                else{
                    DocumentReference documentReference=firestore.collection(oth.getCurrentUser().getUid()).document("Incident Details");

                    Modal1 md=new Modal1(place,datefrom,dateto,desc,loc2,Nature);

                    documentReference.set(md, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                if(task.isSuccessful()){
                                    documentReference.set(md,SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(getApplicationContext(), "Incident  details success", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }

                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }


                startActivity(new Intent(Incidentdetails.this,complaintdocuments.class));

            }
        });
        adapterItems=new ArrayAdapter<String>(this,R.layout.list_item,items);
        txt1.setAdapter(adapterItems);
        txt1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item =adapterView.getItemAtPosition(i).toString();
            }
        });
        adapterItems=new ArrayAdapter<String>(this,R.layout.list_item,items1);
        txt2.setAdapter(adapterItems);
        txt2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item =adapterView.getItemAtPosition(i).toString();
            }
        });

    }
}