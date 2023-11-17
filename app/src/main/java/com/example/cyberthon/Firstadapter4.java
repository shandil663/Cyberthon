package com.example.cyberthon;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Firstadapter4 extends RecyclerView.Adapter<Firstadapter4.MyViewHolder> {
    Context context;
    ArrayList<helperforinsight> list;

    public Firstadapter4(Context context, ArrayList<helperforinsight> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.assignedcaseview1, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        helperforinsight hl = list.get(position);
        holder.txt1.setText(hl.getCase_name());
        holder.txt2.setText(hl.getCase_Description());
        holder.txt3.setText(hl.getCase_date());
        holder.txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.txt1.getContext(), casestudy.class);
                holder.txt1.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt1, txt2, txt3;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.casetitle1);
            txt2 = itemView.findViewById(R.id.casecat1);
            txt3 = itemView.findViewById(R.id.descin);

        }
    }
}
