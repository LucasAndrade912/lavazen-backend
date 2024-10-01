package com.example.lavazen.models;

public enum PaymentMethod {
    PIX("pix"),
    CREDIT_CARD("credit_card"),
    CASH("cash");

    private final String method;

    PaymentMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }
}
