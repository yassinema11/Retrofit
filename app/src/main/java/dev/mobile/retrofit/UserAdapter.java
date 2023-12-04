package dev.mobile.retrofit;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>
{

    private List<User> userList;

    public UserAdapter(List<User> userList)
    {
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleviewitem, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position)
    {
        User user = userList.get(position);
        holder.bind(user);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder
    {

        private TextView nameTextView,usernameTextView,emailTextView,passTextView;


        public UserViewHolder(@NonNull View itemView)
        {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.txtnom);
            usernameTextView = itemView.findViewById(R.id.txtuser);
            emailTextView = itemView.findViewById(R.id.txtemail);
            passTextView = itemView.findViewById(R.id.txtpass);

        }

        public void bind(User user)
        {
            nameTextView.setText(user.getName());
            usernameTextView.setText(user.getUsername());
            emailTextView.setText(user.getEmail());
            passTextView.setText(user.getPassword());

        }
    }
}
