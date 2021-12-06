package com.example.d22_login_p;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.d22_login_p.api_interface.ApiClient;
import com.example.d22_login_p.api_interface.OnButtonListener;
import com.example.d22_login_p.api_interface.UserService;
import com.example.d22_login_p.model.Recycler.RecReqestParams;
import com.example.d22_login_p.model.Recycler.RecRequest;
import com.example.d22_login_p.model.Recycler.RecResponse;
import com.example.d22_login_p.model.Recycler.RecDataPetlist;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements OnButtonListener {

    //  Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;

    private UserService apiInterface2;

    OnButtonListener thiscontext;



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        thiscontext = (OnButtonListener) context;
    }




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
    // Rename and change types and number of parameters
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

//        thiscontext = (OnButtonListener) container.getContext();        //TODO: 1

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
                    Log.d("okok", "id " + response.body().getData().getPetlist().get(0).getId());
                    Log.d("okok", "petname0 " + response.body().getData().getPetlist().get(0).getPetName());

                    Log.d("okok", "id " + response.body().getData().getPetlist().get(1).getId());
                    Log.d("okok", "petname1 " + response.body().getData().getPetlist().get(1).getPetName());

//                    int size = response.body().getData().getPetlist().size();

                    ArrayList<RecDataPetlist> arrayList= response.body().getData().getPetlist();


                    recyclerView.setAdapter(new myadapter(arrayList,thiscontext));        // TODO: if working fine
                    // why passing context >>  referring to the interface

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

    @Override
    public void onButtonclick(int position) {   // THIS WILL  get triggered by --> viweHolder's onClick
        Log.d("okok", "onButtonclick position: " + position);
    }

}