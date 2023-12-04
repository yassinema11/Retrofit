package dev.mobile.retrofit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    List<User> listeUser;
    Context context;

    public UserAdapter(Context context,List<User> listeUser) {
        this.listeUser = listeUser;
        this.context = context;
    }


    @NonNull
    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater. from (parent.getContext()).inflate(R.layout.recyclerviewitem, parent, false);
        UserViewHolder userViewHolder = new UserViewHolder(view);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserViewHolder holder, @SuppressLint("RecyclerView") int position) {

        User user = listeUser.get (position);
        holder.id.setText (String.valueOf(user.getId()));
        holder.name.setText (user.getName());
        holder.username.setText (user.getUsername());
        holder.password.setText (user.getPassword());
        holder.email.setText (user.getEmail());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, listeUser.get(position).getName()+" "+listeUser.get(position).getUsername(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.listeUser.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView id, name, username, password, email;
        public UserViewHolder (View itemView) {
            super (itemView);
            id = itemView.findViewById(R.id.id);
            name =itemView.findViewById(R.id.txtvnom);
            username = itemView.findViewById(R.id.txtvusername);
            password= itemView.findViewById(R.id.password);
            email = itemView.findViewById(R.id.email);
        }
    }
}