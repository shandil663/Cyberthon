package com.example.cyberthon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Firstadapter extends RecyclerView.Adapter<Firstadapter.MyViewHolder> {
 Context context;
 ArrayList<Helper> list;

    public Firstadapter(Context context, ArrayList<Helper> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.caseview,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Helper hl= list.get(position);
        holder.txt1.setText(hl.getCase_Title());
        holder.txt2.setText(hl.getCase_category());
        holder.txt3.setText(hl.getCase_name());
        holder.txt4.setText(hl.getStart());
        holder.txt5.setText(hl.getCase_Closing_date());
        holder.txt6.setText(hl.getVictim());
        holder.txt7.setText(hl.getSuspect());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
TextView txt1,txt2,txt3,txt4,txt5,txt6,txt7;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt1=itemView.findViewById(R.id.casetitle);
            txt2=itemView.findViewById(R.id.casecat);
            txt3=itemView.findViewById(R.id.locationcase);
            txt4=itemView.findViewById(R.id.start);
            txt5=itemView.findViewById(R.id.close);
            txt6=itemView.findViewById(R.id.victim);
            txt7=itemView.findViewById(R.id.suspect);
        }
    }
}
