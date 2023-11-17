package com.example.cyberthon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
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
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.StorageReference;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    AutoCompleteTextView autoCompleteTxt;
    EditText txt1,txt2,txt3,txt4;
    private FirebaseFirestore firestore;
    private FirebaseAuth oth;
    private StorageReference mStorageref;
    Button btn;
    String[] items={"Anonymous", "B.D.C", "BDCSector26(Part-II)", "BeatElanteMallPart-1", "BeatKhudaLahora", "Beatpartll", "Burail", "CharanSinghColonyMauliJagran", "CivilSectorPBnHR", "Dadu majra", "Dariya", "Deep Complex Hallo Majra", "DLF Mullanpur", "EWS Colony 1", "EWS COLONY 2", "EWSColony", "Fish Market", "GasColonyDaria", "GawalaColony Govindura,Manimajra", "GrainMarket", "Hallo Majra", "HBCDhanas", "HighCourt", "IAColonyNo.4", "IAPh-III", "Indra Colony", "Industrial Area Phase II", "IT Park", "KabadiMarket", "Kaimbwala", "Khuda jassu", "LakeArea", "LIC,MIGFlatsSector38(West)", "M.C.Dhanas", "MainMarket,Manimajra", "Maloya colony", "Manimajra", "MarbleMarket MarbleMarketDhanas", "ModernHousingComplex,MM", "MotorMarket, Manimajra", "MotorMarketSector38West", "MouliComplex", "NAC,Manimajra", "NewEWSColony1,Maloya", "NewEWSColony2,Maloya", "NewEWSColony3,Maloya", "RailwayColony+NAC", "Raipur Kalan", "Raipur Khurd", "Ram Darbar", "RamDarbarPhasell", "RockGarden", "SanjayColony", "Sector 1", "Sector 10A", "Sector 10D", "Sector 11A", "Sector 11B", "Sector 11D", "Sector 14", "Sector 15A", "Sector 15B", "Sector 15C", "Sector 15D", "Sector 16A", "Sector 16D", "Sector 18", "Sector 19", "Sector 2", "Sector 20", "Sector 21", "Sector 22A", "Sector 22B", "Sector 22C", "Sector 22D", "Sector 23", "Sector 24C", "Sector 24D", "Sector 25", "Sector 25", "Sector 25", "Sector 26", "Sector 27", "Sector 27A", "Sector 27C", "Sector 27D", "Sector 28", "Sector 28 C", "Sector 28 D", "Sector 28A", "Sector 29", "Sector 29D", "Sector 3", "Sector 30", "Sector 31", "Sector 32", "Sector 34"};
    ArrayAdapter<String> adapterItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();
        txt1=findViewById(R.id.namecom);
        txt2=findViewById(R.id.email);
        txt3=findViewById(R.id.mobile);
        oth=FirebaseAuth.getInstance();
        txt4=findViewById(R.id.house);
        autoCompleteTxt=findViewById(R.id.auto_complete_txt);
        btn=findViewById(R.id.movenxt);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=txt1.getText().toString().trim();
                firestore=FirebaseFirestore.getInstance();
                String email=txt2.getText().toString().trim();
                String mobile=txt3.getText().toString().trim();
                String house=txt4.getText().toString().trim();
                String location=autoCompleteTxt.getText().toString().trim();

               if(TextUtils.isEmpty(name)&&TextUtils.isEmpty(email)&&TextUtils.isEmpty(mobile)&&TextUtils.isEmpty(house)&&TextUtils.isEmpty(location)){
                   Toast.makeText(MainActivity.this, "Enter details", Toast.LENGTH_SHORT).show();
               }
               else{
                   DocumentReference documentReference=firestore.collection(oth.getCurrentUser().getUid()).document("Complainant Details");
                   Modal md=new Modal(name,email,mobile,house,location);
                   documentReference.set(md, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {
                           if(task.isSuccessful()){
                               if(task.isSuccessful()){


                                   documentReference.set(md,SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                       @Override
                                       public void onComplete(@NonNull Task<Void> task) {
                                           if(task.isSuccessful()){
                                               if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                                                   if(checkSelfPermission(android.Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED){
                                                       try {

                                                           SmsManager sms = SmsManager.getDefault();
                                                           sms.sendTextMessage("+919149380289", null, "Dear Bhomik your compliant has been successfully  registered.\nFIR No: 3416", null, null);

                                                       } catch (Exception e) {


                                                           e.printStackTrace();

                                                       }

                                                   }
                                                   else {
                                                       requestPermissions(new String[]{Manifest.permission.SEND_SMS},1);
                                                   }
                                               }
                                               Toast.makeText(getApplicationContext(), "Complainant details success", Toast.LENGTH_SHORT).show();
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
                   startActivity(new Intent(MainActivity.this,Incidentdetails.class));
               }

            }
        });



        autoCompleteTxt=findViewById(R.id.auto_complete_txt);
        adapterItems=new ArrayAdapter<String>(this,R.layout.list_item,items);
        autoCompleteTxt.setAdapter(adapterItems);
        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item =adapterView.getItemAtPosition(i).toString();
            }
        });
    }
}