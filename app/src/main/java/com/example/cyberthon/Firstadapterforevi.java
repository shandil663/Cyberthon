package com.example.cyberthon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Firstadapterforevi extends RecyclerView.Adapter<Firstadapterforevi.MyViewHolder> {
 Context context;
 ArrayList<Helperforevi> list;

    public Firstadapterforevi(Context context, ArrayList<Helperforevi> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.caseviewforevi,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Helperforevi hl= list.get(position);
        holder.txt1.setText(hl.getCase_name());
        holder.txt2.setText(hl.getCase_Description());
        Glide.with(holder.img.getContext()).load(hl.getCase_doc1()).into(holder.img);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
TextView txt1,txt2,txt3,txt4,txt5,txt6,txt7;
ImageView img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt1=itemView.findViewById(R.id.casenamehere);
            txt2=itemView.findViewById(R.id.casedesc);
            img=itemView.findViewById(R.id.eviimg);


        }
    }
}
