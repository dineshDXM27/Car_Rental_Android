package org.uta.rental;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        Log.i("Creating car_rental database.", null);
        String qry = "create table tbl_registerUser(username text primary key,password text, usertype text)";
        db.execSQL(qry);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("Upgrading car_rental database.", null);
        db.execSQL("DROP TABLE IF EXISTS tbl_registerUser");
        onCreate(db);
    }
}
