package org.uta.rental.carsInformation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import org.uta.rental.ApplicationMainScreen;
import org.uta.rental.R;
import org.uta.rental.RentalManagerScreen;


public class SearchCars_Manager_Screen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchcars_managerscreen);

        Button logOut = (Button)findViewById(R.id.logout_searchcarsManager);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchCars_Manager_Screen.this, ApplicationMainScreen.class));
            }
        });

        ImageButton backButton = (ImageButton) findViewById(R.id.backButton_managerScreen);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchCars_Manager_Screen.this,
                        RentalManagerScreen.class));
            }
        });




    }
}
