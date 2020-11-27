package org.uta.rental;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.uta.rental.user.AdapterAdminSearchUsers;
import org.uta.rental.user.AdminSearchUserController;

import java.util.List;

public class SearchUsers_AdminScreen extends AppCompatActivity {
    private AdminSearchUserController adminSearchUserDetailsController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_search_user_screen);

        EditText lastName = (EditText) findViewById(R.id.lastNameTxtView);
        adminSearchUserDetailsController = new AdminSearchUserController(this.getApplicationContext(), lastName);
        Button searchBtn = (Button) findViewById(R.id.searchBtnUserSearch);
        final Context context = this.getApplicationContext();

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ViewProfile> users = adminSearchUserDetailsController.displayUsers();
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_searchUsers);
                AdapterAdminSearchUsers adaptersearchUsers = new AdapterAdminSearchUsers(recyclerView, users, adminSearchUserDetailsController, context);
                recyclerView.setAdapter(adaptersearchUsers);
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_searchUsers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(true);

        Button logoutBtn = (Button)findViewById(R.id.viewReservationslogoutBtn);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchUsers_AdminScreen.this,
                        ApplicationMainScreen.class));
            }
        });

        ImageButton backBtn = (ImageButton) findViewById(R.id.vrUserBackButton);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchUsers_AdminScreen.this,
                        AdminMainScreen.class));
            }
        });
    }

}
