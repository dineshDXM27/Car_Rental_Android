package org.uta.rental.user;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.uta.rental.R;
import org.uta.rental.ViewProfile;

import java.util.List;


public class AdapterAdminSearchUsers extends RecyclerView.Adapter<AdapterAdminSearchUsers.ViewHolder> {
    private List<ViewProfile> users;
    private Context context;
    private RecyclerView recyclerView;
    private AdminSearchUserDetailsController adminSearchUserDetailsController;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.userList);
        }

        public TextView getTextView() {
            return textView;
        }
    }

    public AdapterAdminSearchUsers(RecyclerView recyclerView, List<ViewProfile> users, AdminSearchUserController adminSearchUserDetailsController, Context context){
        this.recyclerView = recyclerView;
        this.users = users;
        this.recyclerView = recyclerView;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.userlist_adapter_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.getTextView().setText(parseUserDetails(users.get(position)));
        holder.textView.setTextSize(22);
        holder.getTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recyclerView.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
                    AdminSearchUserDetailsController.setUsers(users.get(position));
                    Intent intent = new Intent(context, ViewUserDetailsAdminScreen.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    @SuppressLint("DefaultLocale")
    private String parseUserDetails(ViewProfile user){
        String lastName = user.getLastName();
        String firstName = user.getFirstName();

        String displayText = "LastName: %s\nFirstName: %s\n";

        return String.format(displayText, lastName, firstName);
    }

}
