package org.uta.rental.reservation;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import org.uta.rental.AdminMainScreen;
import org.uta.rental.ApplicationMainScreen;
import org.uta.rental.R;
import org.uta.rental.UserHomeScreen;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ViewReservationsUserScreen extends AppCompatActivity {


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
        }

        return reservations;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reservations_screen);
        ArrayList<Reservation> list = (ArrayList) generateReservations();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvReservations);
        AdapterReservation adapterReservation = new AdapterReservation(recyclerView,
                list);
        recyclerView.setAdapter(adapterReservation);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(true);

        Button logoutBtn = (Button)findViewById(R.id.viewReservationslogoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewReservationsUserScreen.this,
                        ApplicationMainScreen.class));
            }
        });
    }
}