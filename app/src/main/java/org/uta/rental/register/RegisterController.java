package org.uta.rental.register;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import org.uta.rental.ApplicationMainScreen;
import org.uta.rental.DBManager;
import org.uta.rental.reservation.Reservation;
import org.uta.rental.reservation.UserReservationDetails;
import org.uta.rental.reservation.ViewReservationDetailsUserController;
import org.uta.rental.reservation.ViewReservationDetailsUserScreen;
import org.uta.rental.user.RegisterUser;

public class RegisterController {

    private final Context context;


    public RegisterController(Context context) {
        this.context = context;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void register(RegisterUser registerUser) {
        DBManager dbManager = DBManager.getInstance(context);
        dbManager.saveUser(registerUser);

        Intent intent = new Intent(context, ApplicationMainScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        Toast.makeText(context, "Registration Successful", Toast.LENGTH_SHORT).show();
    }
}
