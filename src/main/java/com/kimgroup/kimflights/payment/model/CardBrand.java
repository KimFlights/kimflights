package com.kimgroup.kimflights.payment.model;

public enum CardBrand {
    VISA("4"),
    MASTERCARD("5"),
    AMEX("3"),
    OTHER(""),
    UNKNOWN("");

    private final String prefix;

    CardBrand(String prefix) {
        this.prefix = prefix;
    }

    public static CardBrand fromCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.isEmpty()) {
            return UNKNOWN;
        }
        for (CardBrand brand : values()) {
            if (!brand.prefix.isEmpty() && cardNumber.startsWith(brand.prefix)) {
                return brand;
            }
        }
        return OTHER;
    }
}
