package org.uta.rental;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import org.uta.rental.car.Car;
import org.uta.rental.car.CarStatus;
import org.uta.rental.profile.ViewProfile;
import org.uta.rental.reservation.Reservation;
import org.uta.rental.reservation.TotalCostUtility;
import org.uta.rental.user.Admin;
import org.uta.rental.user.RegisterUser;
import org.uta.rental.user.RentalManager;
import org.uta.rental.user.User;
import org.uta.rental.user.UserType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void saveCar(Car car) throws SQLiteException {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("carNumber", car.getCarNumber());
        cv.put("carName", car.getCarName());
        cv.put("capacity", car.getCapacity());
        cv.put("carStatus", car.getCarStatus().getStatus());
        long res = db.replace("tbl_cars", null, cv);

        if(res== -1) {
            throw new SQLiteException("Unable to insert car");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<Car> findCarsByAvailabilityDateAndTime(LocalDateTime availableTime) {
        final List<Car> cars = new ArrayList<>();
        SQLiteDatabase sqldb = this.getReadableDatabase();
        String query = "select * from tbl_cars";
        Cursor cursor = sqldb.rawQuery(query, null);
        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            CarStatus carStatus = CarStatus.valueOf(cursor.getString(3).toUpperCase());

            if (carStatus == CarStatus.RESERVED) {
                continue;
            }

            long carNumber = cursor.getInt(0);
            String carName = cursor.getString(1);
            int capacity = cursor.getInt(2);


            Car car = new Car();
            car.setCarNumber(carNumber);
            car.setCarName(carName);
            car.setCarStatus(carStatus);
            car.setCapacity(capacity);

            cars.add(car);
        }

        Collections.sort(cars, new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                int result = o1.getCarName().compareTo(o2.getCarName());

                if (result == 0) {
                    result = new Integer(o1.getCapacity()).compareTo(o2.getCapacity());
                }

                return result;
            }
        });

        return cars;
    }




    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<Car> findCarsByAvailabilityDateAndCarName(TotalCostUtility.CarType carType, LocalDateTime availableTime) {
        final List<Car> cars = new ArrayList<>();
        SQLiteDatabase sqldb = this.getReadableDatabase();
        String query = "select * from tbl_cars";
        Cursor cursor = sqldb.rawQuery(query, null);
        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            CarStatus carStatus = CarStatus.valueOf(cursor.getString(3).toUpperCase());
            String carName = cursor.getString(1);

            if (carStatus == CarStatus.RESERVED || !carType.getType().equals(carName)) {
                continue;
            }

            long carNumber = cursor.getInt(0);
            int capacity = cursor.getInt(2);

            Car car = new Car();
            car.setCarNumber(carNumber);
            car.setCarName(carName);
            car.setCarStatus(carStatus);
            car.setCapacity(capacity);

            cars.add(car);
        }

        Collections.sort(cars, new Comparator<Car>() {
            @Override
            public int compare(Car o1, Car o2) {
                int result = o1.getCarName().compareTo(o2.getCarName());

                if (result == 0) {
                    result = new Integer(o1.getCapacity()).compareTo(o2.getCapacity());
                }

                return result;
            }
        });

        return cars;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void saveReservation(Reservation reservation) throws SQLiteException {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("reservationnumber", reservation.getReservationNumber());
        cv.put("carnumber", reservation.getCarNumber());
        cv.put("carname", reservation.getCarName());
        cv.put("capacity", reservation.getCapacity());
        cv.put("onstar", reservation.isOnStar());
        cv.put("gps", reservation.isGps());
        cv.put("siriusxm", reservation.isSiriusXm());
        cv.put("startdatetime", DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(reservation.getStartDateTime()));
        cv.put("enddatetime", DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(reservation.getEndDateTime()));
        cv.put("aamemberid", reservation.getAaMemberId());
        cv.put("username", reservation.getOwningUsername());
        // put remainder of data stored here
        long res = db.replace("tbl_reservation", null, cv);

        if(res== -1) {
            throw new SQLiteException("Unable to insert reservation");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<Reservation> getReservationsFromDateAndTime(LocalDateTime dateTime) {
        final List<Reservation> reservations = new ArrayList<>();
        SQLiteDatabase sqldb = this.getReadableDatabase();
        String query = "select * from tbl_reservation";
        Cursor cursor = sqldb.rawQuery(query, null);
        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            LocalDateTime startDateTime = LocalDateTime.parse(cursor.getString(7),
                    DateTimeFormatter.ISO_LOCAL_DATE_TIME);

            String userName = cursor.getString(10);
            if (startDateTime.isBefore(dateTime)) {
                continue;
            }

            long id = cursor.getLong(0);
            long carNumber = cursor.getLong(1);
            String carName = cursor.getString(2);
            int capacity = cursor.getInt(3);
            boolean gps = cursor.getInt(4) == 1;
            boolean onStar = cursor.getInt(5) == 1;
            boolean siriusXm = cursor.getInt(6) == 1;
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
            reservation.setOwningUsername(userName);

            reservations.add(reservation);
            cursor.moveToNext();
        }

        Collections.sort(reservations, new Comparator<Reservation>() {
            @Override
            public int compare(Reservation o1, Reservation o2) {
                LocalDateTime o1Date = o1.getStartDateTime().withNano(0);
                LocalDateTime o2Date = o2.getStartDateTime().withNano(0);
                int result = o2Date.compareTo(o1Date);

                if (result == 0) {
                    result = new Integer(o1.getCapacity()).compareTo(new Integer(o2.getCapacity()));
                }

                return result;
            }
        });

        return reservations;
    }

    public void deleteReservation(Reservation reservation) {
        SQLiteDatabase sqldb = this.getReadableDatabase();
        boolean deleted = sqldb.delete("tbl_reservation", "reservationnumber=" + reservation.getReservationNumber(), null) > 0;

        if (!deleted) {
            throw new SQLiteException("Unable to delete reservation");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<Reservation> getReservationsFromDateAndTimeAndOwningUser(LocalDateTime dateTime,
                                                                         String owningUserName) {
        List<Reservation> reservationsFiltered = new ArrayList<>();
        for (Reservation reservation : getReservationsFromDateAndTime(dateTime)) {
            if (owningUserName.equals(reservation.getOwningUsername())) {
                reservationsFiltered.add(reservation);
            }
        }


        return reservationsFiltered;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Reservation getReservation(long id) throws SQLiteException {
        LocalDateTime dateTime = LocalDateTime.MIN;
        List<Reservation> reservations = getReservationsFromDateAndTime(dateTime);
        Optional<Reservation> reservationOptional = Optional.empty();
        for (Reservation reservation: reservations) {
            if (reservation.getReservationNumber() == id) {
               reservationOptional = Optional.of(reservation);
            }
        }

        if (!reservationOptional.isPresent()) {
            throw new SQLiteException("Bad id could not find reservation");
        }

        return reservationOptional.get();
    }

    public void saveUser(RegisterUser registerUser) throws SQLiteException {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", registerUser.getUserName());
        cv.put("password", registerUser.getPassword());
        cv.put("usertype", registerUser.getRole().getType());
        cv.put("utaid", registerUser.getUtaId());
        cv.put("lastname", registerUser.getLastName());
        cv.put("firstname", registerUser.getFirstName());
        cv.put("phone", registerUser.getPhoneNumber());
        cv.put("email", registerUser.getEmail());
        cv.put("streetaddress", registerUser.getStreetAddress());
        cv.put("city", registerUser.getCity());
        cv.put("state", registerUser.getState());
        cv.put("zipcode", registerUser.getZipCode());
        cv.put("Rentalprivilegestatus", registerUser.getRentalPrivilegeStatus());
        cv.put("aacmemberId", registerUser.getAacMemberId());
        // put remainder of data stored here

        long res = db.insert("tbl_registerUser", null,cv );

        if(res== -1) {
            throw new SQLiteException("Unable to insert register user.");
        }
    }

    public ViewProfile findUserByUsernameforRentalPrivilegeStatus(String username) {
        SQLiteDatabase sqldb = this.getReadableDatabase();
        String query = "Select * from tbl_registerUser where username = '" + username + "'";
        Cursor cursor = sqldb.rawQuery(query, null);

        ViewProfile vp = new ViewProfile();

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            String password = cursor.getString(1);
            String typeString = cursor.getString(2);
            UserType userType = null;
            for (UserType type : UserType.values()) {
                if (type.getType().equals(typeString)) {
                    userType = type;
                }
            }

            String rentalPrivStatus = cursor.getString(12);

            vp.setRentalprivilegeStatus(rentalPrivStatus);

        }
        return vp;
    }

    public Optional<RegisterUser> findUserByUsername(String username) {
        Optional<RegisterUser> registerUserOptional = Optional.empty();
        SQLiteDatabase sqldb = this.getReadableDatabase();
        String query = "Select * from tbl_registerUser where username = '" + username + "'";
        Cursor cursor = sqldb.rawQuery(query, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            String password = cursor.getString(1);
            String typeString = cursor.getString(2);
            UserType userType = null;
            for (UserType type : UserType.values()) {
                if (type.getType().equals(typeString)) {
                    userType = type;
                }
            }

            String utaId = cursor.getString(3);
            String lastName = cursor.getString(4);
            String firstName = cursor.getString(5);
            String phone = cursor.getString(6);
            String email = cursor.getString(7);
            String streetAddress = cursor.getString(8);
            String city = cursor.getString(9);
            String state = cursor.getString(10);
            String zipCode = cursor.getString(11);
            String rentalPrivlege = cursor.getString(12);
            String aacMemberId = cursor.getString(13);

            RegisterUser registerUser = null;
            if (userType == UserType.RENTAL_MANAGER) {
               registerUser = new RentalManager();
            } else if (userType == UserType.ADMIN) {
               registerUser = new Admin();
            } else {
               registerUser = new User();
            }

            registerUser.setUserName(username);
            registerUser.setPassword(password);
            registerUser.setUtaId(utaId);
            registerUser.setLastName(lastName);
            registerUser.setFirstName(firstName);
            registerUser.setPhoneNumber(phone);
            registerUser.setEmail(email);
            registerUser.setStreetAddress(streetAddress);
            registerUser.setCity(city);
            registerUser.setState(state);
            registerUser.setZipCode(zipCode);
            registerUser.setRentalPrivilegeStatus(rentalPrivlege);
            registerUser.setAacMemberId(aacMemberId);

            registerUserOptional = Optional.of(registerUser);
        }

        return registerUserOptional;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("database", "Creating car_rental database.");
        String qry = "create table tbl_registerUser(username text primary key,password text, " +
                "usertype text,utaid text,lastname text,firstname text,phone text,email text," +
                "streetaddress text, city text,state text,zipcode text , Rentalprivilegestatus text," +
                "aacmemberId text)";
        db.execSQL(qry);
        qry = "create table tbl_reservation(reservationnumber int primary key,carnumber int," +
                "carname text,capacity int,gps int,onstar int,siriusxm int,startdatetime text," +
                "enddatetime text,aamemberid text,username text)";
        db.execSQL(qry);

        qry ="create table tbl_cars(carNumber int primary key, carName text, capacity int, carStatus text)";
        db.execSQL(qry);
    }

    public void updateProfileDataonDataBase(ViewProfile viewProfile)
    {
        SQLiteDatabase sqldb = this.getReadableDatabase();

//      String query1 =  "update tbl_registerUser set username , password, usertype, utaid, lastname, firstname, phone, email, streetaddress, city, state, zipcode)        values ('username' , 'password', 'usertype', 'utaid', 'lastname', 'firstname', 'phone', 'email', 'streetaddress', 'city', 'state', 'zipcode')";
        String query = "update tbl_registerUser set utaid = '" + viewProfile.getUtaID() + "', " +
                        "password = '" + viewProfile.getPassword() + "', " +
                        //"usertype = '" + viewProfile.getRole() + "' " +
                        "utaid = '" + viewProfile.getUtaID() + "', " +
                        "lastname = '" + viewProfile.getLastName() + "', " +
                        "firstname = '" + viewProfile.getFirstName() + "', " +
                        "phone = '" + viewProfile.getPhone() + "', " +
                        "email = '" + viewProfile.getEmail() + "', " +
                        "streetaddress = '" + viewProfile.getStreetAddress() + "', " +
                        "city = '" + viewProfile.getCity() + "', " +
                        "state = '" + viewProfile.getState() + "', " +
                        "zipcode = '" + viewProfile.getZipCode() + "', " +
                        "Rentalprivilegestatus = '" + viewProfile.getRentalprivilegeStatus() + "' " +
                        "where username = '" + viewProfile.getUserName() + "' ";
        System.err.println("0809 update profile Db manager L 272 query = "+ query);
        try {
            sqldb.execSQL(query);
        }
        catch (Exception ex){
            System.err.println("0809 update profile Db manager L 277 exception thrown");
            ex.printStackTrace();
        }
        System.err.println(viewProfile.getUserName());

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
