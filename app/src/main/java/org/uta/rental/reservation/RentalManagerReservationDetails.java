package org.uta.rental.reservation;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.uta.rental.reservation.ReservationDetails;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class RentalManagerReservationDetails extends ReservationDetails {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Duration getDuration() {
        int days = (int) getStartDateTime().until(getEndDateTime(),
                ChronoUnit.DAYS);
        return Duration.of(days, ChronoUnit.DAYS);
    }

}
