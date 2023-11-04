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

public class complaintdocuments extends AppCompatActivity {

    private Uri ImageUri;
    private EditText txt1;
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
        setContentView(R.layout.activity_complaintdocuments);
        oth=FirebaseAuth.getInstance();
        txt1=findViewById(R.id.desc1);
        firestore=FirebaseFirestore.getInstance();
        docbtn=findViewById(R.id.docbtn);
        docbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploadImage();
                startActivity(new Intent(complaintdocuments.this,Allged.class));
            }
        });
        storage=FirebaseStorage.getInstance();
        mStorageref=storage.getReference();
        img=findViewById(R.id.doccom);
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
            final  StorageReference myref=mStorageref.child("photo/"+ImageUri.getLastPathSegment());
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

        if(TextUtils.isEmpty(title)){
            Toast.makeText(getApplicationContext(), "Enter all required fields", Toast.LENGTH_LONG).show();
        }
        else {
            DocumentReference documentReference=firestore.collection(oth.getCurrentUser().getUid()).document("Incident related documents");

            Modal2 md=new Modal2(title,PhotoUrl,"",oth.getCurrentUser().getUid());

            documentReference.set(md, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        if(task.isSuccessful()){
                            String DocId=documentReference.getId();
                            md.setImageid(DocId);

                            documentReference.set(md,SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(getApplicationContext(), "Uploaded Successfully", Toast.LENGTH_SHORT).show();
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