package org.uta.rental.reservation;


import android.app.AlertDialog;
import android.content.DialogInterface;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.uta.rental.ApplicationMainScreen;
import org.uta.rental.R;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ViewReservationDetailsUserScreen extends AppCompatActivity {

    private ViewReservationDetailsUserController controller;

    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_details_user_screen);

        controller = new ViewReservationDetailsUserController(this.getApplicationContext());

        UserReservationDetails userReservationDetails = controller.viewReservationDetails();
        TextView textView = (TextView) findViewById(R.id.reservationDetailText);
        textView.setText(reservationDetailsToString(userReservationDetails));
        Button logoutBtn = (Button)findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewReservationDetailsUserScreen.this,
                        ApplicationMainScreen.class));
            }
        });

        ImageButton backBtn = (ImageButton) findViewById(R.id.vrUserBackButton);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewReservationDetailsUserScreen.this,
                        ViewReservationsUserScreen.class));
            }
        });


        AlertDialog.Builder builder = new AlertDialog.Builder(this.getWindow().getContext());
        builder.setTitle("Cancel Reservation");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                controller.cancelReservation();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        final AlertDialog dialog = builder.create();
        // Display the alert dialog on interface

        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String reservationDetailsToString(UserReservationDetails reservation) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String startTime = timeFormatter.format(reservation.getStartDateTime());
        String startDate = dateFormatter.format(reservation.getStartDateTime());
        String endTime = timeFormatter.format(reservation.getEndDateTime());

        String endDate = dateFormatter.format(reservation.getEndDateTime());

        String gps = reservation.isGps() ? "Yes" : "No";
        String onStar = reservation.isOnStar() ? "Yes" : "No";
        String siriusXm = reservation.isSiriusXm() ? "Yes" : "No";
        String guiString = "Reservation Number: %d\nCar Number: %d\nCar Name: %s\n" +
                "Capacity: %d\nGPS: %s\nOnStar: %s\nSiriusXM: %s\n" +
                "Start Date: %s\nStart Time: %s\nEnd Date: %s\nEnd Time: %s\n" +
                "Total Cost: %.2f\nAAMemberID: %s\n";
        return String.format(guiString, reservation.getReservationNumber(), reservation.getCarNumber(),
                reservation.getCarName(), reservation.getCapacity(), gps, onStar, siriusXm,
                startDate, startTime, endDate,
                endTime, reservation.getTotalCost(), reservation.getAaMemberId());
    }
}