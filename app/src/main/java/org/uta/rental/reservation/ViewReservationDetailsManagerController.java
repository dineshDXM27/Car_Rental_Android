package org.uta.rental.reservation;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class ViewReservationDetailsManagerController {

    private Context context;

    private static Reservation res;

    public ViewReservationDetailsManagerController(Context context) {
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public RentalManagerReservationDetails viewReservationDetails() {
        RentalManagerReservationDetails reservation = new RentalManagerReservationDetails();
        reservation.setReservationNumber(res.getReservationNumber());
        reservation.setCarName(res.getCarName());
        reservation.setCarNumber(res.getCarNumber());
        reservation.setCapacity(res.getCapacity());
        reservation.setStartDateTime(res.getStartDateTime());
        reservation.setEndDateTime(res.getEndDateTime());
        reservation.setAaMemberId(res.getAaMemberId());
        reservation.setGps(res.isGps());
        reservation.setOnStar(res.isOnStar());
        reservation.setSiriusXm(res.isSiriusXm());

        return reservation;
    }

    public static void setReservation(Reservation reservation) {
        ViewReservationDetailsManagerController.res = reservation;
    }
}
