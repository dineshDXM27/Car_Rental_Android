package org.uta.rental;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
}