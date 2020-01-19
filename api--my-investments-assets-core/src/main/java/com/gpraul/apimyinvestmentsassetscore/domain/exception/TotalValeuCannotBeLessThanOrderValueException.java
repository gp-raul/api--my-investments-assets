package com.gpraul.apimyinvestmentsassetscore.domain.exception;

public class TotalValeuCannotBeLessThanOrderValueException extends RuntimeException {

    public TotalValeuCannotBeLessThanOrderValueException() {
        super("Total value cannot be less than order's value.");
    }
}
