package com.example.d22_login_p;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.d22_login_p.model.pet.PetDetail;
import com.example.d22_login_p.model.pet.PetDetailData;
import com.example.d22_login_p.model.pet.PetDetailDataUser;
import com.example.d22_login_p.model.pet.SetPetid;
import com.example.d22_login_p.model.pet.SetPetidpetData;
import com.example.d22_login_p.retrofit.ApiClient;
import com.example.d22_login_p.retrofit.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PetCardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PetCardFragment extends Fragment {

    //  Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private UserService apiInterface3;
    private FragmentActivity fragmentActivity;

    private TextView petname,petBreedname,petage,petID,gender;
    private TextView petParentname, ph_number, address;
    private ImageView dogimg;

    public PetCardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PetCardFragment.
     */
    //  Rename and change types and number of parameters

    public static PetCardFragment newInstance(String param1, String param2) {
        PetCardFragment fragment = new PetCardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pet_card, container, false);


        dogimg = view.findViewById(R.id.petcard_dogimg);

        petname = view.findViewById(R.id.petcard_petName);
        petage = view.findViewById(R.id.petcard_age);
        petBreedname = view.findViewById(R.id.petcard_breed);
        petID = view.findViewById(R.id.petcard_petID);
        gender = view.findViewById(R.id.petcard_gender);


        petParentname = view.findViewById(R.id.petcard_petParent);
        ph_number = view.findViewById(R.id.petcard_phno);
        address = view.findViewById(R.id.petcard_address);

        Log.d("okok", " 6 " );


        int  myInt = 9269;          // def value setting,,

        Bundle bundle = this.getArguments();
        if (bundle != null) {
             myInt = bundle.getInt("position", 9269);
        }
        Log.d("okok", "ID in PetCardFragment: " + myInt);



        apiInterface3 = ApiClient.getClient(getActivity()).create(UserService.class);

        SetPetidpetData id = new SetPetidpetData();
        id.setId(myInt);
        SetPetid setPetid = new SetPetid();
        setPetid.setPetData(id);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("token", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "this is string token");

        Call<PetDetail> petDetailCall = apiInterface3.getpetDetail(setPetid, token);

        petDetailCall.enqueue(new Callback<PetDetail>() {
            @Override
            public void onResponse(Call<PetDetail> call, Response<PetDetail> response) {


                if (response.isSuccessful()) {
                    Log.d("okok", "response code: " + response.body().getData().getPetName());
                    Log.d("okok", "petid: " + response.body().getData().getPetUniqueId());
                    Log.d("okok", "age: " + response.body().getData().getPetAge());

                    PetDetailData petDetailData = response.body().getData();

//                    PetDetailDataUser userObj = response.body().getData().getUser();               no need,  insead  userObj.getAddress()  <--> petDetailData.getUser().getAddress()

                    dogimg.setImageResource(R.drawable.dogimggg);

                    petname.setText(petDetailData.getPetName());
                    petBreedname.setText(petDetailData.getPetBreed());
                    petage.setText(petDetailData.getPetAge());
                    petID.setText(petDetailData.getPetUniqueId());
                    gender.setText(petDetailData.getPetSex());


                    petParentname.setText(petDetailData.getPetParentName());
                    ph_number.setText(petDetailData.getContactNumber());
                    address.setText(petDetailData.getUser().getAddress());


                    /*
                     //  there's no Img url

                    String imgUrl = userObj.getProfileImageUrl();
                    Log.d("okok", "img Url: " + imgUrl);

        Glide.with(getActivity())
                .load(imgUrl) // image url
                .placeholder(R.drawable.dogimggg)
                .centerCrop()
                .into(dogimg);  // imageview object
*/


                } else {
                    Log.d("okok", "Response failed");
                }

            }

            @Override
            public void onFailure(Call<PetDetail> call, Throwable t) {

                Log.e("okok", "onFailure:" + t.getLocalizedMessage());
            }
        });



        // Inflate the layout for this fragment
        return view;
    }
}