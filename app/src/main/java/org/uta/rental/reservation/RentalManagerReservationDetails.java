package org.uta.rental.reservation;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.uta.rental.reservation.ReservationDetails;

import java.time.Duration;

public class RentalManagerReservationDetails extends ReservationDetails {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Duration getDuration() {
       return Duration.between(getEndTime().toLocalTime(), getStartTime().toLocalTime());
    }

}
