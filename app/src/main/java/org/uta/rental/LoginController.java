package org.uta.rental;

import android.util.Log;



public class LoginController {

    public String loginFunction(String username, String password, DBManager dbManager)
    {
        UserType userType;

        boolean checkCredentials = dbManager.checkPassword(username,password);
        if(!checkCredentials)
        {
            String msg = "please recheck your user name and password as they do not match";
            return msg;
        }

        try{
            userType = dbManager.getUserType(username).get();
            Log.i("Logging in", String.format("User type %s logged in.", userType.getType()));
            if(checkCredentials && userType == UserType.USER)
            {
                return "USER";
            }
            else if (checkCredentials && userType == UserType.ADMIN){
                return "ADMIN";
            }
            else if (checkCredentials && userType == UserType.RENTAL_MANAGER){
                return "RENTAL_MANAGER";
            }

        }
        catch (Exception e) {
            Log.e("Logging in", e.getMessage());
        }
        return "please recheck your user name and password as they do not match";
    }
}
