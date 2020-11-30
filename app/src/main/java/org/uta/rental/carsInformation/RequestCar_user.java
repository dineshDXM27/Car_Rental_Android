package org.uta.rental.carsInformation;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import org.uta.rental.ApplicationMainScreen;
import org.uta.rental.R;

import org.uta.rental.UserHomeScreen;


public class RequestCar_user extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requestcar_user);

        Button logOut_requestedCar_user = (Button)findViewById(R.id.logOut_requestedCar_user);
        logOut_requestedCar_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RequestCar_user.this, ApplicationMainScreen.class));
            }
        });

        ImageButton backButton = (ImageButton) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RequestCar_user.this,
                        UserHomeScreen.class));
            }
        });



    }







}

