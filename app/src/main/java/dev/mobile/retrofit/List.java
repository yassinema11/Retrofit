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

public class List extends Fragment
{
    RecyclerView recyclerViewUser;
    RecyclerView.LayoutManager layoutManager;

    public List() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_list, container, false);
        String URL = getArguments().getString("url", "");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiHandler api = retrofit.create(ApiHandler.class);
        Call<List<User>> userListCall = api.getAllUsers();

        userListCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Response<List<User>> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    List<User> userList = response.body();

                    if (userList != null) {
                        recyclerViewUser = v.findViewById(R.id.user_recycleview);
                        layoutManager = new LinearLayoutManager(requireContext());
                        recyclerViewUser.setLayoutManager(layoutManager);

                        UserAdapter userAdapter = new UserAdapter(requireContext(), userList);
                        recyclerViewUser.setAdapter(userAdapter);

                        // Separate each row with a divider
                        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL);
                        recyclerViewUser.addItemDecoration(dividerItemDecoration);
                    } else {
                        Toast.makeText(requireContext(), "Response body is null", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(requireContext(), "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(requireContext(), "Error: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
}
