package org.uta.rental.reservation;


import android.os.Build;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.uta.rental.ApplicationMainScreen;
import org.uta.rental.R;

import java.time.format.DateTimeFormatter;

public class UpdateReservationDetailsUserScreen extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_details_edit_user_screen);
        UserReservationDetails userReservationDetails = (UserReservationDetails) getIntent().getSerializableExtra("UserReservationDetails");

        EditText editText = (EditText) findViewById(R.id.reservationNumber);
        editText.setText(String.format("%d", userReservationDetails.getReservationNumber()));

    }
}