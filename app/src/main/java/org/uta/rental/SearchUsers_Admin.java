package org.uta.rental;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SearchUsers_Admin extends AppCompatActivity {

    private String userName;
    private String lastName;
    private String firstName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_search_user_screen);
    }

}
