package org.uta.rental.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import org.uta.rental.ApplicationMainScreen;
import org.uta.rental.R;
import org.uta.rental.UserHomeScreen;
import org.uta.rental.car.RequestCarUserScreen;

public class RequestedCarDetailsUser extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_requested_car);

        Button logOut_requestedCar_user = (Button) findViewById(R.id.logoutBtn);
        logOut_requestedCar_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RequestedCarDetailsUser.this, ApplicationMainScreen.class));
            }
        });

        ImageButton backButton = (ImageButton) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RequestedCarDetailsUser.this,
                        UserHomeScreen.class));
            }
        });
    }
}