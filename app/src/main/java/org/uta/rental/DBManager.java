package org.uta.rental;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.uta.rental.carsInformation.CarsInformation;
import org.uta.rental.reservation.Reservation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


public class DBManager extends SQLiteOpenHelper
{

    private static final String dbname = "car_rental.db";

    private static DBManager dbManager = null;

    private DBManager(Context context){
        super(context,dbname , null, 1);
    }

    public static DBManager getInstance(Context context) {
        if (dbManager == null) {
            dbManager = new DBManager(context.getApplicationContext());
        }

        return dbManager;
    }

    public void saveReservation(Reservation reservation) throws SQLiteException {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("reservationnumber", reservation.getReservationNumber());
        cv.put("carnumber", reservation.getCarNumber());
        cv.put("carname", reservation.getCarName());
        cv.put("capacity", reservation.getCapacity());
        cv.put("onstar", reservation.isOnStar());
        cv.put("siriusxm", reservation.isSiriusXm());
        cv.put("startdatetime", reservation.getStartDateTime().toString());
        cv.put("enddatetime", reservation.getEndDateTime().toString());
        cv.put("aamemberid", reservation.getAaMemberId());
        // put remainder of data stored here

        long res = db.insert("tbl_reservation", null,cv );

        if(res== -1) {
            throw new SQLiteException("Unable to insert reservation");
        }
    }

    public Reservation getReservation(long id) throws SQLiteException {
        SQLiteDatabase sqldb = this.getReadableDatabase();
        String query = "Select * from tbl_reservation where reservationnumber = " + id;
        Cursor cursor = sqldb.rawQuery(query, null);
        if (cursor.getCount() == 0) {
            throw new SQLiteException("Bad id could not find reservation");
        }

        cursor.moveToFirst();
        long carNumber = cursor.getLong(1);
        String carName = cursor.getString(2);
        int capacity = cursor.getInt(3);
        boolean gps = cursor.getInt(4) == 1;
        boolean onStar = cursor.getInt(5) == 1;
        boolean siriusXm = cursor.getInt(6) == 1;
        LocalDateTime startDateTime = LocalDateTime.parse(cursor.getString(7),
                DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        LocalDateTime endDateTime = LocalDateTime.parse(cursor.getString(8),
                DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String aaMemberId = cursor.getString(9);

        Reservation reservation = new Reservation();
        reservation.setReservationNumber(id);
        reservation.setCarNumber(carNumber);
        reservation.setCarName(carName);
        reservation.setCapacity(capacity);
        reservation.setGps(gps);
        reservation.setOnStar(onStar);
        reservation.setSiriusXm(siriusXm);
        reservation.setStartDateTime(startDateTime);
        reservation.setEndDateTime(endDateTime);
        reservation.setAaMemberId(aaMemberId);

        return reservation;
    }



    public Optional<UserType> getUserType(String username) {
        Optional<UserType> userTypeOptional = Optional.empty();

        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = new String[]{"username", "usertype"};
        Cursor cursor = db.query("tbl_registerUser", columns, "username = '"+username+"'", null, null, null, null);
        if (cursor.getCount() == 1) {
            cursor.moveToFirst();
            String typeString = cursor.getString(1);
            for (UserType type : UserType.values()) {
                if (type.getType().equals(typeString)) {
                    userTypeOptional = Optional.of(type);
                }
            }
        }

        return userTypeOptional;
    }

    public boolean checkPassword(String username, String password) {
        SQLiteDatabase sqldb = this.getReadableDatabase();
        String queryForCheckingPassword = "Select * from tbl_registerUser where username = '" + username + "' and password = '"+password+"'";
        Cursor cursor = sqldb.rawQuery(queryForCheckingPassword, null);
        return cursor.getCount() > 0;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("database", "Creating car_rental database.");
        String qry = "create table tbl_registerUser(username text primary key,password text, usertype text)";
        db.execSQL(qry);
        qry = "create table tbl_reservation(reservationnumber int primary key,carnumber int," +
                "carname text,capacity int,gps int,onstar int,siriusxm int,startdatetime text," +
                "enddatetime text,aamemberid text)";
        db.execSQL(qry);
        qry ="create table tbl_cars(carNumber int primary key, carName text, capacity int, weekendRate int, weekRate int, weekRate int, GPSRate int, OnStarRate int, SiriusXM int, carStatus text)";
        db.execSQL(qry);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("database", "Upgrading car_rental database.");
        db.execSQL("DROP TABLE IF EXISTS tbl_registerUser");
        db.execSQL("DROP TABLE IF EXISTS tbl_reservation");
        db.execSQL("DROP TABLE IF EXISTS tbl_cars");
        onCreate(db);
    }
}
