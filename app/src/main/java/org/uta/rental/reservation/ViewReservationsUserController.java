package org.uta.rental.reservation;

import android.content.Context;
import android.os.Build;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import org.uta.rental.DBManager;
import org.uta.rental.LoginController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ViewReservationsUserController {

    private Context context;
    private EditText date;
    private EditText time;

    public ViewReservationsUserController(Context context, EditText date, EditText time) {
        this.date = date;
        this.time = time;
        this.context = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<Reservation>  viewReservations() {
        String dateString = date.getText() + " " + time.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
        return DBManager.getInstance(context).getReservationsFromDateAndTimeAndOwningUser(dateTime,
                LoginController.getCurrentUser());
    }
}
