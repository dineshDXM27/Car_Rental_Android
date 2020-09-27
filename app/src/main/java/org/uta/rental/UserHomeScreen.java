package org.uta.rental;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserHomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_screen);

        Button viewProfileBtn = (Button)findViewById(R.id.viewProfileBtn);
        viewProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Button viewReservationsBtn = (Button)findViewById(R.id.viewReservationsBtn);
        viewReservationsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        
        Button requestCarBtn = (Button)findViewById(R.id.requestCarBtn);
        requestCarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void logoutFunc(View view) {
        Intent intent = new Intent(this,ApplicationMainScreen.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(),"You have successfully Logged Out",Toast.LENGTH_SHORT).show();
    }
}