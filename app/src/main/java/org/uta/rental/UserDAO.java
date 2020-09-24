package org.uta.rental;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UserDAO extends SQLiteOpenHelper {
    private static final String dbname = "Users.db";
    public UserDAO( Context context) {
        super(context,dbname , null, 1);
    }

    public Cursor getUserDetails(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = new String[]{"username", "usertype"};
        Cursor cursor = db.query("tbl_registerUser", columns, "username = '"+username+"'", null, null, null, null);
        return cursor;
    }

    public void registerUser(String username, String password, UserType userType) {
        SQLiteDatabase wdb = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("password", password);
        cv.put("usertype", userType.getType());
        // put remainder of data stored here

        wdb.insert("tbl_registerUser", null,cv );
    }

    public boolean checkPassword(String username, String password) {
        SQLiteDatabase sqldb = this.getReadableDatabase();
        String queryForCheckingPassword = "Select * from tbl_registerUser where username = '" + username + "' and password = '"+password+"'";
        Cursor cursor = sqldb.rawQuery(queryForCheckingPassword, null);
        return cursor.getCount() > 0;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry = "create table tbl_registerUser(username text primary key,password text, usertype text)";
        db.execSQL(qry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tbl_registerUser");
        onCreate(db);
    }
}
