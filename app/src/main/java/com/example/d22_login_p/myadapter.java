package com.example.d22_login_p;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.d22_login_p.model.RecResponse;

import java.util.ArrayList;

public class myadapter extends RecyclerView.Adapter<myadapter.myViewHolder> {

    ArrayList<RecResponse> dataholder;

    public myadapter(ArrayList<RecResponse> dataholder) {
        this.dataholder = dataholder;
    }

    @NonNull
    @Override
    public myadapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_design, parent, false);
        return new myViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull myadapter.myViewHolder holder, int position) {

        holder.dogName.setText(dataholder.get(position).getDogName());
        holder.dogDOB.setText(dataholder.get(position).getDogDOB());
        holder.doctorName.setText(dataholder.get(position).getDoctorName());

        holder.img.setImageResource(dataholder.get(position).getImage());



    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView dogName, dogDOB, doctorName;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.imgDOG);
            dogName = itemView.findViewById(R.id.txt_dogName);
            dogDOB = itemView.findViewById(R.id.txt_dogDOB);
            doctorName = itemView.findViewById(R.id.txt_doctorName);




        }
    }
}
