package com.example.d22_login_p;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.d22_login_p.adapter.myadapter;
import com.example.d22_login_p.api_interface.ApiClient;
import com.example.d22_login_p.api_interface.UserService;
import com.example.d22_login_p.model.RecReqestParams;
import com.example.d22_login_p.model.RecRequest;
import com.example.d22_login_p.model.RecResponse;
import com.example.d22_login_p.model.pet.PetListData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;

    private UserService apiInterface2;

    //TODO:  take via Login call<>
//    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6IjVkMGJkNmQ0LTIzNjQtNGU1Ny04Yzk1LTA3MzZlYTgwMDIyMSIsIm5iZiI6MTYzODI2NjkyOCwiZXhwIjoxNjY5ODAyOTI4LCJpYXQiOjE2MzgyNjY5Mjh9.4tRNX0vlPDEpR0kLG43JbtJ10-AZmONtpIIoXMk0Ksg";


    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        Log.d("okok", "under ONcreate");

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerView_response);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));


        getApidata();


        return view;
    }

    private void getApidata() {
        Log.d("okok", "under getApidata");

        apiInterface2 = ApiClient.getClient(getActivity()).create(UserService.class);

        RecReqestParams recReqestParams = new RecReqestParams(1, 20);       // setting the static fields , to be send in Call<> in get_petData()
        RecRequest recRequest = new RecRequest(recReqestParams);
        Log.d("okok", "created obj, recRequest");
        //        getActivity().getShared...   Vs      getContext.getShared...
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("token", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "this is string token");
        Log.d("okok", "HomeFragment token: " + token);

        Call<RecResponse> recResponseCall = apiInterface2.get_petData(recRequest, token);
        Log.d("okok", "1");

        recResponseCall.enqueue(new Callback<RecResponse>() {
            @Override
            public void onResponse(Call<RecResponse> call, Response<RecResponse> response) {
                Log.d("okok", "2");

                if (response.isSuccessful()) {
                    Log.d("okok", "response successful");
                    Log.d("okok", "Response " + response.body().toString());
                    Log.d("okok", "petname " + response.body().getData().getPetlist().get(0).getPetName());
                    Log.d("okok", "id " + response.body().getData().getPetlist().get(1).getId());
                    Log.d("okok", "one " + response.body().getData().getPetlist().get(1).getPetName());

//                    int size = response.body().getData().getPetlist().size();

                    ArrayList<PetListData> arrayList = response.body().getData().getPetlist();

                    recyclerView.setAdapter(new myadapter(arrayList));

                } else {
                    Log.d("okok", "response unsucessful");
                }
            }

            @Override
            public void onFailure(Call<RecResponse> call, Throwable t) {

            }
        });


        Log.d("okok", "3");


    }
}