package com.example.petofyReplica;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.petofyReplica.adapter.myadapter;
import com.example.petofyReplica.api_interface.OnButtonListener;

import com.example.petofyReplica.common_functionality.Internet_permission;
import com.example.petofyReplica.model.Recycler.RecDataPetlist;
import com.example.petofyReplica.model.Recycler.RecReqestParams;
import com.example.petofyReplica.model.Recycler.RecRequest;
import com.example.petofyReplica.model.Recycler.RecResponse;
import com.example.petofyReplica.retrofit.ApiClient;
import com.example.petofyReplica.retrofit.UserService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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

    private ArrayList<RecDataPetlist> arrayList;
    private myadapter adapter;

    private ProgressBar progressBar;
    private Context context;

    private SearchView searchView;



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerView_response);
        progressBar = view.findViewById(R.id.progressBar2);

        searchView = view.findViewById(R.id.searchView);

        SearchViewFilter();

        getApidata();


        return view;
    }

    private void SearchViewFilter() {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                ArrayList<RecDataPetlist> filtered_list = new ArrayList<>();

                for (RecDataPetlist item : arrayList) {

                    if (item.getPetName().toLowerCase().contains(newText.toLowerCase())) {
                        // if item exist we add to our filtered list
                        filtered_list.add(item);
                    }
                }
                if (filtered_list.isEmpty()) {
                    Toast.makeText(getActivity(), "No Data Found..", Toast.LENGTH_SHORT).show();
                } else {
                    adapter.filterList(filtered_list);          // todo:  understand,,
                }

                return false;
            }
        });

    }


    private void getApidata() {

        apiInterface2 = ApiClient.getClient(getActivity()).create(UserService.class);


        RecReqestParams recReqestParams = new RecReqestParams(1, 20);       // setting the static fields , to be send in Call<> in get_petData()
        RecRequest recRequest = new RecRequest(recReqestParams);


        //        getActivity().getShared...   Vs      getContext.getShared...
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("token", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "this is string token");
        Log.d("okok", "HomeFragment token: " + token);

        Call<RecResponse> recResponseCall = apiInterface2.get_petData(recRequest, token);

        recResponseCall.enqueue(new Callback<RecResponse>() {
            @Override
            public void onResponse(Call<RecResponse> call, Response<RecResponse> response) {


                if (response.isSuccessful()) {
                    Log.d("okok", " Response successful Responsecode: " + response.body().toString());
//                    int size = response.body().getData().getPetlist().size();

                    arrayList = response.body().getData().getPetlist();
                    updateAdapter(arrayList);

                } else {
                    Log.d("okok", "response unsucessful");
                }

                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<RecResponse> call, Throwable t) {
                Log.d("okok", "onFAILURE activated");
                progressBar.setVisibility(View.GONE);

            }
        });


    }


    private void updateAdapter(ArrayList<RecDataPetlist> arrayList) {

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        adapter = new myadapter(arrayList, this, context);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onButtonclick(int position) {   // THIS WILL  get triggered by --> viweHolder's onClick

        Log.d("okok", "Button clicked  PET_id: " + position);


        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        Fragment petCardFragment = new PetCardFragment();        // on which setBundle  and  replace layout with
        petCardFragment.setArguments(bundle);


        if (Internet_permission.isOnline(context)) {
            moveToFragment(petCardFragment);
        }


    }


    private void moveToFragment(Fragment petCardFragment) {             //TODO: make seperate class,  static method:  therefore pass> context , R.id.  , Fragemnt obj
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainContainer, petCardFragment);
        fragmentTransaction.addToBackStack(null);

//        fragmentTransaction.add(R.id.mainContainer, petCardFragment);
//        fragmentTransaction.addToBackStack("back");

        fragmentTransaction.commit();
    }

}