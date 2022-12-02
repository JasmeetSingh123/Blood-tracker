package com.rodavid20.withsplashscreen;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adaptar extends RecyclerView.Adapter<myviewholder> {
    List<modelclass> data;
    Context context;

    public Adaptar(Context context, List<modelclass> data) {


        this.data = data;
        this.context= (Context) context;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.single_row,parent,false);
        return  new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {

        final modelclass temp=data.get(position);

        holder.t1.setText(data.get(position).getTextview1());
        holder.t2.setText(data.get(position).getTextview2());
        holder.t3.setText(data.get(position).getTextview3());
        holder.img.setImageResource(R.drawable.bbank);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context,aftercamplistdesign.class);
                intent.putExtra("imageView",R.drawable.bbank);
                intent.putExtra("textview1",temp.getTextview1());
                intent.putExtra("textview2",temp.getTextview2());
                intent.putExtra("textview3",temp.getTextview3());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
