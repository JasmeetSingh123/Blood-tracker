package com.rodavid20.withsplashscreen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

public class myviewholder extends RecyclerView.ViewHolder {
    ImageView img;
    TextView t1, t2, t3;

    public myviewholder(@NonNull View itemView) {
        super(Objects.requireNonNull(itemView));
        img = (ImageView)itemView.findViewById(R.id.hospital_logo);
        t1=(TextView)itemView.findViewById(R.id.cmp_name);
        t2=(TextView)itemView.findViewById(R.id.cmp_mobile);
        t3=(TextView)itemView.findViewById(R.id.cmp_address);


    }
}

