package org.uta.rental.car;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.uta.rental.R;
import org.uta.rental.reservation.AdapterManagerReservation;
import org.uta.rental.reservation.TotalCostUtility;

public class SearchCarsManagerScreen extends AppCompatActivity {

    private Button buttonDate, buttonTime;
    private TextView textDate_manager, textTime_manager;

    private SearchCarManagerController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_cars_manager);

        controller = new SearchCarManagerController(SearchCarsManagerScreen.this);
        buttonDate = findViewById(R.id.buttonDate);
        buttonTime = findViewById(R.id.buttonTime);
        textDate_manager = findViewById(R.id.textDate_manager);
        textTime_manager = findViewById(R.id.textTime_manager);

        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleDateButton();
            }
        });
        buttonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleTimeButton();
            }
        });

        final EditText carName = findViewById(R.id.carName_manager);
        Button searchButton = findViewById(R.id.search_cars);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy h:mm a");
                String date = textDate_manager.getText().toString();
                String time = textTime_manager.getText().toString();
                TotalCostUtility.CarType carType = TotalCostUtility.CarType.valueOf(carName.getText().toString()
                        .replaceAll(" ", "_")
                        .toUpperCase());
                LocalDateTime dateTime = LocalDateTime.parse(date + " " + time, dateTimeFormatter);
                List<CarsInformation> carsInformations = controller.search(carType, dateTime);

                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.carDetails);
                AdapterCarInformation adapterCarInformation = new AdapterCarInformation(SearchCarsManagerScreen.this,
                        recyclerView, carsInformations, controller);
                recyclerView.setAdapter(adapterCarInformation);
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.carDetails);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(true);
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

                textDate_manager.setText(dateText);
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
                textTime_manager.setText(dateText);
            }
        }, HOUR, MINUTE, is24HourFormat);

        timePickerDialog.show();

    }

}

