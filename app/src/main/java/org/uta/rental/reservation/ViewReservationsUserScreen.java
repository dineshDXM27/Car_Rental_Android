package org.uta.rental.reservation;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import org.uta.rental.ApplicationMainScreen;
import org.uta.rental.LoginController;
import org.uta.rental.R;
import org.uta.rental.UserHomeScreen;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ViewReservationsUserScreen extends AppCompatActivity {

    private ViewReservationsUserController controller;

    // TODO delete this when using full controller logic. Placeholder for gui testing
    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<Reservation> generateReservations() {
        Random random = new Random();
        List<Reservation> reservations = new ArrayList<>();
        for (int i = 0; i < 9; ++i) {
            int carNumber = random.nextInt();
            String carName = "Sport";
            int capacity = random.nextInt(5);
            LocalDateTime start = LocalDateTime.now();
            LocalDateTime end = LocalDateTime.now();
            start = start.minusDays(random.nextInt(4));
            end = end.plusDays(1);
            double totalCost = random.nextDouble();

            Reservation reservation = new Reservation();
            reservation.setReservationNumber(random.nextLong());
            reservation.setCarNumber(carNumber);
            reservation.setCapacity(capacity);
            reservation.setCarName(carName);
            reservation.setStartDateTime(start);
            reservation.setEndDateTime(end);
            reservation.setTotalCost(totalCost);
            reservations.add(reservation);
            reservation.setOwningUsername(LoginController.getCurrentUser());
        }

        return reservations;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reservations_screen);

        EditText editTextDate = (EditText) findViewById(R.id.userRvDateInput);
        EditText editTextTime = (EditText) findViewById(R.id.userRvTimeInput);
        controller = new ViewReservationsUserController(this.getApplicationContext(), editTextDate,
                editTextTime);
        Button searchButton = (Button) findViewById(R.id.searchRvUserBtn);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Reservation> reservations = controller.viewReservations();
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvReservations);
                AdapterReservation adapterReservation = new AdapterReservation(recyclerView,
                        reservations, controller);
                recyclerView.setAdapter(adapterReservation);
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvReservations);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(true);

        Button logoutBtn = (Button)findViewById(R.id.viewReservationslogoutBtn);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewReservationsUserScreen.this,
                        ApplicationMainScreen.class));
            }
        });

        ImageButton backBtn = (ImageButton) findViewById(R.id.vrUserBackButton);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewReservationsUserScreen.this,
                        UserHomeScreen.class));
            }
        });
    }
}