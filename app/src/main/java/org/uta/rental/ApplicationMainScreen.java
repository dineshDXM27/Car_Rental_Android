package org.uta.rental;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class ApplicationMainScreen extends AppCompatActivity {

    private UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_main_screen);

        userDAO = new UserDAO(this);
        userDAO.onUpgrade(userDAO.getWritableDatabase(), 0, 0);
        userDAO.registerUser("foou", "foop", UserType.USER);

        boolean passCheck = userDAO.checkPassword("foou", "foop");
        UserType userType = userDAO.getUserType("foou").get();

        Log.d("Check", String.format("Password check result is %b", passCheck));
        Log.d("Check", String.format("Usertype is %s", userType.getType()));
    }
}