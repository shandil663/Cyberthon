package com.example.cyberthon;

import android.content.Context;
import android.graphics.Color;
import android.text.style.AlignmentSpan;
import android.view.CollapsibleActionView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class Chatadapter  extends RecyclerView.Adapter<Chatadapter.ChatViewHolder> {
Context context;
List<Messagemodal> messages;
DatabaseReference msgdb;
 public Chatadapter(Context context,List<Messagemodal> messages, DatabaseReference msgdb){
     this.context=context;
     this.messages=messages;
     this.msgdb=msgdb;
 }
    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.msg,parent,false);
        return new ChatViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
Messagemodal msg= messages.get(position);
               if(msg.getName().equals(AllMethods.email)){
    holder.txt.setText("You: " + msg.getMessage());
    holder.txt.setGravity(Gravity.START);
    holder.L1.setBackgroundColor(Color.parseColor("#EF9E73"));

        }

        else {
            holder.txt.setText(msg.getName() + ":"  +msg.getMessage());


        }


    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {
TextView txt;
LinearLayout L1;
        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            txt=itemView.findViewById(R.id.txt1);
            L1=itemView.findViewById(R.id.L1msg);
        }
    }
}
