package org.uta.rental;

import androidx.appcompat.app.AppCompatActivity;
//import org.uta.rental.ApplicationMainScreen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.uta.rental.user.RegisterUser;

import java.util.Optional;

public class UpdateProfileScreen extends AppCompatActivity {

    EditText userNameUpdateProfileETups;
    EditText passwordUpdateProfileETups;
    EditText utaidUpdateProfileETups;
    EditText lastNameUpdateProfileETups;
    EditText firstNameUpdateProfileETups;
    EditText phoneUpdateProfileETups;
    EditText roleUpdateProfileETups;
    EditText emailUpdateProfileETups;
    EditText streetAddressUpdateProfileETups;
    EditText cityUpdateProfileETups;
    EditText zipcodeUpdateProfileETups;
    EditText stateUpdateProfileETups;
    EditText aacMemberIdUpdateProfileETups;
    EditText RentalPriviledgeStatusUpdateProfileETups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile_screen);
        userNameUpdateProfileETups = (EditText) findViewById(R.id.userNameUpdateProfileETups);
        passwordUpdateProfileETups =  (EditText) findViewById(R.id.passwordUpdateProfileETups);
        utaidUpdateProfileETups = (EditText) findViewById(R.id.utaidUpdateProfileETups);
        lastNameUpdateProfileETups = (EditText) findViewById(R.id.lastNameUpdateProfileETups);
        firstNameUpdateProfileETups = (EditText) findViewById(R.id.firstNameUpdateProfileETups);
        phoneUpdateProfileETups = (EditText) findViewById(R.id.phoneUpdateProfileETups);
        roleUpdateProfileETups = (EditText) findViewById(R.id.roleUpdateProfileETups);
        emailUpdateProfileETups = (EditText) findViewById(R.id.emailUpdateProfileETups);
        streetAddressUpdateProfileETups = (EditText) findViewById(R.id.streetAddressUpdateProfileETups);
        cityUpdateProfileETups = (EditText) findViewById(R.id.cityUpdateProfileETups);
        zipcodeUpdateProfileETups =(EditText) findViewById(R.id.zipcodeUpdateProfileETups);
        stateUpdateProfileETups = (EditText) findViewById(R.id.stateUpdateProfileETups);
        //aacMemberIdUpdateProfileETups = (EditText) findViewById(R.id.aacMemberIdUpdateProfileETups);
        RentalPriviledgeStatusUpdateProfileETups = (EditText) findViewById(R.id.RentalPriviledgeStatusUpdateProfileETups);

        session = new Session(getApplicationContext());
        this.setUserName(session.getValue(StringConstants.USERNAME));
        //System.err.println("0809 L 15 Update profile  username="+ this.getUserName());
        datavaluesFetching(this.getUserName() );

    }

    private void datavaluesFetching(String UserName) {
        DBManager dbManager = DBManager.getInstance(getApplicationContext());
        Optional<RegisterUser> regUserOpt = dbManager.findUserByUsername(UserName);
        RegisterUser regUser = regUserOpt.get();
        userNameUpdateProfileETups.setText(UserName);
        passwordUpdateProfileETups.setText(regUser.getPassword());
        utaidUpdateProfileETups.setText(regUser.getUtaId());
        lastNameUpdateProfileETups.setText(regUser.getLastName());
        firstNameUpdateProfileETups.setText(regUser.getFirstName());
        phoneUpdateProfileETups.setText(regUser.getPhoneNumber());
        roleUpdateProfileETups.setText(regUser.getRole().toString());
        emailUpdateProfileETups.setText(regUser.getEmail());
        streetAddressUpdateProfileETups.setText(regUser.getStreetAddress());
        cityUpdateProfileETups.setText(regUser.getCity());
        zipcodeUpdateProfileETups.setText(regUser.getZipCode());
        stateUpdateProfileETups.setText(regUser.getState());
        ViewProfile rentalprivstatus = dbManager.findUserByUsernameforRentalPrivilegeStatus(UserName);
        RentalPriviledgeStatusUpdateProfileETups.setText(rentalprivstatus.getRentalprivilegeStatus());
        //aacMemberIdUpdateProfileETups.setText(regUser.g);


    }

    private Session session;
    private String userName;
    private String password;
    private String utaID;
    private String lastName;
    private String firstName;
    private String phone;
    private String role;
    private String email;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String rentalprivilegeStatus;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUtaID() {
        return utaID;
    }

    public void setUtaID(String utaID) {
        this.utaID = utaID;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public UpdateProfileScreen()
    {
        showDataOnUpdateProfileScreen();
    }

    public void showDataOnUpdateProfileScreen()
    {
        ApplicationMainScreen ams = new ApplicationMainScreen();
        String username = ams.getUserNamestr();
        System.out.println("line No 131");
        System.out.println(username);
    }

    public void UpdateProfileFunc(View view) {

        ViewProfile viewProfile = new ViewProfile();

        session = new Session(getApplicationContext());
        this.setUserName(session.getValue(StringConstants.USERNAME));
        viewProfile.setUserName(this.getUserName());

        viewProfile.setPassword(passwordUpdateProfileETups.getText().toString());
        viewProfile.setUtaID(utaidUpdateProfileETups.getText().toString());
        viewProfile.setLastName(lastNameUpdateProfileETups.getText().toString());
        viewProfile.setFirstName(firstNameUpdateProfileETups.getText().toString());
        viewProfile.setPhone(phoneUpdateProfileETups.getText().toString());
        viewProfile.setRole(roleUpdateProfileETups.getText().toString());
        viewProfile.setEmail(emailUpdateProfileETups.getText().toString());
        viewProfile.setStreetAddress(streetAddressUpdateProfileETups.getText().toString());
        viewProfile.setCity(cityUpdateProfileETups.getText().toString());
        viewProfile.setZipCode(zipcodeUpdateProfileETups.getText().toString());
        viewProfile.setState(stateUpdateProfileETups.getText().toString());
        viewProfile.setRentalprivilegeStatus(RentalPriviledgeStatusUpdateProfileETups.getText().toString());


        //System.err.println(passwordUpdateProfileETups.getText().toString());

        DBManager dbManager = DBManager.getInstance(getApplicationContext());
        dbManager.updateProfileDataonDataBase(viewProfile);
        Toast.makeText(getApplicationContext(),"underProcess",Toast.LENGTH_SHORT).show();

    }

    public void logoutFunc(View view) {
        startActivity(new Intent(UpdateProfileScreen.this, ApplicationMainScreen.class));
    }
}