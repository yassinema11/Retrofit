package dev.mobile.retrofit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class ListerFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerViewUser;
    private RecyclerView.LayoutManager layoutManager;

    public ListerFragment() {
        // Required empty public constructor
    }

    public static ListerFragment newInstance(String param1, String param2) {
        ListerFragment fragment = new ListerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lister, container, false);

        recyclerViewUser = v.findViewById(R.id.user_recycleview);
        layoutManager = new LinearLayoutManager(getActivity());

        // Retrieve URL from arguments
        String URL = getArguments().getString("url", "");

        // Create Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create API service
        ApiHandler api = retrofit.create(ApiHandler.class);

        // Make a network request to get the list of users
        Call<List<User>> userListCall = api.getAllUsers();
        userListCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Response<List<User>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    List<User> listUser = response.body();

                    recyclerViewUser.setLayoutManager(layoutManager);

                    // Set up RecyclerView
                    UserAdapter userAdapter = new UserAdapter(getActivity(), listUser);
                    recyclerViewUser.setAdapter(userAdapter);

                    // Add a divider between each row of the list
                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
                    recyclerViewUser.addItemDecoration(dividerItemDecoration);
            }}

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(getActivity(), "Error: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }


        });

        return v;
    }
}
