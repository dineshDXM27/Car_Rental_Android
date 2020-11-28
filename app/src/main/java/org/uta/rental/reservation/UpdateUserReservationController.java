package org.uta.rental.reservation;

import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;

import org.uta.rental.DBManager;

public class UpdateUserReservationController {

    private final Context context;


    public UpdateUserReservationController(Context context) {
        this.context = context;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void updateReservation(UserReservationDetails userReservationDetails) {
        DBManager dbManager = DBManager.getInstance(context);
        Reservation reservation = dbManager.getReservation(userReservationDetails.getReservationNumber());
        reservation.setSiriusXm(userReservationDetails.isSiriusXm());
        reservation.setGps(userReservationDetails.isGps());
        reservation.setOnStar(userReservationDetails.isOnStar());
        reservation.setEndDateTime(userReservationDetails.getEndDateTime());
        reservation.setStartDateTime(userReservationDetails.getStartDateTime());

        dbManager.saveReservation(reservation);
        ViewReservationDetailsUserController.setReservation(reservation);

        Intent intent = new Intent(context, ViewReservationDetailsUserScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void cancelUpdate(UserReservationDetails userReservationDetails) {
        Intent intent = new Intent(context, ViewReservationDetailsUserScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("UserReservationDetails", userReservationDetails);
        context.startActivity(intent);
    }
}
