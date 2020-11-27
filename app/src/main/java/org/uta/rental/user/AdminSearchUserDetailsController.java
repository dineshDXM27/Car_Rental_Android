package org.uta.rental.user;

import android.content.Context;

import org.uta.rental.ViewProfile;
import org.uta.rental.ViewProfileAdmin;

public class AdminSearchUserDetailsController {
    private static ViewProfile users;
    private Context context;

    public AdminSearchUserDetailsController(Context context){
        this.context = context;
    }

    public ViewProfileAdmin userDetails(){
        ViewProfileAdmin user = new ViewProfileAdmin();
        user.setUserName(users.getUserName());
        user.setPassword(users.getPassword());
        user.setUtaID(users.getUtaID());
        user.setLastName(users.getLastName());
        user.setFirstName(users.getFirstName());
        user.setPhone(users.getPhone());
        user.setRole(users.getRole());
        user.setEmail(users.getEmail());
        user.setStreetAddress(users.getStreetAddress());
        user.setCity(users.getCity());
        user.setState(users.getState());
        user.setZipCode(users.getZipCode());
        user.setRentalprivilegeStatus(users.getRentalprivilegeStatus());

        return user;
    }

    public static void setUsers(ViewProfile users) {
        AdminSearchUserDetailsController.users = users;
    }
}
