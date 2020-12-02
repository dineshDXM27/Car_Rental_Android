package org.uta.rental;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    private SharedPreferences prefs;

    public Session(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setValue(String key, String value) {
        prefs.edit().putString(key, value).commit();
    }

    public String getValue(String key) {
        String usename = prefs.getString(key,"");
        return usename;
    }

}
