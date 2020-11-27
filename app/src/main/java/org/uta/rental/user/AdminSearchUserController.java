package org.uta.rental.user;

import android.content.Context;
import android.widget.EditText;

import org.uta.rental.ViewProfile;

import java.util.List;

public class AdminSearchUserController {
    private Context context;
    private EditText lastName;

    public AdminSearchUserController(Context context, EditText lastName){
        this.lastName = lastName;
        this.context = context;
    }

    public List<ViewProfile> displayUsers(){
        return null;    //yet to implement
    }
}

