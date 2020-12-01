package org.uta.rental.car;


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
import org.uta.rental.reservation.RentalManagerReservationDetails;
import org.uta.rental.reservation.TotalCostUtility;
import org.uta.rental.reservation.UserReservationDetails;
import org.uta.rental.reservation.ViewReservationDetailsManagerController;
import org.uta.rental.reservation.ViewReservationsManagerScreen;

import java.time.format.DateTimeFormatter;

public class ViewCarDetailsManagerScreen extends AppCompatActivity {


    @RequiresApi(api = Build.VERSION_CODES.O)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details_manager_screen);

        final CarsInformation carsInformation = (CarsInformation) getIntent().getSerializableExtra("CarsInformation");

        TextView textView = findViewById(R.id.carDetailText);
        textView.setText(carsInformationToString(carsInformation));

        Button logoutBtn = (Button) findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewCarDetailsManagerScreen.this,
                        ApplicationMainScreen.class));
            }
        });

        ImageButton backBtn = (ImageButton) findViewById(R.id.backButton);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewCarDetailsManagerScreen.this,
                        SearchCarsManagerScreen.class));
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String carsInformationToString(CarsInformation carsInformation) {
        long carNumber = carsInformation.getCarNumber();
        String carName = carsInformation.getCarName();
        TotalCostUtility.CarType carType = TotalCostUtility.CarType.valueOf(carName.replaceAll(" ", "_").toUpperCase());
        int capacity = carsInformation.getCapacity();
        double weekdayRate = TotalCostUtility.getWeekdayRate(carType);
        double weekendRate = TotalCostUtility.getWeekendRate(carType);
        double weekRate = TotalCostUtility.getWeekRate(carType);
        double gpsRate = TotalCostUtility.getGpsDayRate(carType);
        double onStarRate = TotalCostUtility.getOnStarDayRate(carType);
        double xmRate = TotalCostUtility.getSiriusXmDayRate(carType);
        CarStatus carStatus = carsInformation.getCarStatus();

        return String.format("Car Number: %d\nCar Name: %s\nCapacity: %d\nWeekday Rate: $%.2f,\nWeekend Rate: $%.2f\n" +
                        "Week Rate: $%.2f\nGps Daily Rate: $%.2f\nOnStar Daily Rate: $%.2f\nSiriusXM Daily Rate: $%.2f\n" +
                        "Car Status: %s",
                carNumber, carName, capacity, weekdayRate, weekendRate, weekRate, gpsRate, onStarRate, xmRate, carStatus.getStatus());
    }
}