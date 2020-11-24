package org.uta.rental.reservation;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import org.uta.rental.DBManager;
import org.uta.rental.LoginController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ViewReservationDetailsUserController {

    private Context context;

    private static Reservation res;

    public ViewReservationDetailsUserController(Context context) {
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public UserReservationDetails viewReservationDetails() {
        UserReservationDetails reservation = new UserReservationDetails();
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

    public void cancelReservation() {
        DBManager.getInstance(context).deleteReservation(res);
        Intent intent = new Intent(context, ViewReservationsUserScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void setReservation(Reservation reservation) {
        ViewReservationDetailsUserController.res = reservation;
    }
}
