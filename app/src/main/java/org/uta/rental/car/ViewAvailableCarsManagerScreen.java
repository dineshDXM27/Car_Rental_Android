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
import org.uta.rental.RentalManagerScreen;
import org.uta.rental.reservation.AdapterManagerReservation;
import org.uta.rental.reservation.TotalCostUtility;

public class ViewAvailableCarsManagerScreen extends AppCompatActivity {

    Button pickDate, pickTime;
    TextView date_available, time_available;
    private ViewAvailableCarsControllerManager controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_availablecars_manager);

        controller = new ViewAvailableCarsControllerManager(ViewAvailableCarsManagerScreen.this);

        pickDate = findViewById(R.id.pickDate);
        pickTime = findViewById(R.id.pickTime);
        date_available = findViewById(R.id.date_available);
        time_available = findViewById(R.id.time_available);

        pickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleDateButton();
            }
        });
        pickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleTimeButton();
            }
        });


        Button logOut_manager= (Button)findViewById(R.id.logout_manager_available);
        logOut_manager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewAvailableCarsManagerScreen.this, ApplicationMainScreen.class));
            }
        });

        ImageButton backButton = (ImageButton) findViewById(R.id.backButton_manager);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewAvailableCarsManagerScreen.this,
                        RentalManagerScreen.class));
            }
        });


        Button searchButton = findViewById(R.id.seacrhCar_availableManager);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                try {
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy h:mm a");
                    String date = date_available.getText().toString();
                    String time = time_available.getText().toString();
                    LocalDateTime selectedTime = LocalDateTime.parse(date + " " + time, dateTimeFormatter);

                    List<CarsInformation> carsInformations = controller.search(selectedTime);

                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.view_availableCars_manager);

                    AdapterAvailableCars adapterAvailableCars = new AdapterAvailableCars(ViewAvailableCarsManagerScreen.this,
                            recyclerView, carsInformations, controller);
                    recyclerView.setAdapter(adapterAvailableCars);


                } catch (IllegalArgumentException e) {
                    Toast.makeText(ViewAvailableCarsManagerScreen.this, "Invalid date and time.", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.view_availableCars_manager);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    private void handleDateButton() {
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

                date_available.setText(dateText);
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
                time_available.setText(dateText);
            }
        }, HOUR, MINUTE, is24HourFormat);

        timePickerDialog.show();

    }

}

