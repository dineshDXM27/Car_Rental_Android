package org.uta.rental.user;

public enum UserType {

    ADMIN("admin"),
    RENTAL_MANAGER("rental_manager"),
    USER("user");

    private final String type;

    UserType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
