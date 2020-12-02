package org.uta.rental.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.uta.rental.ApplicationMainScreen;
import org.uta.rental.DBManager;
import org.uta.rental.R;
import org.uta.rental.Session;
import org.uta.rental.StringConstants;
import org.uta.rental.UserHomeScreen;
import org.uta.rental.car.CarsInformation;
import org.uta.rental.car.RequestCarUserScreen;
import org.uta.rental.reservation.TotalCostUtility;
import org.uta.rental.reservation.UserReservationDetails;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RequestedCarDetailsUserScreen extends AppCompatActivity {

    private RequestedCarDetailsUserController controller;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_requested_car);
        controller = new RequestedCarDetailsUserController(RequestedCarDetailsUserScreen.this);

        Button logOut_requestedCar_user = (Button) findViewById(R.id.logoutBtn);
        logOut_requestedCar_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RequestedCarDetailsUserScreen.this, ApplicationMainScreen.class));
            }
        });

        ImageButton backButton = (ImageButton) findViewById(R.id.vrUserBackButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RequestedCarDetailsUserScreen.this,
                        UserHomeScreen.class));
            }
        });


        final CarsInformation carsInformation = (CarsInformation) getIntent().getSerializableExtra("CarsInformation");
        final LocalDateTime startDateTime = (LocalDateTime) getIntent().getSerializableExtra("StartTime");
        final LocalDateTime endDateTime = (LocalDateTime) getIntent().getSerializableExtra("EndTime");

        Button saveButton = findViewById(R.id.saveReservation);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.reserve(carsInformation, startDateTime, endDateTime);
                Intent intent = new Intent(RequestedCarDetailsUserScreen.this, RequestCarUserScreen.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                Toast.makeText(RequestedCarDetailsUserScreen.this, "Reservation created.", Toast.LENGTH_SHORT).show();
            }
        });

        TextView details = findViewById(R.id.requestedCarDetails);
        details.setText(carInformationToString(carsInformation, startDateTime, endDateTime));
    }

    private String carInformationToString(CarsInformation carsInformation, LocalDateTime start, LocalDateTime end) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        DBManager dbManager = DBManager.getInstance(RequestedCarDetailsUserScreen.this);
        Session session = new Session(RequestedCarDetailsUserScreen.this);
        String userName = session.getValue(StringConstants.USERNAME);
        RegisterUser registerUser = dbManager.findUserByUsername(userName).get();
        TotalCostUtility.CarType carType = TotalCostUtility.CarType.valueOf(carsInformation.getCarName().replaceAll(" ", "_").toUpperCase());

        double cost;
        TotalCostUtility.Extras extras = new TotalCostUtility.Extras(false, false, false);
        if (registerUser.getAacMemberId() != null && !registerUser.getAacMemberId().equals("")) {
           cost = TotalCostUtility.calculateBaseCostWithDiscount(start, end, carType, extras);
        } else {
            cost = TotalCostUtility.calculateBaseCostWithoutDiscount(start, end, carType, extras);
        }

        return String.format("Car Number: %d\nCar Name: %s\nCapacity: %d\nNumber of Riders: %d\n" +
                "GPS: No\nOnStar: No\nSiriusXM: No\nStart Date: %s\nStart Time: %s\nEnd Date: %s\nEnd Time: %s\nTotal Cost: $%.2f\n" +
                "AA Member Status: %s", carsInformation.getCarNumber(), carsInformation.getCarName(),
                carsInformation.getCapacity(), carsInformation.getCapacity(), dateFormatter.format(start),
                timeFormatter.format(start), dateFormatter.format(end), timeFormatter.format(end),
                cost, registerUser.getAacMemberId());
    }
}