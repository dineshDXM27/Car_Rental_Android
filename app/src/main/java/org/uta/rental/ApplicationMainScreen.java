package org.uta.rental;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class ApplicationMainScreen extends AppCompatActivity {

    private UserDAO userDAO;
    private EditText username, password;
    public static final String SUCCESSFUL_LOGIN_MSG = "You have successfully logged in";

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_main_screen);

        userDAO = new UserDAO(this);
       // userDAO.registerUser("user", "password", UserType.USER);
        boolean passCheck = userDAO.checkPassword("user", "password");
        UserType userType = null;
        try
        {
            userType = userDAO.getUserType("user").get();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }


//        Log.d("Check", String.format("Password check result is %b", passCheck));
//        Log.d("Check", String.format("Usertype is %s", userType.getType()));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void loginFunc(View view) {
        username = findViewById(R.id.editTextTextPersonName);
        password = findViewById(R.id.editTextTextPassword);
        UserType userType;

        boolean checkCredentials = userDAO.checkPassword(username.getText().toString(),password.getText().toString());
        if(!checkCredentials)
        {
            Toast.makeText(getApplicationContext(),"please recheck your user name and password as they do not match",Toast.LENGTH_SHORT).show();
            return;
        }

        try{
            userType = userDAO.getUserType(username.getText().toString()).get();
            Log.i("Logging in", String.format("User type %s logged in.", userType.getType()));
            if(checkCredentials && userType == UserType.USER)
            {
                Intent intent = new Intent(this,UserHomeScreen.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), SUCCESSFUL_LOGIN_MSG,Toast.LENGTH_SHORT).show();
            }
            else if (checkCredentials && userType == UserType.ADMIN){
                startActivity(new Intent(this, AdminMainScreen.class));
                Toast.makeText(getApplicationContext(), SUCCESSFUL_LOGIN_MSG, Toast.LENGTH_SHORT).show();
            }
            else if (checkCredentials && userType == UserType.RENTAL_MANAGER){
                startActivity(new Intent(this, RentalManagerScreen.class));
                Toast.makeText(getApplicationContext(), SUCCESSFUL_LOGIN_MSG, Toast.LENGTH_SHORT).show();
            }

        }
        catch (Exception e) {
            Log.e("Logging in", e.getMessage());
        }
    }

    public void registerFunc(View view) {
        Toast.makeText(getApplicationContext(),"This is under process",Toast.LENGTH_LONG).show();
    }
}