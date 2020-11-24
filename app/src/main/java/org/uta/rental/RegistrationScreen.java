package org.uta.rental;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);

        Button regButton = (Button) findViewById(R.id.regButton);
        regButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationScreen.this, ApplicationMainScreen.class));
                Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
            }
        }));
    }
}