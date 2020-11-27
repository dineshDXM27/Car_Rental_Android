package org.uta.rental;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminMainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main_screen);

        Button searchUsersBtn = (Button) findViewById(R.id.searchUsers);
        searchUsersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminMainScreen.this, SearchUsers_AdminScreen.class));
            }
        });

        Button viewMyProfileBtn = (Button) findViewById(R.id.viewprofileBtn_admin);
        viewMyProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        Button logoutBtn = (Button) findViewById(R.id.logoutBtn_admin);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(AdminMainScreen.this, ApplicationMainScreen.class));
            }
        });
    }
}