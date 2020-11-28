package org.uta.rental.reservation;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.uta.rental.ApplicationMainScreen;
import org.uta.rental.R;
import org.uta.rental.UserHomeScreen;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiresApi(api = Build.VERSION_CODES.O)
public class UpdateReservationDetailsUserScreen extends AppCompatActivity {

    private UpdateUserReservationController controller;

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_details_edit_user_screen);
        final UserReservationDetails userReservationDetails = (UserReservationDetails) getIntent().getSerializableExtra("UserReservationDetails");
        controller = new UpdateUserReservationController(UpdateReservationDetailsUserScreen.this);

        EditText gpsText = (EditText) findViewById(R.id.gps);
        gpsText.setText(userReservationDetails.isGps() ? "Yes" : "No");
        EditText onStartText = (EditText) findViewById(R.id.onStar);
        onStartText.setText(userReservationDetails.isOnStar() ? "Yes" : "No");
        EditText xmText = (EditText) findViewById(R.id.siriusXm);
        xmText.setText(userReservationDetails.isSiriusXm() ? "Yes" : "No");
        EditText startTimeText = (EditText) findViewById(R.id.startTime);
        startTimeText.setText(timeFormatter.format(userReservationDetails.getStartDateTime()));
        EditText startDateText = (EditText) findViewById(R.id.startDate);
        startDateText.setText(dateFormatter.format(userReservationDetails.getStartDateTime()));
        EditText endTimeText = (EditText) findViewById(R.id.endTime);
        endTimeText.setText(timeFormatter.format(userReservationDetails.getEndDateTime()));
        EditText endDateText = (EditText) findViewById(R.id.endDate);
        endDateText.setText(dateFormatter.format(userReservationDetails.getEndDateTime()));

        Button logoutBtn = (Button)findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpdateReservationDetailsUserScreen.this,
                        ApplicationMainScreen.class));
            }
        });

        ImageButton backBtn = (ImageButton) findViewById(R.id.vrUserBackButton);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UpdateReservationDetailsUserScreen.this,
                        UserHomeScreen.class));
            }
        });


        AlertDialog.Builder builder = new AlertDialog.Builder(this.getWindow().getContext());
        builder.setTitle("Update Reservation");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                UserReservationDetails reservation = new UserReservationDetails();
                reservation.setReservationNumber(userReservationDetails.getReservationNumber());
                reservation.setCarName(userReservationDetails.getCarName());
                reservation.setCarNumber(userReservationDetails.getCarNumber());
                reservation.setCapacity(userReservationDetails.getCapacity());

                EditText gpsText = (EditText) findViewById(R.id.gps);
                EditText onStartText = (EditText) findViewById(R.id.onStar);
                EditText xmText = (EditText) findViewById(R.id.siriusXm);
                EditText startTimeText = (EditText) findViewById(R.id.startTime);
                EditText startDateText = (EditText) findViewById(R.id.startDate);
                EditText endTimeText = (EditText) findViewById(R.id.endTime);
                EditText endDateText = (EditText) findViewById(R.id.endDate);

                reservation.setStartDateTime(LocalDateTime.parse(startDateText.getText() + " "
                        + startTimeText.getText(), dateTimeFormatter));
                reservation.setEndDateTime(LocalDateTime.parse(endDateText.getText() + " "
                        + endTimeText.getText(), dateTimeFormatter));
                String gpsString = gpsText.getText().toString();
                String gpsString2 = String.format("%s", gpsText.getText());
                reservation.setGps(gpsText.getText().toString().equals("Yes"));
                reservation.setOnStar(onStartText.getText().toString().equals("Yes"));
                reservation.setSiriusXm(xmText.getText().toString().equals("Yes"));
                controller.updateReservation(reservation);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        final AlertDialog dialog = builder.create();

        Button saveButton = findViewById(R.id.updateButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        Button cancelButton = findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               controller.cancelUpdate(userReservationDetails);
            }
        });
    }
}