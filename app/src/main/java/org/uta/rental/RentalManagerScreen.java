package org.uta.rental;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;




public class RentalManagerScreen extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rentalmanager_homescreen);

        Button view_reservations = (Button)findViewById(R.id.view_reservations);
        view_reservations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });

        Button search_cars = (Button)findViewById(R.id.search_cars);
        search_cars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ///
            }
        });

        Button view_available_cars = (Button)findViewById(R.id.view_available_cars);
        view_available_cars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });

        Button logout_rm = (Button)findViewById(R.id.logout_rm);
        logout_rm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RentalManagerScreen.this, ApplicationMainScreen.class));
            }
        });
    }
}