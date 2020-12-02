package org.uta.rental.user;

import android.content.Context;

import org.uta.rental.DBManager;
import org.uta.rental.profile.ViewProfile;

import java.util.List;

public class AdminSearchUserController {
    private Context context;
    private String lastName;

    public AdminSearchUserController(Context context, String lastName){
        this.lastName = lastName;
        this.context = context;
    }

    public List<ViewProfile> displayUsers(){
        String lName = lastName;
       return DBManager.getInstance(context).getUsersFromLastName(lName);
    }
}

