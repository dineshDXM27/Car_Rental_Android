package org.uta.rental;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.Duration;

public class RentalManagerReservationDetails extends ReservationDetails {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Duration getDuration() {
       return Duration.between(getEndTime().toLocalTime(), getStartTime().toLocalTime());
    }

}
