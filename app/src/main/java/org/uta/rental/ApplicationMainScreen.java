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
            System.out.println("Version issue due to get method not available in version less than 24");
        }


        Log.d("Check", String.format("Password check result is %b", passCheck));
        Log.d("Check", String.format("Usertype is %s", userType.getType()));
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
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                userType = userDAO.getUserType(username.getText().toString()).get();
                String s = userType.getType();
                Log.i("User type %s logged in.", s);
                if(checkCredentials && (s == "user") )
                {
                    Intent intent = new Intent(this,UserHomeScreen.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"You have successfully Logged In as User",Toast.LENGTH_SHORT).show();
                }
            }
        }catch (Exception e)
        {
            Toast.makeText(getApplicationContext()," TODO - Version issue due to get method not available in version less than 24",Toast.LENGTH_SHORT).show();
        }

    }

    public void registerFunc(View view) {
        Toast.makeText(getApplicationContext(),"This is under process",Toast.LENGTH_LONG).show();
    }
}