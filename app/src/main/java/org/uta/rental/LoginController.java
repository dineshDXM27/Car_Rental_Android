package org.uta.rental;

import android.util.Log;

import org.uta.rental.user.RegisterUser;
import org.uta.rental.user.UserType;

import java.util.Optional;


public class LoginController {

    private static String currentUser;

    public UserType loginFunction(String username, String password, DBManager dbManager)
    {
        UserType userType;

        Optional<RegisterUser> registerUserOptional = dbManager.findUserByUsername(username);

        boolean checkCredentials = registerUserOptional.isPresent() &&
                password.equals(registerUserOptional.get().getPassword());
        if(!checkCredentials)
        {
            //String msg = "please recheck your user name and password as they do not match";
            return null;
        }

        try{
            userType = registerUserOptional.get().getRole();
            Log.i("Logging in", String.format("User type %s logged in.", userType.getType()));
            this.currentUser = registerUserOptional.get().getUserName();
            if(checkCredentials && userType == UserType.USER)
            {
                return UserType.USER;
            }
            else if (checkCredentials && userType == UserType.ADMIN){
                return UserType.ADMIN;
            }
            else if (checkCredentials && userType == UserType.RENTAL_MANAGER){
                return UserType.RENTAL_MANAGER;
            }

        }
        catch (Exception e) {
            Log.e("Logging in", e.getMessage());
        }
        //return "please recheck your user name and password as they do not match";
        return null;
    }

    public static String getCurrentUser() {
        return currentUser;
    }
}
