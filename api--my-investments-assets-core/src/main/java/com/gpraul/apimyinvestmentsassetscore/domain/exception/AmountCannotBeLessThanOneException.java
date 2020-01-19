package com.gpraul.apimyinvestmentsassetscore.domain.exception;

public class AmountCannotBeLessThanOneException extends RuntimeException {

    public AmountCannotBeLessThanOneException() {
        super("Order's amount cannot be negative.");
    }
}
