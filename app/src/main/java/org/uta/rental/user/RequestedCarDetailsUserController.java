package org.uta.rental.user;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import org.uta.rental.DBManager;
import org.uta.rental.Session;
import org.uta.rental.StringConstants;
import org.uta.rental.car.Car;
import org.uta.rental.car.CarsInformation;
import org.uta.rental.reservation.Reservation;
import org.uta.rental.reservation.TotalCostUtility;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RequestedCarDetailsUserController {

    private final Context context;

    private final Random random;

    public RequestedCarDetailsUserController(Context context) {
        this.context = context;
        this.random = new Random();
    }

    /*
      qry = "create table tbl_reservation(reservationnumber int primary key,carnumber int," +
                "carname text,capacity int,gps int,onstar int,siriusxm int,startdatetime text," +
                "enddatetime text,aamemberid text,username text)";
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void reserve(CarsInformation carsInformation, LocalDateTime start, LocalDateTime end) {
        DBManager dbManager = DBManager.getInstance(context);
        Reservation reservation = new Reservation();
        reservation.setReservationNumber((random.nextInt(65536)-32768));
        reservation.setCarNumber(carsInformation.getCarNumber());
        reservation.setCarName(carsInformation.getCarName());
        reservation.setCapacity(carsInformation.getCapacity());
        reservation.setGps(false);
        reservation.setOnStar(false);
        reservation.setSiriusXm(false);
        reservation.setStartDateTime(start);
        reservation.setEndDateTime(end);
        Session session = new Session(getApplicationContext());
        String userName = session.getValue(StringConstants.USERNAME);
        RegisterUser registerUser = dbManager.findUserByUsername(userName).get();
        reservation.setOwningUsername(userName);
    }
}

