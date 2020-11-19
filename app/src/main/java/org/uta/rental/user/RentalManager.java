package org.uta.rental.user;

public class RentalManager extends  NonAdmin {
    public RentalManager() {
        this.setRole(UserType.RENTAL_MANAGER);
    }
}
