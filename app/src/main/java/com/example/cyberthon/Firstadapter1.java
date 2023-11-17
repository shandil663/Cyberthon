package com.example.cyberthon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Firstadapter1 extends RecyclerView.Adapter<Firstadapter1.MyViewHolder> {
 Context context;
 ArrayList<Helper> list;

    public Firstadapter1(Context context, ArrayList<Helper> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.assignedcaseview,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Helper hl= list.get(position);
        holder.txt1.setText(hl.getCase_Title());
        holder.txt2.setText(hl.getCase_category());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
TextView txt1,txt2;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt1=itemView.findViewById(R.id.casetitle1);
            txt2=itemView.findViewById(R.id.casecat1);

        }
    }
}
