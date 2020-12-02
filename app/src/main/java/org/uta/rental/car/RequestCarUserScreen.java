package org.uta.rental.car;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.uta.rental.ApplicationMainScreen;
import org.uta.rental.R;
import org.uta.rental.RequestCarUser;
import org.uta.rental.UserHomeScreen;
import org.uta.rental.reservation.TotalCostUtility;
import org.uta.rental.user.AdapterCarInformationUser;
import org.uta.rental.user.RequestCarUserController;

public class RequestCarUserScreen extends AppCompatActivity {
    Button startDate, endDate, startTime, endTime;
    TextView textStartDate, textEndDate, textStartTime, textEndTime;
    private RequestCarUserController controller;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_car_user);

        startDate = findViewById(R.id.startDate_user);
        endDate = findViewById(R.id.finalDate_user);
        startTime =  findViewById(R.id.startTime_user);
        endTime =  findViewById(R.id.finalTime_user);
        textStartDate = findViewById(R.id.textStartDate);
        textEndDate = findViewById(R.id.TextFinalDate);
        textStartTime =  findViewById(R.id.TextstartTime);
        textEndTime =  findViewById(R.id.TextFinalTime);

        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleDateButton();
            }
        });
        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleDateButton1();
            }
        });

        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleTimeButton();
            }
        });

        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleTimeButton1();
            }
        });


        Button logOut_requestedCar_user = (Button)findViewById(R.id.logOut_requestedCar_user);
        logOut_requestedCar_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RequestCarUserScreen.this, ApplicationMainScreen.class));
            }
        });

        ImageButton backButton = (ImageButton) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RequestCarUserScreen.this,
                        UserHomeScreen.class));
            }
        });


        Button searchButton = findViewById(R.id.requestCar_user);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                try {
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy h:mm a");
                    String date = textStartDate.getText().toString();
                    String time = textStartTime.getText().toString();
                    LocalDateTime dateTime = LocalDateTime.parse(date + " " + time, dateTimeFormatter);
                    List<CarsInformation> carsInformations = controller.request(dateTime);

                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.viewCarRequest_User);
                   AdapterCarInformationUser adapterCarInformation = new AdapterCarInformationUser(RequestCarUserScreen.this,
                            recyclerView, carsInformations, controller);
                    recyclerView.setAdapter(adapterCarInformation);
                } catch (IllegalArgumentException e) {
                    Toast.makeText(RequestCarUserScreen.this, "Invalid input", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.viewCarRequest_User);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }



    private void handleDateButton(){
        Calendar calendar = Calendar.getInstance();
        int YEAR = calendar.get(Calendar.YEAR);
        int MONTH = calendar.get(Calendar.MONTH);
        int DATE = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {

                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.YEAR, year);
                calendar1.set(Calendar.MONTH, month);
                calendar1.set(Calendar.DATE, date);
                String dateText = DateFormat.format("EEEE, MMM d, yyyy", calendar1).toString();

                textStartDate.setText(dateText);
            }
        }, YEAR, MONTH, DATE);

        datePickerDialog.show();
    }


    private void handleDateButton1() {
        Calendar calendar = Calendar.getInstance();
        int YEAR = calendar.get(Calendar.YEAR);
        int MONTH = calendar.get(Calendar.MONTH);
        int DATE = calendar.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {

                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.YEAR, year);
                calendar1.set(Calendar.MONTH, month);
                calendar1.set(Calendar.DATE, date);
                String dateText = DateFormat.format("EEEE, MMM d, yyyy", calendar1).toString();

                   textEndDate.setText(dateText);
            }
        }, YEAR, MONTH, DATE);

        datePickerDialog.show();
    }

    private void handleTimeButton() {
        Calendar calendar = Calendar.getInstance();
        int HOUR = calendar.get(Calendar.HOUR);
        int MINUTE = calendar.get(Calendar.MINUTE);
        boolean is24HourFormat = DateFormat.is24HourFormat(this);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.HOUR, hour);
                calendar1.set(Calendar.MINUTE, minute);
                String dateText = DateFormat.format("h:mm a", calendar1).toString();
                textStartTime.setText(dateText);
            }
        }, HOUR, MINUTE, is24HourFormat);

        timePickerDialog.show();

    }

    private void handleTimeButton1() {
        Calendar calendar = Calendar.getInstance();
        int HOUR = calendar.get(Calendar.HOUR);
        int MINUTE = calendar.get(Calendar.MINUTE);
        boolean is24HourFormat = DateFormat.is24HourFormat(this);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                Calendar calendar1 = Calendar.getInstance();
                calendar1.set(Calendar.HOUR, hour);
                calendar1.set(Calendar.MINUTE, minute);
                String dateText = DateFormat.format("h:mm a", calendar1).toString();
                textEndTime.setText(dateText);
            }
        }, HOUR, MINUTE, is24HourFormat);

        timePickerDialog.show();

    }

}


