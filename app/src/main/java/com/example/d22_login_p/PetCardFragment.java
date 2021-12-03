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
import android.widget.TextView;

import com.example.d22_login_p.api_interface.ApiClient;
import com.example.d22_login_p.api_interface.UserService;
import com.example.d22_login_p.model.PetDetail;
import com.example.d22_login_p.model.PetDetailData;
import com.example.d22_login_p.model.SetPetid;
import com.example.d22_login_p.model.SetPetidpetData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PetCardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PetCardFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private UserService apiInterface3;
    private FragmentActivity fragmentActivity;

    private TextView petname, petParentname, petBreedname;

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
    // TODO: Rename and change types and number of parameters
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

        petname = view.findViewById(R.id.petcard_petName);
        petParentname = view.findViewById(R.id.petcard_petParentname);
        petBreedname = view.findViewById(R.id.petcard_breed);


        Bundle bundle = this.getArguments();
        int myInt = 0;
        if (bundle != null) {
             myInt = bundle.getInt("id", 9269);
        }


        apiInterface3 = ApiClient.getClient(getActivity()).create(UserService.class);

        SetPetidpetData id = new SetPetidpetData();     // TODO:  make fetch the id,,, from homefragment.java
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

                    PetDetailData petDetailData = response.body().getData();

                    petname.setText(petDetailData.getPetName());
                    petParentname.setText(petDetailData.getPetParentName());
                    petBreedname.setText(petDetailData.getPetBreed());


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