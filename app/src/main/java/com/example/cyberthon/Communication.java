package com.example.cyberthon;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.List;

public class Communication extends AppCompatActivity implements View.OnClickListener {
FirebaseAuth oth;
FirebaseDatabase db;
DatabaseReference ref;
UserModal u;
Chatadapter chat;

List<Messagemodal> list;
RecyclerView revice;
EditText msg1;

CardView card;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communication);

        init();
    }

    private void init(){
        oth= FirebaseAuth.getInstance();
        db=FirebaseDatabase.getInstance();
u=new UserModal();
revice=(RecyclerView)findViewById(R.id.chatview);
msg1=(EditText) findViewById(R.id.chatedit);
card=(CardView) findViewById(R.id.send);
card.setOnClickListener(this);
list=new ArrayList<>();


    }

    @Override
    public void onClick(View view) {
if(!TextUtils.isEmpty(msg1.getText().toString())){
    Messagemodal msg=new Messagemodal(msg1.getText().toString(),u.getEmail());
    msg1.setText("");
    ref.push().setValue(msg);
}
else{
    Toast.makeText(this, "Enter msg ", Toast.LENGTH_SHORT).show();
}
    }

    @Override
    protected void onStart() {
        super.onStart();
        final FirebaseUser currentuser=oth.getCurrentUser();
        u.setUid(currentuser.getUid());
        u.setEmail(currentuser.getEmail());
db.getReference("Users").child(oth.getCurrentUser().getUid()).child("email").addListenerForSingleValueEvent(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        u=snapshot.getValue(UserModal.class);
        u.setUid(oth.getCurrentUser().getUid());
        AllMethods.email=u.getEmail();
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});

ref= db.getReference("messages");
ref.addChildEventListener(new ChildEventListener() {
    @Override
    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
Messagemodal msg= snapshot.getValue(Messagemodal.class);
msg.setKey(snapshot.getKey());
list.add(msg);
display(list);
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
Messagemodal msg=snapshot.getValue(Messagemodal.class);
msg.setKey(snapshot.getKey());
List<Messagemodal> newmsg=new ArrayList<Messagemodal>();
for(Messagemodal m:list){
    if(m.getKey().equals(msg.getKey())){
        newmsg.add(msg);
    }

    else{
        newmsg.add(m);
    }
}

list=newmsg;
display(list);

    }


    @Override
    public void onChildRemoved(@NonNull DataSnapshot snapshot) {
Messagemodal msg= snapshot.getValue(Messagemodal.class);
msg.setKey(snapshot.getKey());
        List<Messagemodal> newmsg=new ArrayList<Messagemodal>();
        for(Messagemodal m:list){
            if(!m.getKey().equals(msg.getKey())){
                newmsg.add(m);
            }
        }
        list=newmsg;
        display(list);
    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});

    }

    @Override
    protected void onResume() {
        super.onResume();
        list =new ArrayList<>();

    }

    private void display(List<Messagemodal> list) {
        revice.setLayoutManager(new LinearLayoutManager(Communication.this));
      chat =new Chatadapter(Communication.this,list,ref);
      revice.setAdapter(chat);
    }
}