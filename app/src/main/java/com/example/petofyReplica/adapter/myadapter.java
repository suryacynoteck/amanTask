package com.example.petofyReplica.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.petofyReplica.R;
import com.example.petofyReplica.api_interface.OnButtonListener;
import com.example.petofyReplica.model.Recycler.RecDataPetlist;

import java.util.ArrayList;

public class myadapter extends RecyclerView.Adapter<myadapter.myViewHolder> {

    private ArrayList<RecDataPetlist> dataholder;
    private OnButtonListener buttonListener;
    private  int id;
    Context context;



    public myadapter(ArrayList<RecDataPetlist> dataholder,OnButtonListener buttonListener , Context context) {
        this.dataholder = dataholder;
        this.buttonListener = buttonListener;
        this.context = context;

    }


    @NonNull
    @Override
    public myadapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_design, parent, false);
        return new myViewHolder(view,buttonListener);
    }

    @Override
    public void onBindViewHolder(@NonNull myadapter.myViewHolder holder, int position) {

        Log.d("okok", "position: "+position+", PetName: "+dataholder.get(position).getPetName());

        holder.dogName.setText(dataholder.get(position).getPetName());
        holder.dogDOB.setText(dataholder.get(position).getDateOfBirth());
        holder.doctorName.setText(dataholder.get(position).getPetParentName());

        holder.img.setImageResource(R.drawable.dog);

        String imgUrl =dataholder.get(position).getUser().getProfileImageUrl();
//        Log.d("okok", "img Url: " + imgUrl);


        Glide.with(context)
                .load(imgUrl) // image url
                .placeholder(R.drawable.dog)
                .centerCrop()
                .into(holder.img);  // imageview object



/*

Glide.with(this)
        .load("url here") // image url
        .placeholder(R.drawable.placeholder) // any placeholder to load at start
        .error(R.drawable.imagenotfound)  // any image in case of error
        .override(200, 200) // resizing
        .centerCrop()
        .into(imageView);  // imageview object


**/


    }




    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView img;
        TextView dogName, dogDOB,doctorName;
        Button viewDetailBtn;
        OnButtonListener callBack;


        public myViewHolder(@NonNull View itemView,OnButtonListener buttonListener) {
            super(itemView);

            img = itemView.findViewById(R.id.imgDOG);
            dogName = itemView.findViewById(R.id.txt_dogName);
            dogDOB = itemView.findViewById(R.id.txt_dogDOB);
            doctorName = itemView.findViewById(R.id.txt_doctorName);

            this.callBack = buttonListener;

            viewDetailBtn = itemView.findViewById(R.id.detailsBtn);
            viewDetailBtn.setOnClickListener(this);                 // setting OnClick on Button

        }

        @Override
        public void onClick(View v) {
            if (callBack!=null)

                id = getAdapterPosition();
           double d =  dataholder.get(id).getId();

           callBack.onButtonclick((int)d);

        }
    }

    public void filterList(ArrayList<RecDataPetlist> filterlist) {

        dataholder = filterlist;

        notifyDataSetChanged();

    }

}
