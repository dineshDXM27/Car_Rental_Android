package org.uta.rental.car;

public enum CarStatus {
    RESERVED("reserved"),
    AVAILABLE("available");

    private final String status;

    CarStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
