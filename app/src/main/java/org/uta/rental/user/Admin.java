package org.uta.rental.user;

public class Admin extends RegisterUser {
    public Admin() {
        this.setRole(UserType.ADMIN);
    }
}
