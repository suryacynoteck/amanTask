package com.example.d22_login_p;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.d22_login_p.api_interface.OnButtonListener;
import com.example.d22_login_p.model.Recycler.RecDataPetlist;

import java.util.ArrayList;

public class myadapter extends RecyclerView.Adapter<myadapter.myViewHolder> {

    private ArrayList<RecDataPetlist> dataholder;
    private OnButtonListener onButtonListener;

//    public myadapter(ArrayList<RecDataPetlist> dataholder, Context context) {
//        this.dataholder = dataholder;
//        this.onButtonListener = (OnButtonListener) context;
//    }

    public myadapter(ArrayList<RecDataPetlist> dataholder, OnButtonListener onButtonListener) {
        this.dataholder = dataholder;
        this.onButtonListener = onButtonListener;
    }

    @NonNull
    @Override
    public myadapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_design, parent, false);
        return new myViewHolder(view,onButtonListener);

    }

    @Override
    public void onBindViewHolder(@NonNull myadapter.myViewHolder holder, int position) {
        Log.d("okok2", "under onBind");

        Log.d("okok2", "petName: " + position + " " + dataholder.get(position).getPetName());


        holder.dogName.setText(dataholder.get(position).getPetName());
        holder.dogDOB.setText(dataholder.get(position).getDateOfBirth());
        holder.doctorName.setText(dataholder.get(position).getPetParentName());

        holder.img.setImageResource(R.drawable.dogimg);

 /*
        holder.viewDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Double d = new Double(dataholder.get(position).getId());
                int id = d.intValue();
                Log.d("okok", "id: " + id);


                Bundle bundle = new Bundle();                   // passing id  as Bundle to PetCardFragment
                bundle.putInt("id", id);
                PetCardFragment fragment = new PetCardFragment();
                fragment.setArguments(bundle);



                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment myFragment = new PetCardFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, myFragment).addToBackStack(null).commit();





//                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                transaction.replace(R.id.mainContainer, PetCardFragment.class,null);
//                transaction.commit();

            }
        });
*/


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
        TextView dogName, dogDOB, doctorName;
        Button viewDetail;
        OnButtonListener onButtonListener;

        public myViewHolder(@NonNull View itemView, OnButtonListener onButtonListener) {
            super(itemView);

            viewDetail = itemView.findViewById(R.id.btn_viewDetail);
            img = itemView.findViewById(R.id.imgDOG);
            dogName = itemView.findViewById(R.id.txt_dogName);
            dogDOB = itemView.findViewById(R.id.txt_dogDOB);
            doctorName = itemView.findViewById(R.id.txt_doctorName);
            this.onButtonListener = onButtonListener;

            itemView.setOnClickListener(this);   // assisgning the onclick listenter to itemView  TODO: <-->  button


        }


        @Override
        public void onClick(View v) {

            onButtonListener.onButtonclick(getAdapterPosition());
            notifyDataSetChanged();             // TODO:   uses??
        }
    }

//    public interface OnButtonListener {
//        void onButtonclick(int position);
//    }


}
