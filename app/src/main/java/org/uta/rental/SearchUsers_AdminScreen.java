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

import org.uta.rental.profile.ViewProfile;
import org.uta.rental.user.AdapterAdminSearchUsers;
import org.uta.rental.user.AdminSearchUserController;

import java.util.List;

public class SearchUsers_AdminScreen extends AppCompatActivity {
    private AdminSearchUserController adminSearchUserController;
    private EditText lastNameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_search_user_screen);

        Button searchBtn = (Button) findViewById(R.id.searchBtnUserSearch);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });

        RecyclerView rView = (RecyclerView) findViewById(R.id.rv_searchUsers);
        rView.setLayoutManager(new LinearLayoutManager(this));
        rView.setNestedScrollingEnabled(true);

        Button logoutBtn = (Button)findViewById(R.id.userLogoutBtn);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchUsers_AdminScreen.this,
                        ApplicationMainScreen.class));
            }
        });

        ImageButton backBtn = (ImageButton) findViewById(R.id.vrUserBackButton3);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchUsers_AdminScreen.this,
                        AdminMainScreen.class));
            }
        });
    }

        private void search(){
            lastNameText = (EditText) findViewById(R.id.lastNameTxtView);
            String lastName = lastNameText.getText().toString();
            adminSearchUserController = new AdminSearchUserController(this.getApplicationContext(), lastName);
            final Context context = this.getApplicationContext();

            List<ViewProfile> users = adminSearchUserController.displayUsers();
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_searchUsers);
            AdapterAdminSearchUsers adaptersearchUsers = new AdapterAdminSearchUsers(recyclerView, users, adminSearchUserController, context);
            recyclerView.setAdapter(adaptersearchUsers);
        }


}
