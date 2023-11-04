package com.example.cyberthon;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class Allged extends AppCompatActivity {

    private Uri ImageUri;
    private EditText txt1,txt2;
    String[] items={"Anonymous", "B.D.C", "BDCSector26(Part-II)", "BeatElanteMallPart-1", "BeatKhudaLahora", "Beatpartll", "Burail", "CharanSinghColonyMauliJagran", "CivilSectorPBnHR", "Dadu majra", "Dariya", "Deep Complex Hallo Majra", "DLF Mullanpur", "EWS Colony 1", "EWS COLONY 2", "EWSColony", "Fish Market", "GasColonyDaria", "GawalaColony Govindura,Manimajra", "GrainMarket", "Hallo Majra", "HBCDhanas", "HighCourt", "IAColonyNo.4", "IAPh-III", "Indra Colony", "Industrial Area Phase II", "IT Park", "KabadiMarket", "Kaimbwala", "Khuda jassu", "LakeArea", "LIC,MIGFlatsSector38(West)", "M.C.Dhanas", "MainMarket,Manimajra", "Maloya colony", "Manimajra", "MarbleMarket MarbleMarketDhanas", "ModernHousingComplex,MM", "MotorMarket, Manimajra", "MotorMarketSector38West", "MouliComplex", "NAC,Manimajra", "NewEWSColony1,Maloya", "NewEWSColony2,Maloya", "NewEWSColony3,Maloya", "RailwayColony+NAC", "Raipur Kalan", "Raipur Khurd", "Ram Darbar", "RamDarbarPhasell", "RockGarden", "SanjayColony", "Sector 1", "Sector 10A", "Sector 10D", "Sector 11A", "Sector 11B", "Sector 11D", "Sector 14", "Sector 15A", "Sector 15B", "Sector 15C", "Sector 15D", "Sector 16A", "Sector 16D", "Sector 18", "Sector 19", "Sector 2", "Sector 20", "Sector 21", "Sector 22A", "Sector 22B", "Sector 22C", "Sector 22D", "Sector 23", "Sector 24C", "Sector 24D", "Sector 25", "Sector 25", "Sector 25", "Sector 26", "Sector 27", "Sector 27A", "Sector 27C", "Sector 27D", "Sector 28", "Sector 28 C", "Sector 28 D", "Sector 28A", "Sector 29", "Sector 29D", "Sector 3", "Sector 30", "Sector 31", "Sector 32", "Sector 34"};
    ArrayAdapter<String> adapterItems;
    AutoCompleteTextView txt3;
    private String PhotoUrl;
    private FirebaseAuth oth;
    private Button docbtn;

    private Bitmap bitmap;
    private FirebaseStorage storage;
    private FirebaseFirestore firestore;
    private StorageReference mStorageref;

    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allged);
        oth=FirebaseAuth.getInstance();
        txt1=findViewById(R.id.allegedname);
        txt2=findViewById(R.id.allegedmobile);
        txt3=findViewById(R.id.auto_complete_txt);
        firestore=FirebaseFirestore.getInstance();
        docbtn=findViewById(R.id.algbtn);
        txt3=findViewById(R.id.auto_complete_txt);
        adapterItems=new ArrayAdapter<String>(this,R.layout.list_item,items);
        txt3.setAdapter(adapterItems);
        txt3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item =adapterView.getItemAtPosition(i).toString();
            }
        });
        docbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploadImage();
                startActivity(new Intent(Allged.this, MainPage.class));
            }
        });
        storage=FirebaseStorage.getInstance();
        mStorageref=storage.getReference();
        img=findViewById(R.id.allegedimg);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                CheckStoragepermission();
                pickimage();
            }
        });

    }
    private void pickimage() {
        Intent intent =new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        launcher.launch(intent);
    }

    ActivityResultLauncher<Intent> launcher
            =registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result ->{
                if(result.getResultCode()== Activity.RESULT_OK){
                    Intent data =result.getData();
                    if(data!=null && data.getData()!=null){
                        ImageUri=data.getData();

                        try {
                            bitmap= MediaStore.Images.Media.getBitmap(

                                    getApplication().getContentResolver(),
                                    ImageUri
                            );
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }


                    }

                    if(ImageUri!=null){
                        img.setImageBitmap(bitmap);
                    }
                }
            }
    );
    private void UploadImage(){
        if(ImageUri!=null){
            final  StorageReference myref=mStorageref.child("alleged/"+ImageUri.getLastPathSegment());
            myref.putFile(ImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    myref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            if(uri!=null){
                                PhotoUrl=uri.toString();
                                uploadinfo();

                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }


    }
    private void uploadinfo(){
        String title= txt1.getText().toString().trim();
        String mobile=txt2.getText().toString().trim();
        String location=txt3.getText().toString().trim();

        if(TextUtils.isEmpty(title)){
            Toast.makeText(getApplicationContext(), "Enter all required fields", Toast.LENGTH_LONG).show();
        }
        else {
            DocumentReference documentReference=firestore.collection(oth.getCurrentUser().getUid()).document("Alleged Details");

            Modal3 md=new Modal3(title,PhotoUrl,"",oth.getCurrentUser().getUid(),mobile,location);

            documentReference.set(md, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        if(task.isSuccessful()){
                            String DocId=documentReference.getId();
                            md.setId(DocId);

                            documentReference.set(md,SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(getApplicationContext(), "Complaint registered Successfully", Toast.LENGTH_SHORT).show();
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

    }

}