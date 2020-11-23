package org.uta.rental.user;

public class User extends NonAdmin {
    public User() {
        this.setRole(UserType.USER);
    }
}
